package fr.igr.odin.common.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * Created on 13/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonFilter("mappingValueFilter")
public class MappingValueDTO {
    private Long id;
    private MappingDTO mapping;
    private String key;
    private String value;
    private ThesaurusValueDTO thesaurusValue;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MappingDTO getMapping() {
        return this.mapping;
    }

    public void setMapping(MappingDTO mapping) {
        this.mapping = mapping;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ThesaurusValueDTO getThesaurusValue() {
        return thesaurusValue;
    }

    public void setThesaurusValue(ThesaurusValueDTO thesaurusValue) {
        this.thesaurusValue = thesaurusValue;
    }
}
