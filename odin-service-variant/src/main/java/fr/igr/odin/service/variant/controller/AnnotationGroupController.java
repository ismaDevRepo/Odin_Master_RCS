package fr.igr.odin.service.variant.controller;

import fr.igr.odin.common.dto.AnnotationGroupDTO;
import fr.igr.odin.common.dto.mapper.MappingFilterUtils;
import fr.igr.odin.common.model.AnnotationGroup;
import fr.igr.odin.service.variant.exception.ResourceNotFoundException;
import fr.igr.odin.service.variant.service.AnnotationGroupService;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static fr.igr.odin.service.variant.ApplicationReadyEventListener.modelMapper;

/**
 * Created on 27/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/annotation-groups")
public class AnnotationGroupController {
    private final AnnotationGroupService annotationGroupService;

    public AnnotationGroupController(AnnotationGroupService annotationGroupService) {
        this.annotationGroupService = annotationGroupService;
    }

    @GetMapping
    public MappingJacksonValue getAllAnnotationGroups(@RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                      @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(annotationGroupService.getAnnotationGroups(), fieldsIncluded, fieldsExcluded);
    }

    @GetMapping("{id}")
    public MappingJacksonValue getAnnotationGroupById(@PathVariable(value = "id") Long id, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                      @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(annotationGroupService.getAnnotationGroupById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Groupe d'annotations", "id", id)), fieldsIncluded, fieldsExcluded);
    }

    @PostMapping
    public MappingJacksonValue createAnnotationGroup(@Valid @RequestBody AnnotationGroupDTO annotationGroupDTO, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                     @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(annotationGroupService.createAnnotationGroup(convertToEntity(annotationGroupDTO)), fieldsIncluded, fieldsExcluded);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAnnotationGroup(@PathVariable(value = "id") Long id) {
        AnnotationGroup annotationGroup = annotationGroupService.getAnnotationGroupById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Groupe d'annotations", "id", id));

        annotationGroupService.deleteAnnotationGroup(annotationGroup);

        return ResponseEntity.ok().build();
    }

    public MappingJacksonValue convertToDto(AnnotationGroup annotationGroup, String fieldsIncluded, String fieldsExcluded) {
        return MappingFilterUtils.applyFilter(modelMapper.map(annotationGroup, AnnotationGroupDTO.class), "annotationGroup", fieldsIncluded, fieldsExcluded);
    }

    public MappingJacksonValue convertToDto(List<AnnotationGroup> annotationGroups, String fieldsIncluded, String fieldsExcluded) {
        return MappingFilterUtils.applyFilter(modelMapper.map(annotationGroups, new TypeToken<List<AnnotationGroupDTO>>() {
        }.getType()), "annotationGroup", fieldsIncluded, fieldsExcluded);
    }

    private AnnotationGroup convertToEntity(AnnotationGroupDTO annotationGroupDTO) {
        return modelMapper.map(annotationGroupDTO, AnnotationGroup.class);
    }
}
