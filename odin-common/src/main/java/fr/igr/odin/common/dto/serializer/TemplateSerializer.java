package fr.igr.odin.common.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.igr.odin.common.dto.AnnotationGroupAnnotationMappingOrderDTO;
import fr.igr.odin.common.dto.MappingValueDTO;
import fr.igr.odin.common.dto.TemplateAnnotationGroupAnnotationMappingDTO;
import fr.igr.odin.common.dto.TemplateDTO;

import java.io.IOException;

/**
 * Created on 03/07/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class TemplateSerializer extends StdSerializer<TemplateDTO> {
    public TemplateSerializer() {
        this(null);
    }

    public TemplateSerializer(Class<TemplateDTO> t) {
        super(t);
    }

    @Override
    public void serialize(TemplateDTO templateDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", templateDTO.getId());
        jsonGenerator.writeStringField("name", templateDTO.getName());
        jsonGenerator.writeStringField("description", templateDTO.getDescription());
        jsonGenerator.writeArrayFieldStart("templateAnnotationGroupAnnotationMappings");
        for (TemplateAnnotationGroupAnnotationMappingDTO templateAnnotationGroupAnnotationMappingDTO : templateDTO.getTemplateAnnotationGroupAnnotationMappings()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", templateAnnotationGroupAnnotationMappingDTO.getId());
            if (null != templateAnnotationGroupAnnotationMappingDTO.getAnnotationGroup()) {
                jsonGenerator.writeObjectFieldStart("annotationGroup");
                jsonGenerator.writeNumberField("id", templateAnnotationGroupAnnotationMappingDTO.getAnnotationGroup().getId());
                jsonGenerator.writeStringField("name", templateAnnotationGroupAnnotationMappingDTO.getAnnotationGroup().getName());
                jsonGenerator.writeStringField("description", templateAnnotationGroupAnnotationMappingDTO.getAnnotationGroup().getDescription());
                jsonGenerator.writeStringField("key", templateAnnotationGroupAnnotationMappingDTO.getAnnotationGroup().getKey());
                jsonGenerator.writeStringField("regexSplitGroup", templateAnnotationGroupAnnotationMappingDTO.getAnnotationGroup().getRegexSplitGroup());
                jsonGenerator.writeStringField("regexSplitValue", templateAnnotationGroupAnnotationMappingDTO.getAnnotationGroup().getRegexSplitValue());

                jsonGenerator.writeArrayFieldStart("annotationGroupAnnotationMappingOrders");
                for (AnnotationGroupAnnotationMappingOrderDTO annotationGroupAnnotationMappingOrderDTO : templateAnnotationGroupAnnotationMappingDTO.getAnnotationGroup().getAnnotationGroupAnnotationMappingOrders()) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", annotationGroupAnnotationMappingOrderDTO.getId());
                    if (null != annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping()) {
                        jsonGenerator.writeObjectFieldStart("annotationMapping");
                        if (null != annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation()) {
                            jsonGenerator.writeObjectFieldStart("annotation");
                            jsonGenerator.writeNumberField("id", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation().getId());
                            jsonGenerator.writeStringField("typeAnnotation", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation().getTypeAnnotation());
                            jsonGenerator.writeStringField("name", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation().getName());
                            jsonGenerator.writeStringField("description", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation().getDescription());
                            jsonGenerator.writeStringField("key", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation().getKey());
                            jsonGenerator.writeStringField("type", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation().getType());
                            jsonGenerator.writeStringField("regexSplitGroup", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation().getRegexSplitGroup());
                            //jsonGenerator.writeStringField("regexCatchValue", annotationGroupAnnotationOrderDTO.getAnnotation().getRegexCatchValue());
                            jsonGenerator.writeBooleanField("locked", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getAnnotation().getLocked());
                            jsonGenerator.writeEndObject();
                        }
                        if (null != annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getMapping()) {
                            jsonGenerator.writeObjectFieldStart("mapping");
                            jsonGenerator.writeNumberField("id", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getMapping().getId());
                            jsonGenerator.writeStringField("name", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getMapping().getName());
                            jsonGenerator.writeStringField("description", annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getMapping().getDescription());
                            jsonGenerator.writeArrayFieldStart("mappingValues");
                            for (MappingValueDTO mappingValueDTO : annotationGroupAnnotationMappingOrderDTO.getAnnotationMapping().getMapping().getMappingValues()) {
                                jsonGenerator.writeStartObject();
                                jsonGenerator.writeNumberField("id", mappingValueDTO.getId());
                                jsonGenerator.writeStringField("key", mappingValueDTO.getKey());
                                jsonGenerator.writeStringField("value", mappingValueDTO.getValue());
                                if (null != mappingValueDTO.getThesaurusValue()) {
                                    jsonGenerator.writeObjectFieldStart("thesaurusValue");
                                    jsonGenerator.writeNumberField("id", mappingValueDTO.getThesaurusValue().getId());
                                    jsonGenerator.writeStringField("name", mappingValueDTO.getThesaurusValue().getName());
                                    jsonGenerator.writeStringField("description", mappingValueDTO.getThesaurusValue().getDescription());
                                    jsonGenerator.writeEndObject();
                                }
                                jsonGenerator.writeEndObject();
                            }
                            jsonGenerator.writeEndArray();
                            jsonGenerator.writeEndObject();
                        }
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeNumberField("order", annotationGroupAnnotationMappingOrderDTO.getOrder());
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();

                jsonGenerator.writeEndObject();
            }
            if (null != templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping()) {
                jsonGenerator.writeObjectFieldStart("annotationMapping");
                if (null != templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation()) {
                    jsonGenerator.writeObjectFieldStart("annotation");
                    jsonGenerator.writeNumberField("id", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getId());
                    jsonGenerator.writeStringField("typeAnnotation", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getTypeAnnotation());
                    jsonGenerator.writeStringField("name", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getName());
                    jsonGenerator.writeStringField("description", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getDescription());
                    jsonGenerator.writeStringField("key", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getKey());
                    jsonGenerator.writeStringField("type", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getType());
                    jsonGenerator.writeStringField("regexSplitGroup", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getRegexSplitGroup());
                    //jsonGenerator.writeStringField("regexCatchValue", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getRegexCatchValue());
                    jsonGenerator.writeBooleanField("locked", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getAnnotation().getLocked());
                    jsonGenerator.writeEndObject();
                }
                if (null != templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getMapping()) {
                    jsonGenerator.writeObjectFieldStart("mapping");
                    jsonGenerator.writeNumberField("id", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getMapping().getId());
                    jsonGenerator.writeStringField("name", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getMapping().getName());
                    jsonGenerator.writeStringField("description", templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getMapping().getDescription());
                    jsonGenerator.writeArrayFieldStart("mappingValues");
                    for (MappingValueDTO mappingValueDTO : templateAnnotationGroupAnnotationMappingDTO.getAnnotationMapping().getMapping().getMappingValues()) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeNumberField("id", mappingValueDTO.getId());
                        jsonGenerator.writeStringField("key", mappingValueDTO.getKey());
                        jsonGenerator.writeStringField("value", mappingValueDTO.getValue());
                        if (null != mappingValueDTO.getThesaurusValue()) {
                            jsonGenerator.writeObjectFieldStart("thesaurusValue");
                            jsonGenerator.writeNumberField("id", mappingValueDTO.getThesaurusValue().getId());
                            jsonGenerator.writeStringField("name", mappingValueDTO.getThesaurusValue().getName());
                            jsonGenerator.writeStringField("description", mappingValueDTO.getThesaurusValue().getDescription());
                            jsonGenerator.writeEndObject();
                        }
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeEndArray();
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
