package com.kongsun.leanring.system.features.attendance;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.features.course.Course;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "attendances")
public class Attendance extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cou_id")
    private Course course;

    @Column(name = "att_date")
    private LocalDate date;
}
