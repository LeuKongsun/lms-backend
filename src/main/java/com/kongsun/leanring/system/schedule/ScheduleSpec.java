package com.kongsun.leanring.system.schedule;

import com.kongsun.leanring.system.course.Course;
import com.kongsun.leanring.system.enrollment.Enrollment;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ScheduleSpec {
    public static Specification<Schedule> hasCourseName(String courseName) {
        return (root, query, builder) -> {
            if (courseName == null || courseName.isEmpty()) {
                return builder.conjunction(); // No filtering if courseName is null or empty
            }
            Join<Enrollment, Course> courseJoin = root.join("course");
            return builder.like(builder.lower(courseJoin.get("name")), "%" + courseName.toLowerCase() + "%");
        };
    }
}
