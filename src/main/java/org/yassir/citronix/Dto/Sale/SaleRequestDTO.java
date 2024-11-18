package org.yassir.citronix.Dto.Sale;

import java.time.LocalDate;

public record SaleRequestDTO(

         LocalDate saleDate,
         String clientName,
         double unitPrice,
         Long harvestId
) {
}
