package com.kongsun.leanring.system.teacher;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TeacherRequest {
    @NotNull(message = "code is required")
    private String code;

    @NotNull(message = "firstname is required")
    private String firstname;

    @NotNull(message = "lastname is required")
    private String lastname;

    @NotNull(message = "gender is required")
    private Gender gender = Gender.MALE;


    private BigDecimal salary = BigDecimal.ZERO;

    private String email;

    private String phone;

    private String address;

    @NotNull(message = "hireDate is required")
    private LocalDate hireDate;

    private boolean stopWork = false;

}
