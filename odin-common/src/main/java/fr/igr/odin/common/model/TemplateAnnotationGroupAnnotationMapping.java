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
@Table(name = "TEMPLATE_ANNOTATION_GROUP_ANNOTATION_MAPPING")
public class TemplateAnnotationGroupAnnotationMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEMPLATE_ID")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "ANNOTATION_GROUP_ID")
    private AnnotationGroup annotationGroup;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ANNOTATION_MAPPING_ID")
    private AnnotationMapping annotationMapping;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
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
}
