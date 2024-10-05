package com.kongsun.leanring.system.features.teacher;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface TeacherMapper {
    Teacher toTeacher(TeacherRequest request);

    TeacherResponse toTeacherResponse(Teacher teacher);
}
