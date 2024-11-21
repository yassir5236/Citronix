package org.yassir.citronix.Repository;

import org.antlr.v4.runtime.tree.Trees;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.citronix.Model.Entity.Harvest;
import org.yassir.citronix.Model.Entity.HarvestDetail;
import org.yassir.citronix.Model.Entity.Tree;

import java.util.List;

@Repository
public interface HarvestRepository extends CrudRepository<Harvest, Long> {

}
