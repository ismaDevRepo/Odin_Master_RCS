package fr.igr.odin.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.igr.odin.common.dto.serializer.TemplateSerializer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 11/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonSerialize(using = TemplateSerializer.class)
public class TemplateDTO {
    private Long id;
    private String name;
    private String description;
    private Set<TemplateAnnotationGroupAnnotationMappingDTO> templateAnnotationGroupAnnotationMappings = new HashSet<>();

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

    public Set<TemplateAnnotationGroupAnnotationMappingDTO> getTemplateAnnotationGroupAnnotationMappings() {
        return templateAnnotationGroupAnnotationMappings;
    }

    public void setTemplateAnnotationGroupAnnotationMappings(Set<TemplateAnnotationGroupAnnotationMappingDTO> templateAnnotationGroupAnnotationMappings) {
        this.templateAnnotationGroupAnnotationMappings = templateAnnotationGroupAnnotationMappings;
    }
}
