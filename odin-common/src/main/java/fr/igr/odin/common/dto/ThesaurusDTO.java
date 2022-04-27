package fr.igr.odin.common.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 13/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonFilter("thesaurusFilter")
public class ThesaurusDTO {
    private Long id;
    private String name;
    private String description;
    private Set<ThesaurusValueDTO> thesaurusValues = new HashSet<>();

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

    public Set<ThesaurusValueDTO> getThesaurusValues() {
        return thesaurusValues;
    }

    public void setThesaurusValues(Set<ThesaurusValueDTO> thesaurusValues) {
        this.thesaurusValues = thesaurusValues;
    }
}
