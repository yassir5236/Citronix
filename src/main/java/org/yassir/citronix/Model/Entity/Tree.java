package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.yassir.citronix.Model.Enum.TreeMaturity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
@Entity
@Table(name = "Trees")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "PlantingDate required" )
    private LocalDate plantingDate;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isProductive;

    @Enumerated(EnumType.STRING)
    private TreeMaturity treeMaturity;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @OneToMany(mappedBy = "tree" , cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();

    @Transient
    private Double age;

    public int getAge () {
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }
}
