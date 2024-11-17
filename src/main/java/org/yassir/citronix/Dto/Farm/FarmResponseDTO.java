package org.yassir.citronix.Dto.Farm;

import org.yassir.citronix.Dto.Field.EmbeddedFieldDTO;

import java.time.LocalDate;
import java.util.List;

public record FarmResponseDTO(
        Long id,

        String name,
        String location,
        double totalArea ,
        LocalDate created,
        List<EmbeddedFieldDTO> fields

) {
}
