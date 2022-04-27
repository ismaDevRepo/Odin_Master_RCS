package fr.igr.odin.service.variant.controller;

import fr.igr.odin.common.dto.ChromosomeDTO;
import fr.igr.odin.common.model.Chromosome;
import fr.igr.odin.service.variant.repository.ChromosomeRepository;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/repository/chromosomes")
public class ChromosomeController {
    private final ChromosomeRepository chromosomeRepository;

    public ChromosomeController(ChromosomeRepository chromosomeRepository) {
        this.chromosomeRepository = chromosomeRepository;
    }

    @GetMapping
    public List<ChromosomeDTO> getAllChromosomes() {
        //return EntityToDTOMapper.INSTANCE.chromosomesToChromosomeDTOs(chromosomeRepository.findAll());
        return modelMapper.map(chromosomeRepository.findAll(), new TypeToken<List<ChromosomeDTO>>() {
        }.getType());
    }

    @GetMapping("/distinct-libelle")
    public List<String> getChromosomesDistinctLibelle() {
        return chromosomeRepository.getDistinctLibelle();
    }

    private ChromosomeDTO convertToDto(Chromosome chromosome) {
        return modelMapper.map(chromosome, ChromosomeDTO.class);
    }

    private Chromosome convertToEntity(ChromosomeDTO chromosomeDTO) {
        return modelMapper.map(chromosomeDTO, Chromosome.class);
    }
}
