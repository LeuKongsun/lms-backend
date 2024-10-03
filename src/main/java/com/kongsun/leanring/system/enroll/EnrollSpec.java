package com.kongsun.leanring.system.enroll;

import com.kongsun.leanring.system.course.Course;
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
        return (root, query, builder) -> {
            if (courseName == null || courseName.isEmpty()) {
                return builder.conjunction(); // No filtering if courseName is null or empty
            }
            Join<Enroll, Course> courseJoin = root.join("courses", JoinType.INNER);
            return builder.like(builder.lower(courseJoin.get("name")), "%" + courseName.toLowerCase() + "%");
        };
    }
}
