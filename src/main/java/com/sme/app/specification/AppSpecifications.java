package com.sme.app.specification;

import org.springframework.data.jpa.domain.Specification;

public class AppSpecifications {

    public static <E> Specification<E> propertyEqual(String property, String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.equal(root.get(property), value);
            }
        };
    }

    public static <E> Specification<E> propertyLike(String property, String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(property)), "%" + value.toLowerCase() + "%");
            }
        };
    }

}
