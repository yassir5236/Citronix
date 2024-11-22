

package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Farms")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Farm name cannot be empty")
    private String name;

    @NotBlank(message = "Farm location cannot be empty")
    private String location;

    @Min(value = 0, message = "Total area cannot be negative")
    @DecimalMin(value = "0.2", message = "Farm area must be at least 0.2 hectare")
    private double totalArea;

    @NotNull(message ="Creation date required")
    private LocalDate created;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Field> fields = new ArrayList<>();
}
