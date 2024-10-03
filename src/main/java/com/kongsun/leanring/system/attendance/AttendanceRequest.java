package com.kongsun.leanring.system.attendance;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Getter
@Setter
public class AttendanceRequest {
    private Long courseId;
    private LocalDate date;
    private Map<AttendanceStatus, List<Long>> attendance;
}
