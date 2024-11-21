package org.yassir.citronix.Dto.Harvest;

import org.yassir.citronix.Dto.Sale.EmbeddedSaleDTO;

import java.util.List;

public record HarvestTotalIncomeDTO(
        EmbeddedHarvestdDTO harvest,
        List<EmbeddedSaleDTO> sales,
        double totalIncome
) {
}



