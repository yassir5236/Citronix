package org.yassir.citronix.Embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record CompositeKey2(
        @Column(name = "tree_id") Long treeId,
        @Column(name = "harvest_id") Long harvestId
) {
}
