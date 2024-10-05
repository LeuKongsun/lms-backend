package com.kongsun.leanring.system.features.category;

import org.springframework.data.jpa.domain.Specification;

public class CategorySpec {
    public static Specification<Category> containName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%");
    }
}
