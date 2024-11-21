package org.yassir.citronix.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.citronix.Model.Entity.Farm;

@Repository
public interface FarmRepository extends CrudRepository<Farm, Long>, JpaSpecificationExecutor<Farm> {
}
