package org.yassir.citronix.Dto.HarvestDetail;

import org.yassir.citronix.Embeddable.CompositeKey2;
import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;

public record HarvestDetailRequestDTO(

        double quantity,
        Long harvestId,
        Long treeId
) {
}
