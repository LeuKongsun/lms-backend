package com.kongsun.leanring.system.features.enrollment;

import com.kongsun.leanring.system.features.payment.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class EnrollmentRequest {
    @NotNull(message = "studentId is required")
    private Long studentId;

    @NotNull(message = "courseIds is required")
    private Set<Long> courseIds;

    @NotNull(message = "date is required")
    private LocalDate date;
    private BigDecimal amount;
    private String receiver;
    private PaymentMethod method;

}
