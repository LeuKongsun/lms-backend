package com.kongsun.leanring.system.enroll;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.course.Course;
import com.kongsun.leanring.system.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "enrolls")
public class Enroll extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enr_id")
    private Long id;

    @Column(
            name = "enr_price",
            nullable = false
    )
    private BigDecimal price; // Store the course price at the time of enroll

    @Column(
            name = "enr_remain",
            nullable = false
    )
    private BigDecimal remain;


    @Column(
            name = "enr_status",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private EnrollStatus status;

    @ManyToOne
    @JoinColumn(
            name = "stu_id",
            nullable = false
    )
    private Student student;

    @Column(
            name = "enr_date",
            nullable = false
    )
    private LocalDate date;

    @ManyToOne
    @JoinColumn(
            name = "cou_id",
            nullable = false
    )
    private Course course;

}
