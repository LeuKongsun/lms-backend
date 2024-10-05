package com.kongsun.leanring.system.features.payment;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.features.enroll.Enroll;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enr_id")
    private Enroll enroll;


    @Column(
            name = "pay_amount",
            nullable = false
    )
    private BigDecimal amount;

    @Column(
            name = "pay_method",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(
            name = "pay_receiver",
            length = 20
    )
    private String receiver;

    @Column(
            name = "pay_date",
            nullable = false
    )
    private LocalDate date;
}
