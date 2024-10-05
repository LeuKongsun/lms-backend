package com.kongsun.leanring.system.features.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentDTO {
    @NotNull(message = "enrollId is required")
    private Long enrollId;

    @PositiveOrZero(message = "amount must be positive or zero")
    @Min(value = 1L, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "date is required")
    private LocalDate date;

    @NotNull(message = "payment method is required")
    private PaymentMethod method;
    @NotNull(message = "receiver is required")
    private String receiver;
}
