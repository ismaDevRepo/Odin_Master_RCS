package fr.igr.odin.common.dto;

/**
 * Created on 27/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class TemplateAnnotationGroupAnnotationMappingDTO {
    private Long id;
    private TemplateDTO template;
    private AnnotationGroupDTO annotationGroup;
    private AnnotationMappingDTO annotationMapping;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TemplateDTO getTemplate() {
        return template;
    }

    public void setTemplate(TemplateDTO template) {
        this.template = template;
    }

    public AnnotationGroupDTO getAnnotationGroup() {
        return annotationGroup;
    }

    public void setAnnotationGroup(AnnotationGroupDTO annotationGroup) {
        this.annotationGroup = annotationGroup;
    }

    public AnnotationMappingDTO getAnnotationMapping() {
        return annotationMapping;
    }

    public void setAnnotationMapping(AnnotationMappingDTO annotationMapping) {
        this.annotationMapping = annotationMapping;
    }
}
