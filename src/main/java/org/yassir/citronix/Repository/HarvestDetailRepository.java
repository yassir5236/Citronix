package org.yassir.citronix.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.citronix.Model.Entity.Field;
import org.yassir.citronix.Model.Entity.HarvestDetail;

@Repository
public interface HarvestDetailRepository extends CrudRepository<HarvestDetail, Long> {
}
