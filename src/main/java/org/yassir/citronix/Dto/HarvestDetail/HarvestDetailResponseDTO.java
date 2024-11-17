package org.yassir.citronix.Dto.HarvestDetail;

import org.yassir.citronix.Dto.Harvest.EmbeddedHarvestdDTO;
import org.yassir.citronix.Dto.Tree.EmbeddedTreeDTO;
import org.yassir.citronix.Embeddable.CompositeKey2;
import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;
import java.util.List;

public record HarvestDetailResponseDTO(

        CompositeKey2 id,

        double quantity,
        EmbeddedTreeDTO tree,
        EmbeddedHarvestdDTO harvest
) {
}
