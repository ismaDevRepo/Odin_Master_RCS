package fr.igr.odin.common.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 18/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "TRANSCRIPT")
public class Transcript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GENE_ID")
    private Gene gene;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "transcript", cascade = CascadeType.ALL)
    private Set<TranscriptCorrespondence> transcriptCorrespondences = new HashSet<>();


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gene getGene() {
        return this.gene;
    }

    public void setGene(Gene gene) {
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

    public Set<TranscriptCorrespondence> getTranscriptCorrespondences() {
        return transcriptCorrespondences;
    }

    public void setTranscriptCorrespondences(Set<TranscriptCorrespondence> transcriptCorrespondences) {
        this.transcriptCorrespondences = transcriptCorrespondences;
    }

    public void addTranscriptCorrespondence(TranscriptCorrespondence transcriptCorrespondence) {
        transcriptCorrespondence.setTranscript(this);
        this.getTranscriptCorrespondences().add(transcriptCorrespondence);
    }

    public void removeTranscriptCorrespondence(TranscriptCorrespondence transcriptCorrespondence) {
        transcriptCorrespondence.setTranscript(null);
        this.getTranscriptCorrespondences().remove(transcriptCorrespondence);
    }
}
