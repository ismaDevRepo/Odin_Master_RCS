package fr.igr.odin.common.model;

import javax.persistence.*;

/**
 * Created on 17/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "RESULT")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VARIANT_ID")
    private Variant variant;

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

    @Column(name = "INFO")
    private String info;

    @Column(name = "ANNOTATION")
    private String annotation;

    @Column(name = "GENOTYPE")
    private String genotype;

    @ManyToOne
    @JoinColumn(name = "IMPORT_ID")
    private Import anImport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public Chromosome getChromosome() {
        return chromosome;
    }

    public void setChromosome(Chromosome chromosome) {
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    public Import getAnImport() {
        return anImport;
    }

    public void setAnImport(Import anImport) {
        this.anImport = anImport;
    }
}
