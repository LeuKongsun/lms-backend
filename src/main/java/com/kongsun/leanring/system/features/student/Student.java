package com.kongsun.leanring.system.features.student;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.features.teacher.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id")
    private Long id;

    @Column(
            name = "stu_firstname",
            nullable = false,
            length = 50
    )
    private String firstname;

    @Column(
            name = "stu_lastname",
            nullable = false,
            length = 50
    )
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "stu_gender",
            nullable = false
    )
    private Gender gender = Gender.MALE;

    @Column(
            name = "stu_phone",
            length = 12,
            unique = true
    )
    private String phone;

    @Column(
            name = "stu_email",
            length = 50
    )
    @Email
    private String email;

    @Column(
            name = "stu_type"
    )
    @Enumerated(EnumType.STRING)
    private StudentType type;

    @Column(
            name = "stu_position",
            length = 20
    )
    private String position;

    @Column(
            name = "stu_from",
            length = 20
    )
    private String from;

}
