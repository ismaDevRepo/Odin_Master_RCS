package fr.igr.odin.common.model;

import javax.persistence.*;

/**
 * Created on 11/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "ANNOTATION")
public class Annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE_ANNOTATION")
    private String typeAnnotation;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "`KEY`")
    private String key;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "REGEX_SPLIT_GROUP")
    private String regexSplitGroup;

    /*@Column(name = "REGEX_CATCH_VALUE")
    private String regexCatchValue;*/

    @Column(name = "LOCKED", insertable = false)
    private Boolean locked;

    @OneToOne
    @JoinColumn(name = "THESAURUS_ID")
    private Thesaurus thesaurus;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeAnnotation() {
        return typeAnnotation;
    }

    public void setTypeAnnotation(String typeAnnotation) {
        this.typeAnnotation = typeAnnotation;
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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegexSplitGroup() {
        return regexSplitGroup;
    }

    public void setRegexSplitGroup(String regexSplitGroup) {
        this.regexSplitGroup = regexSplitGroup;
    }

    /*public String getRegexCatchValue() {
        return this.regexCatchValue;
    }

    public void setRegexCatchValue(String regexCatchValue) {
        this.regexCatchValue = regexCatchValue;
    }*/

    public Boolean getLocked() {
        return this.locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Thesaurus getThesaurus() {
        return thesaurus;
    }

    public void setThesaurus(Thesaurus thesaurus) {
        this.thesaurus = thesaurus;
    }
}
