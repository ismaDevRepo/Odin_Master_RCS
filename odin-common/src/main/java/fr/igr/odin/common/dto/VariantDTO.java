package fr.igr.odin.common.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 05/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonFilter("variantFilter")
public class VariantDTO {
    private Long id;
    private ChromosomeDTO chromosome;
    private Integer genomicStart;
    private Integer genomicStop;
    private String referenceAllele;
    private String alternativeAllele;
    private ClinicalSignificanceDTO clinicalSignificance = new ClinicalSignificanceDTO();
    private Set<ResultDTO> results = new HashSet<>();
    private Set<GeneDTO> genes = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChromosomeDTO getChromosome() {
        return this.chromosome;
    }

    public void setChromosome(ChromosomeDTO chromosome) {
        this.chromosome = chromosome;
    }

    public Integer getGenomicStart() {
        return this.genomicStart;
    }

    public void setGenomicStart(Integer genomicStart) {
        this.genomicStart = genomicStart;
    }

    public Integer getGenomicStop() {
        return this.genomicStop;
    }

    public void setGenomicStop(Integer genomicStop) {
        this.genomicStop = genomicStop;
    }

    public String getReferenceAllele() {
        return this.referenceAllele;
    }

    public void setReferenceAllele(String referenceAllele) {
        this.referenceAllele = referenceAllele;
    }

    public String getAlternativeAllele() {
        return this.alternativeAllele;
    }

    public void setAlternativeAllele(String alternativeAllele) {
        this.alternativeAllele = alternativeAllele;
    }

    public ClinicalSignificanceDTO getClinicalSignificance() {
        return this.clinicalSignificance;
    }

    public void setClinicalSignificance(ClinicalSignificanceDTO clinicalSignificance) {
        this.clinicalSignificance = clinicalSignificance;
    }

    public Set<ResultDTO> getResults() {
        return results;
    }

    public void setResults(Set<ResultDTO> results) {
        this.results = results;
    }

    public void addResult(ResultDTO result) {
        result.setVariant(this);
        this.getResults().add(result);
    }

    public void removeResult(ResultDTO result) {
        result.setVariant(null);
        this.getResults().remove(result);
    }

    public Set<GeneDTO> getGenes() {
        return genes;
    }

    public void setGenes(Set<GeneDTO> genes) {
        this.genes = genes;
    }
}
