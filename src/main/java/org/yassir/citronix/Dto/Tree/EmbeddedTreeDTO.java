package org.yassir.citronix.Dto.Tree;

import java.time.LocalDate;

public record EmbeddedTreeDTO(
         Long id,
         LocalDate plantingDate
) {
}
