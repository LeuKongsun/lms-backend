package com.kongsun.leanring.system.course;

import com.kongsun.leanring.system.category.CategoryService;
import com.kongsun.leanring.system.teacher.Teacher;
import com.kongsun.leanring.system.teacher.TeacherService;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryService.class,
                TeacherService.class
        }
)

public interface CourseMapper {
    @Mappings({
            @Mapping(target = "category", source = "categoryId"),
            @Mapping(target = "teacher", source = "teacherId", qualifiedByName = "mapTeacher")
    })
    Course toCourse(CourseRequest request);
    @Mappings({
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "teacherId", source = "teacher.id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    })
    CourseResponse toCourseResponse(Course course);

    @Named("mapTeacher")
    default Teacher mapTeacher(Long teacherId) {
        if (teacherId == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        return teacher;
    }
}
