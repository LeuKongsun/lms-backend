package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.course.Course;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class EnrollmentSpec {
    public static Specification<Enrollment> containStudentName(String studentName) {
        return (root, query, criteriaBuilder) -> {
            var firstname = criteriaBuilder.like(
                    criteriaBuilder.lower(criteriaBuilder.lower(root.join("student").get("firstname"))), "%" + studentName.toLowerCase() + "%");

            var lastname = criteriaBuilder.like(
                    criteriaBuilder.lower(criteriaBuilder.lower(root.join("student").get("lastname"))), "%" + studentName.toLowerCase() + "%");

            // combine the predicate or
            return criteriaBuilder.or(firstname, lastname);
        };
    }

    public static Specification<Enrollment> equalStatus(String status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);

    }
    public static Specification<Enrollment> hasCourseName(String courseName) {
        return (root, query, builder) -> {
            if (courseName == null || courseName.isEmpty()) {
                return builder.conjunction(); // No filtering if courseName is null or empty
            }
            Join<Enrollment, Course> courseJoin = root.join("courses", JoinType.INNER);
            return builder.like(builder.lower(courseJoin.get("name")), "%" + courseName.toLowerCase() + "%");
        };
    }
}
