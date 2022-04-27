package fr.igr.odin.common.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * Created on 27/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonFilter("annotationGroupAnnotationMappingOrderFilter")
public class AnnotationGroupAnnotationMappingOrderDTO {
    private Long id;
    private AnnotationGroupDTO annotationGroup;
    private AnnotationMappingDTO annotationMapping;
    private Integer order;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
