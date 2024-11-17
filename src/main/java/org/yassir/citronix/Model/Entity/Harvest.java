package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;
import org.yassir.citronix.Model.Enum.Season;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Harvests")
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate harvestDate;
    @Enumerated(EnumType.STRING)
    private Season season;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

}
