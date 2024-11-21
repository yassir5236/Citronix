package org.yassir.citronix.Dto.Sale;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SaleRequestDTO(

         @NotNull(message = "sale date is required")
         LocalDate saleDate,

         @NotNull(message = "clientName  is required")
         String clientName,

         @NotNull(message = "unitPrice  is required")
         double unitPrice,

         @NotNull(message = "Wanted quantity  is required")
         double wantedQuantity,

         @NotNull(message = "harvestId  is required")
         Long harvestId
) {
}
