package org.yassir.citronix.Dto.Field;

import java.time.LocalDate;

public record FieldRequestDTO(

        String  name,
        double area ,
        LocalDate creationDate,
        Long farmId
) {
}
