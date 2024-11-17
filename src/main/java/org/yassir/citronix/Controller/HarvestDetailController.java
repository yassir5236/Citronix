package org.yassir.citronix.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailRequestDTO;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailResponseDTO;
import org.yassir.citronix.Service.IHarvestDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/harvestDetails")
public class HarvestDetailController {

    private final IHarvestDetailService harvestDetailService;

    @Autowired
    public HarvestDetailController(IHarvestDetailService harvestDetailService) {
        this.harvestDetailService = harvestDetailService;
    }

    @PostMapping
    public ResponseEntity<HarvestDetailResponseDTO> createHarvestDetail(@Valid @RequestBody HarvestDetailRequestDTO harvestDetailRequestDTO) {
        HarvestDetailResponseDTO createdHarvestDetail = harvestDetailService.createHarvestDetail(harvestDetailRequestDTO);
        return new ResponseEntity<>(createdHarvestDetail, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDetailResponseDTO> getHarvestDetailById(@PathVariable Long id) {
        HarvestDetailResponseDTO harvestDetail = harvestDetailService.getHarvestDetailById(id);
        return ResponseEntity.ok(harvestDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestDetailResponseDTO> updateHarvestDetail(
            @PathVariable Long id,
            @Valid @RequestBody HarvestDetailRequestDTO harvestDetailRequestDTO) {
        HarvestDetailResponseDTO updatedHarvestDetail = harvestDetailService.updateHarvestDetail(id, harvestDetailRequestDTO);
        return ResponseEntity.ok(updatedHarvestDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvestDetail(@PathVariable Long id) {
        harvestDetailService.deleteHarvestDetail(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<HarvestDetailResponseDTO>> getAllHarvestDetails() {
        List<HarvestDetailResponseDTO> harvestDetails = harvestDetailService.getAllHarvestDetails();
        return ResponseEntity.ok(harvestDetails);
    }




}
