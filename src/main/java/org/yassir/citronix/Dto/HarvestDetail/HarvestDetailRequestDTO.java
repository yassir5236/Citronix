package org.yassir.citronix.Dto.HarvestDetail;

import jakarta.validation.constraints.NotNull;
import org.yassir.citronix.Embeddable.CompositeKey2;
import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;

public record HarvestDetailRequestDTO(

        @NotNull(message = "quantity is required" )
        double quantity,

        @NotNull(message = "harvestId is required" )
        Long harvestId,

        @NotNull(message = "treeId is required" )
        Long treeId
) {
}
