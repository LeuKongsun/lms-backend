package com.kongsun.leanring.system.features.enroll;

import com.kongsun.leanring.system.features.course.Course;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class EnrollSpec {
    public static Specification<Enroll> containStudentName(String studentName) {
        return (root, query, criteriaBuilder) -> {
            var firstname = criteriaBuilder.like(
                    criteriaBuilder.lower(criteriaBuilder.lower(root.join("student").get("firstname"))), "%" + studentName.toLowerCase() + "%");

            var lastname = criteriaBuilder.like(
                    criteriaBuilder.lower(criteriaBuilder.lower(root.join("student").get("lastname"))), "%" + studentName.toLowerCase() + "%");

            // combine the predicate or
            return criteriaBuilder.or(firstname, lastname);
        };
    }

    public static Specification<Enroll> equalStatus(String status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);

    }

    public static Specification<Enroll> hasCourseName(String courseName) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("course").get("name")), "%" + courseName.toLowerCase() + "%");
    }



}
