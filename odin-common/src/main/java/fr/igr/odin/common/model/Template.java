package fr.igr.odin.common.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 11/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "TEMPLATE")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TemplateAnnotationGroupAnnotationMapping> templateAnnotationGroupAnnotationMappings = new HashSet<>();

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

    public Set<TemplateAnnotationGroupAnnotationMapping> getTemplateAnnotationGroupAnnotationMappings() {
        return templateAnnotationGroupAnnotationMappings;
    }

    public void setTemplateAnnotationGroupAnnotationMappings(Set<TemplateAnnotationGroupAnnotationMapping> templateAnnotationGroupAnnotationMappings) {
        this.templateAnnotationGroupAnnotationMappings = templateAnnotationGroupAnnotationMappings;
    }

    public void addTemplateAnnotationGroup(TemplateAnnotationGroupAnnotationMapping templateAnnotationGroupAnnotationMapping) {
        templateAnnotationGroupAnnotationMapping.setTemplate(this);
        this.getTemplateAnnotationGroupAnnotationMappings().add(templateAnnotationGroupAnnotationMapping);
    }

    public void removeTemplateAnnotationGroup(TemplateAnnotationGroupAnnotationMapping templateAnnotationGroupAnnotationMapping) {
        templateAnnotationGroupAnnotationMapping.setTemplate(null);
        this.getTemplateAnnotationGroupAnnotationMappings().remove(templateAnnotationGroupAnnotationMapping);
    }
}
