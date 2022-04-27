package fr.igr.odin.service.variant.controller;

import fr.igr.odin.common.dto.GenomeBuildDTO;
import fr.igr.odin.common.model.GenomeBuild;
import fr.igr.odin.service.variant.repository.GenomeBuildRepository;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static fr.igr.odin.service.variant.ApplicationReadyEventListener.modelMapper;

/**
 * Created on 11/07/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/repository/genome-builds")
public class GenomeBuildController {
    private final GenomeBuildRepository genomeBuildRepository;

    public GenomeBuildController(GenomeBuildRepository genomeBuildRepository) {
        this.genomeBuildRepository = genomeBuildRepository;
    }

    @GetMapping
    public List<GenomeBuildDTO> getAllGenomeBuilds() {
        return modelMapper.map(genomeBuildRepository.findAll(), new TypeToken<List<GenomeBuildDTO>>() {
        }.getType());
    }

    private GenomeBuildDTO convertToDto(GenomeBuild genomeBuild) {
        return modelMapper.map(genomeBuild, GenomeBuildDTO.class);
    }

    private GenomeBuild convertToEntity(GenomeBuildDTO genomeBuildDTO) {
        return modelMapper.map(genomeBuildDTO, GenomeBuild.class);
    }
}
