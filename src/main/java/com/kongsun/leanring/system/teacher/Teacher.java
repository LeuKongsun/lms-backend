package com.kongsun.leanring.system.teacher;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private Long id;

    @Column(
            name = "tea_code",
            length = 50
    )
    private String code;

    @Column(
            name = "tea_firstname",
            nullable = false,
            length = 50
    )
    private String firstname;

    @Column(
            name = "tea_lastname",
            nullable = false,
            length = 7
    )
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "tea_gender",
            nullable = false
    )
    private Gender gender = Gender.MALE;

    @Column(
            name = "tea_salary",
            nullable = false
    )
    private BigDecimal salary = BigDecimal.ZERO;

    @Column(
            name = "tea_hire_date"
    )
    private LocalDate hireDate;

    @Column(
            name = "tea_email"
    )
    @Email
    private String email;

    @Column(
            name = "tea_phone",
            length = 12
    )
    private String phone;

    @Column(
            name = "tea_address"
    )
    private String address;

    @Column(
            name = "tea_stop_work"
    )
    private boolean stopWork = false;

}
