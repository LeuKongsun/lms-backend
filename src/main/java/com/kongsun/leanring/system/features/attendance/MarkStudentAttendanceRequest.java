package com.kongsun.leanring.system.features.attendance;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MarkStudentAttendanceRequest {
    private String studentCode;
    private Long courseId;
    private LocalDate date;
}
