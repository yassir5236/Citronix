package org.yassir.citronix.Dto.Sale;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SaleRequestDTO(

         @NotNull(message = "sale date is required")
         LocalDate saleDate,

         @NotNull(message = "clientName date is required")
         String clientName,

         @NotNull(message = "unitPrice date is required")
         double unitPrice,

         @NotNull(message = "harvestId date is required")
         Long harvestId
) {
}
