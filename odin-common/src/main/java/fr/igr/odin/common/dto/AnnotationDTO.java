package fr.igr.odin.common.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * Created on 11/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonFilter("annotationFilter")
public class AnnotationDTO {
    private Long id;
    private String typeAnnotation;
    private String name;
    private String description;
    private String key;
    private String type;
    private String regexSplitGroup;
    //private String regexCatchValue;
    private Boolean locked;
    private ThesaurusDTO thesaurus;

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
        return this.regexSplitGroup;
    }

    public void setRegexSplitGroup(String regexSplitGroup) {
        this.regexSplitGroup = regexSplitGroup;
    }

    /*public String getRegexCatchValue() {
        return regexCatchValue;
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

    public ThesaurusDTO getThesaurus() {
        return thesaurus;
    }

    public void setThesaurus(ThesaurusDTO thesaurus) {
        this.thesaurus = thesaurus;
    }
}
