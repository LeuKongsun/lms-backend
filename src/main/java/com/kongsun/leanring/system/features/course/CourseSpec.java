package com.kongsun.leanring.system.features.course;

import org.springframework.data.jpa.domain.Specification;

public class CourseSpec {
    public static Specification<Course> containName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Course> containDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }


    public static Specification<Course> containCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(criteriaBuilder.lower(root.join("category").get("name"))), "%" + categoryName.toLowerCase() + "%");
    }

}
