package fr.igr.odin.common.model;

import javax.persistence.*;

/**
 * Created on 19/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "TRANSCRIPT_CORRESPONDENCE")
public class TranscriptCorrespondence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRANSCRIPT_ID")
    private Transcript transcript;

    @ManyToOne
    @JoinColumn(name = "DATABASE_ID")
    private Database database;

    @Column(name = "VALUE")
    private String value;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transcript getTranscript() {
        return this.transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public Database getDatabase() {
        return this.database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
