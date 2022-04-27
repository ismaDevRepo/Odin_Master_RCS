package fr.igr.odin.service.variant.controller;

import fr.igr.odin.common.dto.MappingDTO;
import fr.igr.odin.common.dto.mapper.MappingFilterUtils;
import fr.igr.odin.common.model.Mapping;
import fr.igr.odin.service.variant.exception.ResourceNotFoundException;
import fr.igr.odin.service.variant.repository.MappingRepository;
import fr.igr.odin.service.variant.repository.MappingValueRepository;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static fr.igr.odin.service.variant.ApplicationReadyEventListener.modelMapper;

/**
 * Created on 05/08/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/mappings")
public class MappingController {
    private final MappingRepository mappingRepository;
    private final MappingValueRepository mappingValueRepository;

    public MappingController(MappingRepository mappingRepository, MappingValueRepository mappingValueRepository) {
        this.mappingRepository = mappingRepository;
        this.mappingValueRepository = mappingValueRepository;
    }

    @GetMapping
    public MappingJacksonValue getAllMappings(@RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                              @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(mappingRepository.findAll(), fieldsIncluded, fieldsExcluded);
    }

    @GetMapping("{id}")
    public MappingJacksonValue getMappingById(@PathVariable(value = "id") Long id, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                              @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(mappingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping", "id", id)), fieldsIncluded, fieldsExcluded);
    }

    @GetMapping("{id}/mapping-values")
    public MappingJacksonValue getAllMappingValuesById(@PathVariable(value = "id") Long id, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                       @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return MappingValueController.convertToDto(mappingValueRepository.findAllByMappingId(id), fieldsIncluded, fieldsExcluded);
    }

    @PostMapping
    public MappingJacksonValue createMapping(@Valid @RequestBody MappingDTO mappingDTO, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                             @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(mappingRepository.save(convertToEntity(mappingDTO)), fieldsIncluded, fieldsExcluded);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMapping(@PathVariable(value = "id") Long id) {
        fr.igr.odin.common.model.Mapping mapping = mappingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping", "id", id));

        mappingRepository.delete(mapping);

        return ResponseEntity.ok().build();
    }

    public MappingJacksonValue convertToDto(Mapping mapping, String fieldsIncluded, String fieldsExcluded) {
        return MappingFilterUtils.applyFilter(modelMapper.map(mapping, MappingDTO.class), "mapping", fieldsIncluded, fieldsExcluded);
    }

    public static MappingJacksonValue convertToDto(List<Mapping> mappings, String fieldsIncluded, String fieldsExcluded) {
        return MappingFilterUtils.applyFilter(modelMapper.map(mappings, new TypeToken<List<MappingDTO>>() {
        }.getType()), "mapping", fieldsIncluded, fieldsExcluded);
    }

    protected fr.igr.odin.common.model.Mapping convertToEntity(MappingDTO mappingDTO) {
        return modelMapper.map(mappingDTO, fr.igr.odin.common.model.Mapping.class);
    }
}
