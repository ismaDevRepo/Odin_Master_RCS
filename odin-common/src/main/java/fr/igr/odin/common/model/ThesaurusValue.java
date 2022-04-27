package fr.igr.odin.common.model;

import javax.persistence.*;

/**
 * Created on 02/08/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "THESAURUS_VALUE")
public class ThesaurusValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "THESAURUS_ID")
    private Thesaurus thesaurus;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OSIRIS")
    private String osiris;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Thesaurus getThesaurus() {
        return this.thesaurus;
    }

    public void setThesaurus(Thesaurus thesaurus) {
        this.thesaurus = thesaurus;
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

    public String getOsiris() {
        return this.osiris;
    }

    public void setOsiris(String osiris) {
        this.osiris = osiris;
    }
}
