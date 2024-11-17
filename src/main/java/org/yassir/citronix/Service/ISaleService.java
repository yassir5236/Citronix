package org.yassir.citronix.Service;

import org.yassir.citronix.Dto.Sale.SaleRequestDTO;
import org.yassir.citronix.Dto.Sale.SaleResponseDTO;

import java.util.List;

public interface ISaleService {
    SaleResponseDTO createSale(SaleRequestDTO saleRequestDTO);

    SaleResponseDTO getSaleById(Long saleId);

    SaleResponseDTO updateSale(Long id, SaleRequestDTO saleRequestDTO);

    List<SaleResponseDTO> getAllSales();

    void deleteSale(Long saleId);
}
