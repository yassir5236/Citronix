package org.yassir.citronix.Dto.Harvest;

import jakarta.validation.constraints.NotNull;
import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;

public record HarvestRequestDTO(


//        @NotNull(message = "HarvestDate is required")
//        LocalDate harvestDate,

        @NotNull(message = "HarvestDate is required")
        LocalDate harvestDate,

        @NotNull(message = "Season is required")
        Season season
) {
}
