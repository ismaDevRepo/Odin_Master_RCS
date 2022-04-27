package fr.igr.odin.common.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.util.LinkedHashSet;

/**
 * Created on 27/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonFilter("annotationGroupFilter")
public class AnnotationGroupDTO {
    private Long id;
    private String name;
    private String description;
    private String key;
    private String regexSplitGroup;
    private String regexSplitValue;
    private LinkedHashSet<AnnotationGroupAnnotationMappingOrderDTO> annotationGroupAnnotationMappingOrders = new LinkedHashSet<>();

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

    public LinkedHashSet<AnnotationGroupAnnotationMappingOrderDTO> getAnnotationGroupAnnotationMappingOrders() {
        return annotationGroupAnnotationMappingOrders;
    }

    public void setAnnotationGroupAnnotationMappingOrders(LinkedHashSet<AnnotationGroupAnnotationMappingOrderDTO> annotationGroupAnnotationMappingOrders) {
        this.annotationGroupAnnotationMappingOrders = annotationGroupAnnotationMappingOrders;
    }
}
