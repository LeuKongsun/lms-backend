package com.kongsun.leanring.system.teacher;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface TeacherMapper {
    Teacher toTeacher(TeacherRequest request);

    TeacherResponse toTeacherResponse(Teacher teacher);
}
