package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yassir.citronix.Model.Enum.TreeMaturity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Trees")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


}
