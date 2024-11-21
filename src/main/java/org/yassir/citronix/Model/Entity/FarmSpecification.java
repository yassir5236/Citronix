package org.yassir.citronix.Model.Entity;

import org.springframework.data.jpa.domain.Specification;
import org.yassir.citronix.Model.Entity.Farm;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class FarmSpecification {

    public static Specification<Farm> searchFarms(String name, String location, Double minArea) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            if (location != null && !location.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("location"), location));
            }

            if (minArea != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("totalArea"), minArea));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
