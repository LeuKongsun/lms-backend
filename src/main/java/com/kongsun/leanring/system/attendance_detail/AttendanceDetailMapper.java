package com.kongsun.leanring.system.attendance_detail;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface AttendanceDetailMapper {
    AttendanceDetailResponse toAttendanceDetailResponse(AttendanceDetail attendanceDetail);

    AttendanceDetail toAttendanceDetail(AttendanceDetailRequest request);
}
