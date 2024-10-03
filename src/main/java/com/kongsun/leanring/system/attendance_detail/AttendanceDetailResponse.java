package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.attendance.AttendanceStatus;
import com.kongsun.leanring.system.student.StudentResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AttendanceDetailResponse {
    private Long id;
    private StudentResponse student;
    private AttendanceStatus status;
    private LocalDate date;
}
