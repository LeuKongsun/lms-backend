package com.kongsun.leanring.system.features.teacher;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TeacherDTO {

    @NotNull(message = "code is required")
    private String code;

    @NotNull(message = "firstname is required")
    private String firstname;

    @NotNull(message = "lastname is required")
    private String lastname;


    private Gender gender = Gender.MALE;


    private BigDecimal salary = BigDecimal.ZERO;


    private LocalDate hireDate;

    private boolean stopWork = false;

}
