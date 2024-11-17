package org.yassir.citronix.Dto.Harvest;

import org.yassir.citronix.Dto.Tree.EmbeddedTreeDTO;
import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;
import java.util.List;

public record HarvestResponseDTO(
        Long id,

        LocalDate harvestDate,

        Season season,
        List<EmbeddedTreeDTO> trees,
        List<EmbeddedHarvestdDTO> harvests
) {
}
