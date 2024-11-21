package org.yassir.citronix.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.yassir.citronix.Embeddable.CompositeKey2;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
@Entity
@Table(name = "HarvestDetails")
public class HarvestDetail {

    @EmbeddedId
    private CompositeKey2 id;

    private double quantity;

    @ManyToOne
    @MapsId("harvestId")
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    @ManyToOne
    @MapsId("treeId")
    @JoinColumn(name = "tree_id")
    private Tree tree;


}
