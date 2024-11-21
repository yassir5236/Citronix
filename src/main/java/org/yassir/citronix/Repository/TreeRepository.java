package org.yassir.citronix.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yassir.citronix.Model.Entity.Field;
import org.yassir.citronix.Model.Entity.Sale;
import org.yassir.citronix.Model.Entity.Tree;

import java.util.List;

@Repository
public interface TreeRepository extends CrudRepository<Tree, Long> {
    List<Tree> findByField(Field field);

}
