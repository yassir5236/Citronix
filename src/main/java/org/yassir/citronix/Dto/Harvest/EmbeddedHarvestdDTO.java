package org.yassir.citronix.Dto.Harvest;

import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;

public record EmbeddedHarvestdDTO(
        Long id,

        LocalDate harvestDate,

        Season season
) {
}
