package org.yassir.citronix.Dto.Farm;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.yassir.citronix.Dto.Field.EmbeddedFieldDTO;
import org.yassir.citronix.Model.Entity.Field;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record FarmRequestDTO(

        @NotBlank(message = "Farm name cannot be empty")
        String name,
        @NotBlank(message = "Farm location cannot be empty")
        String location,
        @Min(value = 0, message = "Total area cannot be negative")
        @DecimalMin(value = "1.0", message = "Farm area must be at least 1 hectare")
        double totalArea ,
        @NotNull(message ="Creation date required")
        LocalDate created

) {
}
