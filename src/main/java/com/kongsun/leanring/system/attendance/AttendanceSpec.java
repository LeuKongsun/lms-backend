package com.kongsun.leanring.system.attendance;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AttendanceSpec {
    public static Specification<Attendance> betweenDate(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("date"), startDate, endDate);
    }

    public static Specification<Attendance> containCourseId(Long courseId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("course").get("id"), courseId);
    }
}
