package fr.igr.odin.agent.importation.route;

import fr.igr.odin.common.dto.*;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pharmgkb.parser.vcf.VcfParser;
import org.pharmgkb.parser.vcf.model.VcfSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static fr.igr.odin.common.Constant.GENOTYPE;

/**
 * Created on 04/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class Route extends RouteBuilder {
    private final static String ODIN_IMPORT_ID = "ODIN_IMPORT_ID";

    private Logger logger = LoggerFactory.getLogger(Route.class);

    @Value("${odin.agent.importation.service.variant.url.api}")
    private String serviceVariantAPI;

    @Value("${odin.agent.importation.template.id}")
    private Integer templateID;

    @Value("${odin.agent.importation.path.file.done}")
    private String pathToFileDone;

    @Value("${odin.agent.importation.path.file.out}")
    private String pathToFileOut;

    @Value("${odin.agent.importation.path.file.error}")
    private String pathToFileError;

    @Override
    public void configure() {
        RestTemplate restTemplate = new RestTemplate();

        if (serviceVariantAPI.endsWith("/")) {
            serviceVariantAPI = serviceVariantAPI.substring(0, serviceVariantAPI.length() - 1);
        }

        URI templateURI = null;
        try {
            templateURI = new URI(String.format("%s/templates/%s", serviceVariantAPI, templateID));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ResponseEntity<TemplateDTO> template;
        try {
            template = restTemplate.getForEntity(templateURI, TemplateDTO.class);
        } catch (RestClientException e) {
            logger.error("Est-ce que le template ID : {} existe bien ? Message : {} Cause : {}", templateID, e.getMessage(), e.getCause());
            throw e;
        }

        Set<TemplateAnnotationGroupAnnotationMappingDTO> templateAnnotationGroupAnnotationMappings = template.getBody().getTemplateAnnotationGroupAnnotationMappings();
        Map<String, String> typeAnnotation = new HashMap<>();
        Map<String, AnnotationMappingDTO> annotationMappings = new HashMap<>();
        Map<String, AnnotationDTO> genotypes = new HashMap<>();
        Map<String, AnnotationGroupDTO> annotationGroups = new HashMap<>();
        Map<String, MappingValueDTO> mappingValuesMap = new HashMap<>();

        for (TemplateAnnotationGroupAnnotationMappingDTO templateAnnotationGroupAnnotationMapping : templateAnnotationGroupAnnotationMappings) {
            if (null != templateAnnotationGroupAnnotationMapping.getAnnotationMapping()) {
                //System.out.println("C'est une annotation");
                if (templateAnnotationGroupAnnotationMapping.getAnnotationMapping().getAnnotation().getTypeAnnotation().equals(GENOTYPE)) {
                    genotypes.put(templateAnnotationGroupAnnotationMapping.getAnnotationMapping().getAnnotation().getKey(), templateAnnotationGroupAnnotationMapping.getAnnotationMapping().getAnnotation());
                } else {
                    typeAnnotation.put(templateAnnotationGroupAnnotationMapping.getAnnotationMapping().getAnnotation().getKey(), "ANNOTATION");
                    annotationMappings.put(templateAnnotationGroupAnnotationMapping.getAnnotationMapping().getAnnotation().getKey(), templateAnnotationGroupAnnotationMapping.getAnnotationMapping());
                }
                if ("CHROMOSOME".equals(templateAnnotationGroupAnnotationMapping.getAnnotationMapping().getAnnotation().getKey())) {
                    mappingValuesMap = templateAnnotationGroupAnnotationMapping.getAnnotationMapping().getMapping().getMappingValuesToMap();
                }
            } else if (null != templateAnnotationGroupAnnotationMapping.getAnnotationGroup()) {
                //System.out.println("C'est un groupe d'annotations");
                typeAnnotation.put(templateAnnotationGroupAnnotationMapping.getAnnotationGroup().getKey(), "ANNOTATION_GROUP");
                annotationGroups.put(templateAnnotationGroupAnnotationMapping.getAnnotationGroup().getKey(), templateAnnotationGroupAnnotationMapping.getAnnotationGroup());
            }
        }

        URI resultURI = null;
        try {
            resultURI = new URI(String.format("%s/results", serviceVariantAPI));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        URI importURI = null;
        try {
            importURI = new URI(String.format("%s/imports", serviceVariantAPI));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        onException(Exception.class).process(exchange -> {
            Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

            if (null != exception) {
                logger.error("Error has occurred :", exception);

                URL url = new URL(pathToFileError);
                Path path = Paths.get(url.toURI());
                url = Paths.get(path.toString(), exchange.getIn().getHeader(Exchange.FILE_NAME, String.class) + ".error").toUri().toURL();

                // Ecriture d'un fichier d'erreur
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(url.toURI()), false));
                writer.write(String.format("%s ERROR with file : %s", new Timestamp(System.currentTimeMillis()), exchange.getIn().getHeader(Exchange.FILE_NAME, String.class)));
                writer.write(System.lineSeparator());
                writer.write(ExceptionUtils.getStackTrace(exception));
                writer.close();

                // Rollback : on supprime les résultats importés
                logger.error("Retour arrière : suppression des résultats importés");
                String deleteResultsURIString = String.format("%s/results/import/{anImportId}", serviceVariantAPI);
                URI deleteResultsURI = UriComponentsBuilder.fromUriString(deleteResultsURIString).build(exchange.getProperty(ODIN_IMPORT_ID));
                restTemplate.delete(deleteResultsURI);
            }
        }).handled(true).to(pathToFileError);

        ResponseEntity<TemplateDTO> finalTemplate = template;
        Map<String, MappingValueDTO> finalMappingValuesMap = mappingValuesMap;
        URI finalResultURI = resultURI;
        URI finalImportURI = importURI;

        from(pathToFileOut)
                .process(exchange -> {
                    InputStream inputStream = exchange.getIn().getBody(InputStream.class);
                    ResultDTO result = new ResultDTO();

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    logger.info("-----");
                    logger.info("(1/4) Début de l'import : {}", dtf.format(now));
                    AtomicReference<Integer> numberOfLine = new AtomicReference<>(0);
                    AtomicReference<Integer> numberOfError = new AtomicReference<>(0);

                    // Création d'une ligne d'import
                    ImportDTO anImport = new ImportDTO();
                    anImport.setTemplate(finalTemplate.getBody());
                    anImport.setFilename(exchange.getIn().getHeader(Exchange.FILE_NAME, String.class));
                    ResponseEntity<ImportDTO> importResponseEntity;
                    try {
                        importResponseEntity = restTemplate.postForEntity(finalImportURI, anImport, ImportDTO.class);
                    } catch (RestClientException e) {
                        e.printStackTrace();
                        throw e;
                    }

                    ImportDTO resultImport = importResponseEntity.getBody();
                    if (null != resultImport) {
                        exchange.setProperty(ODIN_IMPORT_ID, resultImport.getId());
                        resultImport.setStartDate(new Timestamp(System.currentTimeMillis()));

                        // Lecture d'un fichier .vcf
                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                            new VcfParser.Builder()
                                    .fromReader(reader)
                                    .parseWith((metadata, position, sampleData) -> {
                                        if (finalMappingValuesMap.containsKey(position.getChromosome())) {
                                            ChromosomeDTO chromosome = new ChromosomeDTO();
                                            chromosome.setId(Long.parseLong(finalMappingValuesMap.get(position.getChromosome()).getValue()));
                                            result.setChromosome(chromosome);
                                            result.setGenomicStart(Math.toIntExact(position.getPosition()));
                                            result.setReferenceAllele(position.getRef());
                                            result.setAlternativeAllele(position.getAltBases().toString());

                                            JSONObject infoJSON = new JSONObject(position.getInfo().asMap());
                                            JSONObject annotationJSON = new JSONObject();
                                            JSONObject genotypeJSON = new JSONObject();

                                            // ANNOTATION
                                            for (Map.Entry<String, String> entry : typeAnnotation.entrySet()) {
                                                if (infoJSON.has(entry.getKey())) {
                                                    int numberOfElement = infoJSON.getJSONArray(entry.getKey()).length();
                                                    for (int i = 0; i < numberOfElement; i++) {
                                                        if ("ANNOTATION_GROUP".equals(entry.getValue())) {
                                                            if (null != annotationGroups.get(entry.getKey()).getRegexSplitGroup() && !annotationGroups.get(entry.getKey()).getRegexSplitGroup().isEmpty()
                                                                    && null != annotationGroups.get(entry.getKey()).getRegexSplitValue() && !annotationGroups.get(entry.getKey()).getRegexSplitValue().isEmpty() &&
                                                                    !"\\,".equals(annotationGroups.get(entry.getKey()).getRegexSplitGroup())) { // la virgule (,) est prise en compte dans le parseur utilisé pour séparer les différents groupes
                                                                String annotationGroup = infoJSON.getJSONArray(entry.getKey()).getString(i);
                                                                String[] groups = annotationGroup.split(annotationGroups.get(entry.getKey()).getRegexSplitGroup());
                                                                for (String group : groups) {
                                                                    splitAndAddAnnotation(annotationGroups.get(entry.getKey()), annotationJSON, group);
                                                                }
                                                            } else if (null != annotationGroups.get(entry.getKey()).getRegexSplitValue() && !annotationGroups.get(entry.getKey()).getRegexSplitValue().isEmpty()) {
                                                                String item = infoJSON.getJSONArray(entry.getKey()).getString(i);
                                                                splitAndAddAnnotation(annotationGroups.get(entry.getKey()), annotationJSON, item);
                                                            }
                                                        } else if ("ANNOTATION".equals(entry.getValue())) {
                                                            String value = getMappedValue(infoJSON.getJSONArray(entry.getKey()).getString(i), annotationMappings.get(entry.getKey()).getMapping());
                                                            addAnnotation(annotationJSON, entry.getKey(), annotationMappings.get(entry.getKey()).getAnnotation().getType(), value);
                                                        }
                                                    }
                                                }
                                            }

                                            result.setInfo(infoJSON.toMap());
                                            result.setAnnotation(annotationJSON.toMap());

                                            // GENOTYPE
                                            for (VcfSample sample : sampleData) {
                                                for (Map.Entry<String, AnnotationDTO> entry : genotypes.entrySet()) {
                                                    if (sample.containsProperty(entry.getKey())) {
                                                        if (null != genotypes.get(entry.getKey()).getRegexSplitGroup()) {
                                                            String[] split = sample.getProperty(entry.getKey()).split(genotypes.get(entry.getKey()).getRegexSplitGroup());
                                                            for (String value : split) {
                                                                addAnnotation(genotypeJSON, entry.getKey(), genotypes.get(entry.getKey()).getType(), value);
                                                            }
                                                        } else {
                                                            addAnnotation(genotypeJSON, entry.getKey(), genotypes.get(entry.getKey()).getType(), sample.getProperty(entry.getKey()));
                                                        }
                                                    }
                                                }
                                            }

                                            result.setGenotype(genotypeJSON.toMap());
                                            result.setAnImport(resultImport);

                                            try {
                                                ResponseEntity<ResultDTO> resultResponseEntity = restTemplate.postForEntity(finalResultURI, result, ResultDTO.class);
                                                numberOfLine.updateAndGet(v -> v + 1);
                                            } catch (RestClientException e) {
                                                logger.error("Erreur d'import : {}", dtf.format(LocalDateTime.now()));
                                                numberOfError.updateAndGet(v -> v + 1);
                                                logger.error("Nombre d'erreur d'import : {}", numberOfError);
                                                e.printStackTrace();
                                            }
                                        }
                                    })
                                    .build().parse();
                        }
                        logger.info("(2/4) Fin de l'import : {}. Nombre de ligne importée : {}. Nombre d'erreur : {}", dtf.format(LocalDateTime.now()), numberOfLine, numberOfError);

                        logger.info("(3/4) Début de la recherche de variant : {}", dtf.format(LocalDateTime.now()));
                        String searchVariantURIString = String.format("%s/results/search-variant", serviceVariantAPI);
                        UriComponents searchVariantURI = UriComponentsBuilder.fromUriString(searchVariantURIString).queryParam("anImportId", resultImport.getId()).build();
                        RequestEntity<Void> requestEntity = RequestEntity.put(searchVariantURI.toUri()).build();
                        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<>() {
                        };
                        ResponseEntity<Map<String, Object>> responseSearchVariant = restTemplate.exchange(requestEntity, typeRef);

                        if (null == responseSearchVariant.getBody()) {
                            logger.info("(4/4) Fin de la recherche de variant : {}.", dtf.format(LocalDateTime.now()));
                            logger.warn("Le body est vide");
                        } else {
                            logger.info("(4/4) Fin de la recherche de variant : {}. Nombre de nouveau variant : {}. Nombre de variant connu : {}",
                                    dtf.format(LocalDateTime.now()), responseSearchVariant.getBody().get("numberOfNewVariant"), responseSearchVariant.getBody().get("numberOfKnownVariant"));
                        }

                        resultImport.setEndDate(new Timestamp(System.currentTimeMillis()));
                        ResponseEntity<ImportDTO> importDTOResponseEntity = restTemplate.postForEntity(finalImportURI, resultImport, ImportDTO.class);
                    } else {
                        logger.error("Erreur : la ligne d'import n'a pas été créée?");
                    }
                })
                .to(pathToFileDone);
    }

    /**
     * @param annotationGroup le groupe d'annotations
     * @param annotationJSON  JSON actuel de la partie ANNOTATION
     * @param item            groupe d'annotation a parser sous forme de string avec des séparateurs
     * @since 1.0.0
     */
    private void splitAndAddAnnotation(AnnotationGroupDTO annotationGroup, JSONObject annotationJSON, String item) {
        LinkedHashSet<AnnotationGroupAnnotationMappingOrderDTO> annotationGroupAnnotationMappingOrders = annotationGroup.getAnnotationGroupAnnotationMappingOrders();
        Iterator<AnnotationGroupAnnotationMappingOrderDTO> it = annotationGroupAnnotationMappingOrders.iterator();
        String[] split = item.split(annotationGroup.getRegexSplitValue());
        while (it.hasNext()) {
            AnnotationGroupAnnotationMappingOrderDTO annotationGroupAnnotationMappingOrder = it.next();
            String value = getMappedValue(split[annotationGroupAnnotationMappingOrder.getOrder() - 1], annotationGroupAnnotationMappingOrder.getAnnotationMapping().getMapping());
            addAnnotation(annotationJSON, annotationGroupAnnotationMappingOrder.getAnnotationMapping().getAnnotation().getKey(),
                    annotationGroupAnnotationMappingOrder.getAnnotationMapping().getAnnotation().getType(), value);
        }
    }

    /**
     * @param value      valeur à vérifier si elle doit être remplacée par une autre valeur
     * @param mappingDTO mapping à utiliser
     * @return valeur traduite en fonction du mapping sinon la même
     * @since 1.0.0
     */
    private String getMappedValue(String value, MappingDTO mappingDTO) {
        if (null != mappingDTO) {
            Map<String, MappingValueDTO> mappingValueMap = mappingDTO.getMappingValuesToMap();
            if (mappingValueMap.containsKey(value)) {
                if (null == mappingValueMap.get(value).getValue()) {
                    value = mappingValueMap.get(value).getThesaurusValue().getName();
                } else {
                    value = mappingValueMap.get(value).getValue();
                }
            } else {
                logger.warn("La valeur '{}' n'a pas de correspondance dans le mapping utilisé. Elle ne sera pas remplacée et ne pourra pas être filtrée. Penser à mettre à jour le mapping '{}' ({})",
                        value, mappingDTO.getName(), mappingDTO.getId());
            }
        }
        return value;
    }

    /**
     * @param annotationJSON     JSON actuel de la partie ANNOTATION
     * @param annotationKey      Clé de l'annotation
     * @param annotationDataType Type de donnée de la valeur à insérer (BOOLEAN, DOUBLE, INTEGER ou STRING)
     * @param infoJSONValue      Valeur correspondante dans la partie INFO
     * @since 1.0.0
     */
    private void addAnnotation(JSONObject annotationJSON, String annotationKey, String annotationDataType, String infoJSONValue) {
        JSONArray jsonArray = new JSONArray();

        if (null == annotationJSON.optJSONArray(annotationKey)) {
            annotationJSON.put(annotationKey, jsonArray);
        }

        if (".".equals(infoJSONValue)) { // Pour valeur manquante/the MISSING value
            annotationJSON.getJSONArray(annotationKey).put(infoJSONValue);
        } else if (!infoJSONValue.isEmpty()) {
            switch (annotationDataType) {
                case "BOOLEAN":
                    try {
                        annotationJSON.getJSONArray(annotationKey).put(Boolean.parseBoolean(infoJSONValue));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case "DOUBLE":
                    try {
                        annotationJSON.getJSONArray(annotationKey).put(Double.parseDouble(infoJSONValue));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case "INTEGER":
                    try {
                        annotationJSON.getJSONArray(annotationKey).put(Integer.parseInt(infoJSONValue));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case "STRING":
                    try {
                        annotationJSON.getJSONArray(annotationKey).put(infoJSONValue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
