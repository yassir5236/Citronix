package org.yassir.citronix.Dto.Tree;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.yassir.citronix.Dto.Field.EmbeddedFieldDTO;

import java.time.LocalDate;

public record TreeRequestDTO(

        @NotNull(message = "plantingDate  is required")
        LocalDate plantingDate,

        @NotNull(message = "fieldId  is required")
        Long fieldId
) {
}
