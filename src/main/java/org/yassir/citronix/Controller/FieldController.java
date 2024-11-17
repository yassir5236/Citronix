package org.yassir.citronix.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yassir.citronix.Dto.Field.FieldRequestDTO;
import org.yassir.citronix.Dto.Field.FieldResponseDTO;
import org.yassir.citronix.Service.IFieldService;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    private final IFieldService fieldService;

    @Autowired
    public FieldController(IFieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping
    public ResponseEntity<FieldResponseDTO> createField(@Valid @RequestBody FieldRequestDTO fieldRequestDTO) {
        FieldResponseDTO createdField = fieldService.createField(fieldRequestDTO);
        return new ResponseEntity<>(createdField, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> getFieldById(@PathVariable Long id) {
        FieldResponseDTO field = fieldService.getFieldById(id);
        return ResponseEntity.ok(field);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> updateField(
            @PathVariable Long id,
            @Valid @RequestBody FieldRequestDTO fieldRequestDTO) {
        FieldResponseDTO updatedField = fieldService.updateField(id, fieldRequestDTO);
        return ResponseEntity.ok(updatedField);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FieldResponseDTO>> getAllFields() {
        List<FieldResponseDTO> fields = fieldService.getAllFields();
        return ResponseEntity.ok(fields);
    }




}
