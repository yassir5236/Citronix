package org.yassir.citronix.Dto.Sale;

import java.time.LocalDate;

public record EmbeddedSaleDTO(
         Long id,
         LocalDate saleDate,
         String clientName,
         double unitPrice
) {
}
