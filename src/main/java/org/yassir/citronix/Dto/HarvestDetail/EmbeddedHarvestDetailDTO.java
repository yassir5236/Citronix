package org.yassir.citronix.Dto.HarvestDetail;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.yassir.citronix.Embeddable.CompositeKey2;
import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;

public record EmbeddedHarvestDetailDTO(


        CompositeKey2 id,

        double quantity

        ) {
}
