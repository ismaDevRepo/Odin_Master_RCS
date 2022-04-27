package fr.igr.odin.common.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 18/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class GeneDTO {
    private Long id;
    private ChromosomeDTO chromosome;
    private String name;
    private String description;
    private String symbol;
    private Set<TranscriptDTO> transcripts = new HashSet<>();
    private Set<VariantDTO> variants = new HashSet<>();

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Set<TranscriptDTO> getTranscripts() {
        return transcripts;
    }

    public void setTranscripts(Set<TranscriptDTO> transcripts) {
        this.transcripts = transcripts;
    }

    public Set<VariantDTO> getVariants() {
        return variants;
    }

    public void setVariants(Set<VariantDTO> variants) {
        this.variants = variants;
    }
}
