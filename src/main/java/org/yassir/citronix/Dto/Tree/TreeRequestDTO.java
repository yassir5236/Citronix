package org.yassir.citronix.Dto.Tree;

import org.yassir.citronix.Dto.Field.EmbeddedFieldDTO;

import java.time.LocalDate;

public record TreeRequestDTO(

        LocalDate plantingDate,
        Long fieldId
) {
}
