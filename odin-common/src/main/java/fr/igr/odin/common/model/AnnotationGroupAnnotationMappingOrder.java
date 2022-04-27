package fr.igr.odin.common.model;

import javax.persistence.*;

/**
 * Created on 27/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "ANNOTATION_GROUP_ANNOTATION_MAPPING_ORDER")
public class AnnotationGroupAnnotationMappingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ANNOTATION_GROUP_ID")
    private AnnotationGroup annotationGroup;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ANNOTATION_MAPPING_ID")
    private AnnotationMapping annotationMapping;

    @Column(name = "`ORDER`")
    private Integer order;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnnotationGroup getAnnotationGroup() {
        return annotationGroup;
    }

    public void setAnnotationGroup(AnnotationGroup annotationGroup) {
        this.annotationGroup = annotationGroup;
    }

    public AnnotationMapping getAnnotationMapping() {
        return annotationMapping;
    }

    public void setAnnotationMapping(AnnotationMapping annotationMapping) {
        this.annotationMapping = annotationMapping;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
