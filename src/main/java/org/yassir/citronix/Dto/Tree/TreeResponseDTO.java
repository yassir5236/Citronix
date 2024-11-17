package org.yassir.citronix.Dto.Tree;

import org.yassir.citronix.Dto.Field.EmbeddedFieldDTO;
import org.yassir.citronix.Dto.Field.FieldResponseDTO;
import org.yassir.citronix.Dto.HarvestDetail.EmbeddedHarvestDetailDTO;
import org.yassir.citronix.Dto.HarvestDetail.HarvestDetailResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record TreeResponseDTO(
        Long id,

        LocalDate plantingDate,

//        EmbeddedFieldDTO field,
        FieldResponseDTO field,

        List<EmbeddedHarvestDetailDTO> harvestDetails

        ) {
}
