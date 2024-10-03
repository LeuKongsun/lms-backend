package com.kongsun.leanring.system.enroll;

import com.kongsun.leanring.system.payment.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class EnrollRequest {
    @NotNull(message = "studentId is required")
    private Long studentId;

    @NotNull(message = "courseId is required")
    private Long courseId;

    @NotNull(message = "date is required")
    private LocalDate date;

    private BigDecimal amount;

    private String receiver;

    private PaymentMethod method;

}
