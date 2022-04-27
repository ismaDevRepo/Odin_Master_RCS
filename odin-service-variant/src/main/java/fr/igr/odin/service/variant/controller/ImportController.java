package fr.igr.odin.service.variant.controller;

import fr.igr.odin.common.dto.ImportDTO;
import fr.igr.odin.common.model.Import;
import fr.igr.odin.service.variant.exception.ResourceNotFoundException;
import fr.igr.odin.service.variant.repository.ImportRepository;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static fr.igr.odin.service.variant.ApplicationReadyEventListener.modelMapper;

/**
 * Created on 17/07/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/imports")
public class ImportController {
    private final ImportRepository importRepository;

    public ImportController(ImportRepository importRepository) {
        this.importRepository = importRepository;
    }

    @GetMapping
    public List<ImportDTO> getAllImports() {
        return modelMapper.map(importRepository.findAll(), new TypeToken<List<ImportDTO>>() {
        }.getType());
    }

    @GetMapping("{id}")
    public ImportDTO getImportById(@PathVariable(value = "id") Long id) {
        return convertToDto(importRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Import", "id", id)));
    }

    @PostMapping
    public ImportDTO createImport(@Valid @RequestBody ImportDTO importDTO) {
        Import importSaved = importRepository.save(convertToEntity(importDTO));
        importRepository.refresh(importSaved);
        return convertToDto(importSaved);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteImport(@PathVariable(value = "id") Long id) {
        Import anImport = importRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Import", "id", id));

        importRepository.delete(anImport);

        return ResponseEntity.ok().build();
    }

    private ImportDTO convertToDto(Import anImport) {
        return modelMapper.map(anImport, ImportDTO.class);
    }

    private Import convertToEntity(ImportDTO importDTO) {
        return modelMapper.map(importDTO, Import.class);
    }
}
