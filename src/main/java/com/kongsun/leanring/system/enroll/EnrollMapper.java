package com.kongsun.leanring.system.enroll;

import com.kongsun.leanring.system.course.CourseService;
import com.kongsun.leanring.system.student.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                CourseService.class,
                EnrollService.class,
                StudentService.class
        }
)
public interface EnrollMapper {
    @Mappings({
            @Mapping(target = "student", source = "studentId"),
            @Mapping(target = "course", source = "courseId"),
    })
    Enroll toEnroll(EnrollRequest request);

    @Mappings({
            @Mapping(target = "studentId", source = "student.id"),
            @Mapping(target = "courseId", source = "course.id"),
    })
    EnrollResponse toEnrollResponse(Enroll enroll);

}
