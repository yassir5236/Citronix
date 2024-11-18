
package org.yassir.citronix.Model.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field name cannot be empty")
    private String name;

    @Min(value = 0, message = "Area cannot be negative")
    @DecimalMin(value = "0.1", message = "Field area must be at least 0.1 hectare (1000 m²)")
    private double area;

    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "Farm_id")
    private Farm farm;

    @OneToMany(mappedBy = "field" , cascade = CascadeType.ALL)
    private List<Tree> trees =new ArrayList<>();
}
