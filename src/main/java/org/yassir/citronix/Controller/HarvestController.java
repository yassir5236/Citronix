package org.yassir.citronix.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yassir.citronix.Dto.Harvest.HarvestRequestDTO;
import org.yassir.citronix.Dto.Harvest.HarvestResponseDTO;
import org.yassir.citronix.Dto.Harvest.HarvestTotalIncomeDTO;
import org.yassir.citronix.Service.IHarvestService;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController {

    private final IHarvestService harvestService;

    @Autowired
    public HarvestController(IHarvestService harvestService) {
        this.harvestService = harvestService;
    }

    @PostMapping
    public ResponseEntity<HarvestResponseDTO> createHarvest(@Valid @RequestBody HarvestRequestDTO harvestRequestDTO) {
        HarvestResponseDTO createdHarvest = harvestService.createHarvest(harvestRequestDTO);
        return new ResponseEntity<>(createdHarvest, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> getHarvestById(@PathVariable Long id) {
        HarvestResponseDTO harvest = harvestService.getHarvestById(id);
        return ResponseEntity.ok(harvest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> updateHarvest(
            @PathVariable Long id,
            @Valid @RequestBody HarvestRequestDTO harvestRequestDTO) {
        HarvestResponseDTO updatedHarvest = harvestService.updateHarvest(id, harvestRequestDTO);
        return ResponseEntity.ok(updatedHarvest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        harvestService.deleteHarvest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<HarvestResponseDTO>> getAllHarvests() {
        List<HarvestResponseDTO> harvests = harvestService.getAllHarvests();
        return ResponseEntity.ok(harvests);
    }


    @GetMapping("/{id}/totalRevenue")
    public ResponseEntity<HarvestTotalIncomeDTO> getHarvestIncome(@PathVariable("id") Long harvestId) {
        HarvestTotalIncomeDTO totalIncomeDTO = harvestService.getHarvestTotalIncome(harvestId);
        return ResponseEntity.ok(totalIncomeDTO);
    }



}
