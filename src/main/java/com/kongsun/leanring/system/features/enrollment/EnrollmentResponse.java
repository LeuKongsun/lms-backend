package com.kongsun.leanring.system.features.enrollment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class EnrollmentResponse {
    private long id;
    private Long studentId;
    private Set<Long> courseIds;
    private BigDecimal total;
    private BigDecimal remain;
    private EnrollmentStatus status;
    private LocalDate date;
}
