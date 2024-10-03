package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.course.CourseService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                CourseService.class
        }
)
public interface AttendanceMapper {
    @Mapping(target = "course", source = "courseId")
    Attendance toAttendance(AttendanceRequest request);

    @Mappings({
            @Mapping(target = "courseName", source = "course.name")
    })
    AttendanceResponse toAttendanceResponse(Attendance attendance);
}
