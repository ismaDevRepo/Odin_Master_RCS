package fr.igr.odin.common.dto;

/**
 * Created on 19/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class TranscriptCorrespondenceDTO {
    private Long id;
    private TranscriptDTO transcript;
    private DatabaseDTO database;
    private String value;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TranscriptDTO getTranscript() {
        return this.transcript;
    }

    public void setTranscript(TranscriptDTO transcript) {
        this.transcript = transcript;
    }

    public DatabaseDTO getDatabase() {
        return this.database;
    }

    public void setDatabase(DatabaseDTO database) {
        this.database = database;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
