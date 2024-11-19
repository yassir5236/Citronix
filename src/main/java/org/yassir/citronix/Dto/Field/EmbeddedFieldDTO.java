package org.yassir.citronix.Dto.Field;

import org.yassir.citronix.Dto.Tree.EmbeddedTreeDTO;

import java.time.LocalDate;
import java.util.List;

public record EmbeddedFieldDTO(
         Long id,
         String  name,
         double area ,
         LocalDate creationDate,
         List<EmbeddedTreeDTO> trees

) {
}
