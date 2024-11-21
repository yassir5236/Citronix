package org.yassir.citronix.Dto.Sale;

import org.yassir.citronix.Dto.Harvest.EmbeddedHarvestdDTO;

import java.time.LocalDate;

public record SaleResponseDTO(
         Long id,
         LocalDate saleDate,
         String clientName,
         double unitPrice,
         double wantedQuantity,
         EmbeddedHarvestdDTO harvest
) {
}
