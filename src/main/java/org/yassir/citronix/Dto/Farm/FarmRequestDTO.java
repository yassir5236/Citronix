package org.yassir.citronix.Dto.Farm;

import org.yassir.citronix.Dto.Field.EmbeddedFieldDTO;
import org.yassir.citronix.Model.Entity.Field;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record FarmRequestDTO(


        String name,
        String location,
        double totalArea ,
        LocalDate created

) {
}
