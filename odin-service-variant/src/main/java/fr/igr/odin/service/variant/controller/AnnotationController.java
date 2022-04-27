package fr.igr.odin.service.variant.controller;

import fr.igr.odin.common.dto.AnnotationDTO;
import fr.igr.odin.common.dto.mapper.MappingFilterUtils;
import fr.igr.odin.common.model.Annotation;
import fr.igr.odin.service.variant.exception.ResourceNotFoundException;
import fr.igr.odin.service.variant.repository.AnnotationRepository;
import fr.igr.odin.service.variant.repository.MappingRepository;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static fr.igr.odin.service.variant.ApplicationReadyEventListener.modelMapper;

/**
 * Created on 11/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/annotations")
public class AnnotationController {
    private final AnnotationRepository annotationRepository;
    private final MappingRepository mappingRepository;

    public AnnotationController(AnnotationRepository annotationRepository, MappingRepository mappingRepository) {
        this.annotationRepository = annotationRepository;
        this.mappingRepository = mappingRepository;
    }

    @GetMapping
    public MappingJacksonValue getAllAnnotations(@RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                 @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(annotationRepository.findAll(), fieldsIncluded, fieldsExcluded);
    }

    @GetMapping("{id}")
    public MappingJacksonValue getAnnotationById(@PathVariable(value = "id") Long id, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                 @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(annotationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation", "id", id)), fieldsIncluded, fieldsExcluded);
    }

    @PostMapping
    public MappingJacksonValue createAnnotation(@Valid @RequestBody AnnotationDTO annotationDTO, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(annotationRepository.save(convertToEntity(annotationDTO)), fieldsIncluded, fieldsExcluded);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAnnotation(@PathVariable(value = "id") Long id) {
        Annotation annotation = annotationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation", "id", id));

        annotationRepository.delete(annotation);

        return ResponseEntity.ok().build();
    }

    public MappingJacksonValue convertToDto(Annotation annotation, String fieldsIncluded, String fieldsExcluded) {
        return MappingFilterUtils.applyFilter(modelMapper.map(annotation, AnnotationDTO.class), "annotation", fieldsIncluded, fieldsExcluded);
    }

    public MappingJacksonValue convertToDto(List<Annotation> annotations, String fieldsIncluded, String fieldsExcluded) {
        return MappingFilterUtils.applyFilter(modelMapper.map(annotations, new TypeToken<List<AnnotationDTO>>() {
        }.getType()), "annotation", fieldsIncluded, fieldsExcluded);
    }

    private Annotation convertToEntity(AnnotationDTO annotationDTO) {
        return modelMapper.map(annotationDTO, Annotation.class);
    }
}
