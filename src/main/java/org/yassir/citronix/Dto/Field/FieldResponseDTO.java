package org.yassir.citronix.Dto.Field;

import org.yassir.citronix.Dto.Farm.EmbeddedFarmDTO;
import org.yassir.citronix.Dto.Tree.EmbeddedTreeDTO;

import java.time.LocalDate;
import java.util.List;

public record FieldResponseDTO(
        Long id,
        String name,
        double area,
        LocalDate creationDate,
        EmbeddedFarmDTO farm,
        List<EmbeddedTreeDTO> trees
) {
}
