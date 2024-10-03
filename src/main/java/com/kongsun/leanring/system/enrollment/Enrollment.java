package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.course.Course;
import com.kongsun.leanring.system.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "enrollments")
public class Enrollment extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enr_id")
    private Long id;

    @Column(
            name = "enr_total",
            nullable = false
    )
    private BigDecimal total;

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
    private EnrollmentStatus status;

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

    @ManyToMany
    @JoinTable(
            name = "enrollment_details",
            joinColumns = @JoinColumn(name = "enr_id"),
            inverseJoinColumns = @JoinColumn(name = "cou_id")
    )
    private Set<Course> courses = new HashSet<>();

}
