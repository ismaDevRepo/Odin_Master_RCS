package fr.igr.odin.service.variant.controller;

import fr.igr.odin.common.dto.MappingValueDTO;
import fr.igr.odin.common.dto.mapper.MappingFilterUtils;
import fr.igr.odin.common.model.MappingValue;
import fr.igr.odin.service.variant.exception.ResourceNotFoundException;
import fr.igr.odin.service.variant.repository.MappingValueRepository;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static fr.igr.odin.service.variant.ApplicationReadyEventListener.modelMapper;

/**
 * Created on 07/08/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/mappings")
public class MappingValueController {
    private final MappingValueRepository mappingValueRepository;

    public MappingValueController(MappingValueRepository mappingValueRepository) {
        this.mappingValueRepository = mappingValueRepository;
    }

    @GetMapping("mapping-values")
    public MappingJacksonValue getAllMappingValues(@RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                   @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(mappingValueRepository.findAll(), fieldsIncluded, fieldsExcluded);
    }

    @GetMapping("mapping-values/{id}")
    public MappingJacksonValue getMappingValueById(@PathVariable(value = "id") Long id, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                   @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(mappingValueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping Value", "id", id)), fieldsIncluded, fieldsExcluded);
    }

    @PostMapping("mapping-values")
    public MappingJacksonValue createMappingValue(@Valid @RequestBody MappingValueDTO mappingValueDTO, @RequestParam(name = "fieldsIncluded", required = false) String fieldsIncluded,
                                                  @RequestParam(name = "fieldsExcluded", required = false) String fieldsExcluded) {
        return convertToDto(mappingValueRepository.save(convertToEntity(mappingValueDTO)), fieldsIncluded, fieldsExcluded);
    }

    @DeleteMapping("mapping-values/{id}")
    public ResponseEntity<?> deleteMappingValue(@PathVariable(value = "id") Long id) {
        MappingValue mappingValue = mappingValueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping Value", "id", id));

        mappingValueRepository.delete(mappingValue);

        return ResponseEntity.ok().build();
    }

    public MappingJacksonValue convertToDto(MappingValue mappingValue, String fieldsIncluded, String fieldsExcluded) {
        return MappingFilterUtils.applyFilter(modelMapper.map(mappingValue, MappingValueDTO.class), "mappingValue", fieldsIncluded, fieldsExcluded);
    }

    public static MappingJacksonValue convertToDto(List<MappingValue> mappingValues, String fieldsIncluded, String fieldsExcluded) {
        return MappingFilterUtils.applyFilter(modelMapper.map(mappingValues, new TypeToken<List<MappingValueDTO>>() {
        }.getType()), "mappingValue", fieldsIncluded, fieldsExcluded);
    }

    private MappingValue convertToEntity(MappingValueDTO mappingValueDTO) {
        return modelMapper.map(mappingValueDTO, MappingValue.class);
    }
}
