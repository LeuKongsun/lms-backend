package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.attendance.Attendance;
import com.kongsun.leanring.system.course.Course;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AttendanceDetailSpec {
    public static Specification<AttendanceDetail> containCourseId(Long courseId) {
        return (root, query, criteriaBuilder) -> {
            Join<AttendanceDetail, Attendance> attendanceJoin = root.join("attendance", JoinType.INNER);
            Join<Attendance, Course> courseJoin = attendanceJoin.join("course", JoinType.INNER);

            return criteriaBuilder.equal(courseJoin.get("id"), courseId);
        };

    }

    public static Specification<AttendanceDetail> betweenDate(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("date"), startDate, endDate);
    }
}
