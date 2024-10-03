package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.teacher.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
    @NotNull(message = "firstname is required")
    private String firstname;

    @NotNull(message = "lastname is required")
    private String lastname;

    @NotNull(message = "gender is required")
    private Gender gender;

    private String phone;

    private String email;

    private StudentType type;

    private String position;

    private String from;

}
