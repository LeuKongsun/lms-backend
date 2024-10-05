package com.kongsun.leanring.system.features.schedule;

import com.kongsun.leanring.system.features.course.Course;
import com.kongsun.leanring.system.features.enrollment.Enrollment;
import jakarta.persistence.criteria.Join;
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
