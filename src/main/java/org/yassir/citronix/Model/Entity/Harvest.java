package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.engine.internal.Cascade;
import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
@Entity
@Table(name = "Harvests")
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message ="HarvestDate date required")
    private LocalDate harvestDate;
    @Enumerated(EnumType.STRING)
    private Season season;

    @Column(nullable = false, columnDefinition = "float default 0.0")
    private double totalQuantity;

    @Transient
    private double totalIncome;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

}
