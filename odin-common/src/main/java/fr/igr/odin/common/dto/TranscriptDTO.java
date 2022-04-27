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
public class TranscriptDTO {
    private Long id;
    private GeneDTO gene;
    private String name;
    private String description;
    private Set<TranscriptCorrespondenceDTO> transcriptCorrespondences = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeneDTO getGene() {
        return this.gene;
    }

    public void setGene(GeneDTO gene) {
        this.gene = gene;
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

    public Set<TranscriptCorrespondenceDTO> getTranscriptCorrespondences() {
        return transcriptCorrespondences;
    }

    public void setTranscriptCorrespondences(Set<TranscriptCorrespondenceDTO> transcriptCorrespondences) {
        this.transcriptCorrespondences = transcriptCorrespondences;
    }
}
