package com.kongsun.leanring.system.enroll;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EnrollResponse {
    private long id;
    private Long studentId;
    private Long courseId;
    private BigDecimal total;
    private BigDecimal remain;
    private Enroll status;
    private LocalDate date;
}
