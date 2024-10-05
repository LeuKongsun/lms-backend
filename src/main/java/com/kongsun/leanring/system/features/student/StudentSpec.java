package com.kongsun.leanring.system.features.student;

import org.springframework.data.jpa.domain.Specification;

public class StudentSpec {
    public static Specification<Student> containFirstname(String firstname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstname")), "%" + firstname.toLowerCase() + "%");
    }

    public static Specification<Student> containLastname(String lastname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastname")), "%" + lastname.toLowerCase() + "%");
    }

    public static Specification<Student> containPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Student> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("phone"), phone);
    }

    public static Specification<Student> hasPhoneExcludingId(String phone, Long id) {
        return (root, query, criteriaBuilder) -> {
            if(phone == null || phone.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("phone"), phone),
                    criteriaBuilder.notEqual(root.get("id"), id)
            );
        };
    }

    public static Specification<Student> containGender(String gender) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.upper(root.get("gender")), gender.toUpperCase());
    }

    public static Specification<Student> containEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }

    public static Specification<Student> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Student> hasEmailExcludingId(String email, Long id) {
        return (root, query, criteriaBuilder) -> {
            if(email == null || email.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("email"), email),
                    criteriaBuilder.notEqual(root.get("id"), id)
            );
        };

    }


}
