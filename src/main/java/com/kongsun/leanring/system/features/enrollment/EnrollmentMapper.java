package com.kongsun.leanring.system.features.enrollment;

import com.kongsun.leanring.system.features.course.Course;
import com.kongsun.leanring.system.features.course.CourseService;
import com.kongsun.leanring.system.features.student.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = {
                CourseService.class,
                EnrollmentService.class,
                StudentService.class
        }
)
public interface EnrollmentMapper {
    @Mappings({
            @Mapping(target = "student", source = "studentId"),
            @Mapping(target = "courses", source = "courseIds"),
    })
    Enrollment toEnrollment(EnrollmentRequest dto);

    @Mappings({
            @Mapping(target = "studentId", source = "student.id"),
            @Mapping(target = "courseIds", source = "courses", qualifiedByName = "coursesToCourseIds"),
    })
    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);

    @Named("coursesToCourseIds")
    default Set<Long> coursesToCourseIds(Set<Course> courses){
        return courses.stream()
                .map(Course::getId)
                .collect(Collectors.toSet());
    }

}
