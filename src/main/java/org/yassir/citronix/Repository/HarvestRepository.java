package org.yassir.citronix.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.citronix.Model.Entity.Harvest;
import org.yassir.citronix.Model.Entity.HarvestDetail;

@Repository
public interface HarvestRepository extends CrudRepository<Harvest, Long> {
}
