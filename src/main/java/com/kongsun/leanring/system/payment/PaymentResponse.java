package com.kongsun.leanring.system.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponse {
    private Long id;
    private Long enrollId;
    private BigDecimal amount;
    private LocalDate date;
    private PaymentMethod method;
    private String receiver;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

