package fr.igr.odin.common.dto;

import java.sql.Timestamp;

public class ImportDTO {
    private Long id;
    private String name;
    private String description;
    private TemplateDTO template;
    private String filename;
    private java.sql.Timestamp startDate;
    private java.sql.Timestamp endDate;
    private java.sql.Timestamp creationDate;
    private java.sql.Timestamp modificationDate;

    /*@JsonIgnore
    private Set<ResultDTO> results = new HashSet<>();*/

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TemplateDTO getTemplate() {
        return this.template;
    }

    public void setTemplate(TemplateDTO template) {
        this.template = template;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public java.sql.Timestamp getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(java.sql.Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public java.sql.Timestamp getModificationDate() {
        return this.modificationDate;
    }

    public void setModificationDate(java.sql.Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    /*public Set<ResultDTO> getResults() {
        return results;
    }

    public void setResults(Set<ResultDTO> results) {
        this.results = results;
    }*/
}
