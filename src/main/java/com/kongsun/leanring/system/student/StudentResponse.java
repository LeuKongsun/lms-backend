package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.teacher.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private Gender gender;
    private String phone;
    private String email;
    private StudentType type;
    private String position;
    private String from;

}
