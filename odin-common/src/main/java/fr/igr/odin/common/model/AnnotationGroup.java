package fr.igr.odin.common.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 27/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "ANNOTATION_GROUP")
public class AnnotationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "`KEY`")
    private String key;

    @Column(name = "REGEX_SPLIT_GROUP")
    private String regexSplitGroup;

    @Column(name = "REGEX_SPLIT_VALUE")
    private String regexSplitValue;

    @OrderBy("order ASC")
    @OneToMany(mappedBy = "annotationGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AnnotationGroupAnnotationMappingOrder> annotationGroupAnnotationMappingOrders = new HashSet<>();

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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRegexSplitGroup() {
        return this.regexSplitGroup;
    }

    public void setRegexSplitGroup(String regexSplitGroup) {
        this.regexSplitGroup = regexSplitGroup;
    }

    public String getRegexSplitValue() {
        return this.regexSplitValue;
    }

    public void setRegexSplitValue(String regexSplitValue) {
        this.regexSplitValue = regexSplitValue;
    }

    public Set<AnnotationGroupAnnotationMappingOrder> getAnnotationGroupAnnotationMappingOrders() {
        return annotationGroupAnnotationMappingOrders;
    }

    public void setAnnotationGroupAnnotationMappingOrders(Set<AnnotationGroupAnnotationMappingOrder> annotationGroupAnnotationMappingOrders) {
        this.annotationGroupAnnotationMappingOrders = annotationGroupAnnotationMappingOrders;
    }

    public void addAnnotationGroupAnnotationOrder(AnnotationGroupAnnotationMappingOrder annotationGroupAnnotationMappingOrder) {
        annotationGroupAnnotationMappingOrder.setAnnotationGroup(this);
        this.getAnnotationGroupAnnotationMappingOrders().add(annotationGroupAnnotationMappingOrder);
    }

    public void removeAnnotationGroupAnnotationOrder(AnnotationGroupAnnotationMappingOrder annotationGroupAnnotationMappingOrder) {
        annotationGroupAnnotationMappingOrder.setAnnotationGroup(null);
        this.getAnnotationGroupAnnotationMappingOrders().remove(annotationGroupAnnotationMappingOrder);
    }
}
