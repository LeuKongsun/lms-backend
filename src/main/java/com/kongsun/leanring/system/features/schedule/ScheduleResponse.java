package com.kongsun.leanring.system.features.schedule;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ScheduleResponse {
    private Long id;

    private String description;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long courseId;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal totalTime;
}
