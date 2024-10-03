package com.kongsun.leanring.system.teacher;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TeacherResponse {
    private Long id;

    private String code;

    private String firstname;

    private String lastname;

    private Gender gender;

    private BigDecimal salary;

    private String email;

    private String phone;

    private String address;

    private LocalDate hireDate;
}
