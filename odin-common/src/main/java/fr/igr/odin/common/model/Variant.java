package fr.igr.odin.common.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 05/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "VARIANT")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHROMOSOME_ID")
    private Chromosome chromosome;

    @Column(name = "GENOMIC_START")
    private Integer genomicStart;

    @Column(name = "GENOMIC_STOP")
    private Integer genomicStop;

    @Column(name = "REFERENCE_ALLELE")
    private String referenceAllele;

    @Column(name = "ALTERNATIVE_ALLELE")
    private String alternativeAllele;

    @ManyToOne
    @JoinColumn(name = "CLINICAL_SIGNIFICANCE_ID")
    private ClinicalSignificance clinicalSignificance;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<Result> results = new HashSet<>();

    @ManyToMany(mappedBy = "variants")
    private Set<Gene> genes = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chromosome getChromosome() {
        return chromosome;
    }

    public void setChromosome(Chromosome chromosome) {
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

    public ClinicalSignificance getClinicalSignificance() {
        return this.clinicalSignificance;
    }

    public void setClinicalSignificance(ClinicalSignificance clinicalSignificance) {
        this.clinicalSignificance = clinicalSignificance;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    public void addResult(Result result) {
        result.setVariant(this);
        this.getResults().add(result);
    }

    public void removeResult(Result result) {
        result.setVariant(null);
        this.getResults().remove(result);
    }

    public Set<Gene> getGenes() {
        return genes;
    }

    public void setGenes(Set<Gene> genes) {
        this.genes = genes;
    }
}
