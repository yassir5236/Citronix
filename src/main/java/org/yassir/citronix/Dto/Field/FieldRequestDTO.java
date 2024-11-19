package org.yassir.citronix.Dto.Field;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FieldRequestDTO(

        @NotBlank(message = "Field name cannot be empty")
        String  name,

        @Min(value = 0, message = "Area cannot be negative")
        @DecimalMin(value = "0.1", message = "Field area must be at least 0.1 hectare (1000 m²)")
        double area ,

        @NotNull(message ="Creation date required")
        LocalDate creationDate,

        @NotNull(message ="Farm id required")
        Long farmId
) {
}
