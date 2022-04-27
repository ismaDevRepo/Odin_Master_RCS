package fr.igr.odin.common.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * Created on 02/08/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonFilter("thesaurusValueFilter")
public class ThesaurusValueDTO {
    private Long id;
    private ThesaurusDTO thesaurus;
    private String name;
    private String description;
    private String osiris;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ThesaurusDTO getThesaurus() {
        return this.thesaurus;
    }

    public void setThesaurus(ThesaurusDTO thesaurus) {
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
        return osiris;
    }

    public void setOsiris(String osiris) {
        this.osiris = osiris;
    }
}
