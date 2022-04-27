package fr.igr.odin.service.interpretation.controller;

import fr.igr.odin.common.dto.RunDTO;
import fr.igr.odin.common.model.Run;
import fr.igr.odin.service.interpretation.exception.ResourceNotFoundException;
import fr.igr.odin.service.interpretation.repository.RunRepository;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static fr.igr.odin.service.interpretation.ApplicationReadyEventListener.modelMapper;

/**
 * Created on 25/03/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping
    public List<RunDTO> getAllLocalisations() {
        return modelMapper.map(runRepository.findAll(), new TypeToken<List<RunDTO>>() {
        }.getType());
    }

    @PostMapping
    public RunDTO createLocalisation(@Valid @RequestBody RunDTO runDTO) {
        return convertToDto(runRepository.save(convertFromDto(runDTO)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteLocalisation(@PathVariable(value = "id") Long id) {
        Run run = runRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Run", "id", id));

        runRepository.delete(run);

        return ResponseEntity.ok().build();
    }

    private RunDTO convertToDto(Run run) {
        return modelMapper.map(run, RunDTO.class);
    }

    private Run convertFromDto(RunDTO runDTO) {
        return modelMapper.map(runDTO, Run.class);
    }
}
