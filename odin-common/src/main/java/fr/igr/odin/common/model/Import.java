package fr.igr.odin.common.model;

import javax.persistence.*;

@Entity
@Table(name = "IMPORT")
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "TEMPLATE_ID")
    private Template template;

    @Column(name = "FILENAME")
    private String filename;

    @Column(name = "START_DATE")
    private java.sql.Timestamp startDate;

    @Column(name = "END_DATE")
    private java.sql.Timestamp endDate;

    @Column(name = "CREATION_DATE", insertable = false)
    private java.sql.Timestamp creationDate;

    @Column(name = "MODIFICATION_DATE")
    private java.sql.Timestamp modificationDate;

    /*@OneToMany(mappedBy = "anImport", cascade = CascadeType.ALL)
    private Set<Result> results = new HashSet<>();*/

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

    public Template getTemplate() {
        return this.template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public java.sql.Timestamp getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.sql.Timestamp startDate) {
        this.startDate = startDate;
    }

    public java.sql.Timestamp getEndDate() {
        return this.endDate;
    }

    public void setEndDate(java.sql.Timestamp endDate) {
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

    /*public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }*/
}
