package org.yassir.citronix.Dto.Farm;

import java.time.LocalDate;

public record EmbeddedFarmDTO(
         Long id,

         String name,
         String location,
         double totalArea ,
         LocalDate created
) {
}
