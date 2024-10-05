package com.kongsun.leanring.system.features.payment;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class PaymentSpec {
    public static Specification<Payment> containFirstname(String firstname) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> enroll = root.join("enroll", JoinType.INNER);
            Join<Object, Object> student = enroll.join("student", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(student.get("firstname")), "%" + firstname.toLowerCase() + "%");
        };
    }

    public static Specification<Payment> containLastname(String lastname) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> enroll = root.join("enroll", JoinType.INNER);
            Join<Object, Object> student = enroll.join("student", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(student.get("lastname")), "%" + lastname.toLowerCase() + "%");
        };
    }

    public static Specification<Payment> hasStudentName(String firstname, String lastname) {
        return Specification.where(containFirstname(firstname)).and(containLastname(lastname));
    }

}
