package fr.igr.odin.common.dto;

/**
 * Created on 05/08/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class AnnotationMappingDTO {
    private Long id;
    private AnnotationDTO annotation;
    private MappingDTO mapping;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnnotationDTO getAnnotation() {
        return annotation;
    }

    public void setAnnotation(AnnotationDTO annotation) {
        this.annotation = annotation;
    }

    public MappingDTO getMapping() {
        return mapping;
    }

    public void setMapping(MappingDTO mapping) {
        this.mapping = mapping;
    }
}
