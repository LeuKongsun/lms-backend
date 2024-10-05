package com.kongsun.leanring.system.features.attendance;

import com.kongsun.leanring.system.features.attendance_detail.AttendanceDetailResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AttendanceResponse {
    private Long id;
    private String courseName;
    private LocalDate date;
    private List<AttendanceDetailResponse> attendanceDetails;
}
