package org.yassir.citronix.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.citronix.Model.Entity.Farm;
import org.yassir.citronix.Model.Entity.Field;

@Repository
public interface FieldRepository extends CrudRepository<Field, Long> {
}
