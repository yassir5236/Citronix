package org.yassir.citronix.Dto.Field;

import org.yassir.citronix.Dto.Farm.EmbeddedFarmDTO;

import java.time.LocalDate;

public record FieldResponseDTO(
        Long id,
        String  name,
        double area ,
        LocalDate creationDate,
        EmbeddedFarmDTO farm
) {
}
