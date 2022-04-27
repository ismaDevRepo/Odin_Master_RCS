package fr.igr.odin.common.dto;

import java.util.Map;

/**
 * Created on 17/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResultDTO {
    private Long id;
    private VariantDTO variant;
    private ChromosomeDTO chromosome;
    private Integer genomicStart;
    private Integer genomicStop;
    private String referenceAllele;
    private String alternativeAllele;
    private Map<String, Object> info;
    private Map<String, Object> annotation;
    private Map<String, Object> genotype;
    private ImportDTO anImport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VariantDTO getVariant() {
        return variant;
    }

    public void setVariant(VariantDTO variant) {
        this.variant = variant;
    }

    public ChromosomeDTO getChromosome() {
        return chromosome;
    }

    public void setChromosome(ChromosomeDTO chromosome) {
        this.chromosome = chromosome;
    }

    public Integer getGenomicStart() {
        return genomicStart;
    }

    public void setGenomicStart(Integer genomicStart) {
        this.genomicStart = genomicStart;
    }

    public Integer getGenomicStop() {
        return genomicStop;
    }

    public void setGenomicStop(Integer genomicStop) {
        this.genomicStop = genomicStop;
    }

    public String getReferenceAllele() {
        return referenceAllele;
    }

    public void setReferenceAllele(String referenceAllele) {
        this.referenceAllele = referenceAllele;
    }

    public String getAlternativeAllele() {
        return alternativeAllele;
    }

    public void setAlternativeAllele(String alternativeAllele) {
        this.alternativeAllele = alternativeAllele;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public Map<String, Object> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Map<String, Object> annotation) {
        this.annotation = annotation;
    }

    public Map<String, Object> getGenotype() {
        return genotype;
    }

    public void setGenotype(Map<String, Object> genotype) {
        this.genotype = genotype;
    }

    public ImportDTO getAnImport() {
        return anImport;
    }

    public void setAnImport(ImportDTO anImport) {
        this.anImport = anImport;
    }
}
