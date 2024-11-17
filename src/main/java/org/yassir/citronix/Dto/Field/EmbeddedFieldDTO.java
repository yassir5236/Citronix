package org.yassir.citronix.Dto.Field;

import java.time.LocalDate;

public record EmbeddedFieldDTO(
         Long id,
         String  name,
         double area ,
         LocalDate creationDate

) {
}
