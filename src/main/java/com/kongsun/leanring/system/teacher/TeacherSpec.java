package com.kongsun.leanring.system.teacher;

import com.kongsun.leanring.system.student.Student;
import org.springframework.data.jpa.domain.Specification;

public class TeacherSpec {
    public static Specification<Teacher> containFirstname(String firstname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstname.toLowerCase() + "%");
    }

    public static Specification<Teacher> containLastname(String lastname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastname.toLowerCase() + "%");
    }

    public static Specification<Teacher> hasGender(String gender) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.upper(root.get("gender")), gender.toUpperCase());
    }

    public static Specification<Teacher> isStopWork(boolean stopWork) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("stopWork"), stopWork);
    }

    public static Specification<Teacher> hasPhoneExcludingId(String phone, Long id) {
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

    public static Specification<Teacher> hasEmailExcludingId(String email, Long id) {
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

    public static Specification<Teacher> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("phone"), phone);
    }

    public static Specification<Teacher> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email);
    }

}
