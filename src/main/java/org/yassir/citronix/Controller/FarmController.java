package org.yassir.citronix.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yassir.citronix.Dto.Farm.FarmRequestDTO;
import org.yassir.citronix.Dto.Farm.FarmResponseDTO;
import org.yassir.citronix.Service.IFarmService;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    private final IFarmService farmService;

    @Autowired
    public FarmController(IFarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public ResponseEntity<FarmResponseDTO> createFarm(@Valid @RequestBody FarmRequestDTO farmRequestDTO) {
        FarmResponseDTO createdFarm = farmService.createFarm(farmRequestDTO);
        return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> getFarmById(@PathVariable Long id) {
        FarmResponseDTO farm = farmService.getFarmById(id);
        return ResponseEntity.ok(farm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> updateFarm(
            @PathVariable Long id,
            @Valid @RequestBody FarmRequestDTO farmRequestDTO) {
        FarmResponseDTO updatedFarm = farmService.updateFarm(id, farmRequestDTO);
        return ResponseEntity.ok(updatedFarm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FarmResponseDTO>> getAllFarms() {
        List<FarmResponseDTO> farms = farmService.getAllFarms();
        return ResponseEntity.ok(farms);
    }




}
