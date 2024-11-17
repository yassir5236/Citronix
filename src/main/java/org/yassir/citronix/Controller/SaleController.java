package org.yassir.citronix.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yassir.citronix.Dto.Sale.SaleRequestDTO;
import org.yassir.citronix.Dto.Sale.SaleResponseDTO;
import org.yassir.citronix.Service.ISaleService;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final ISaleService saleService;

    @Autowired
    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@Valid @RequestBody SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO createdSale = saleService.createSale(saleRequestDTO);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        SaleResponseDTO sale = saleService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> updateSale(
            @PathVariable Long id,
            @Valid @RequestBody SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO updatedSale = saleService.updateSale(id, saleRequestDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        List<SaleResponseDTO> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }




}
