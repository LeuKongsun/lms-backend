package com.kongsun.leanring.system.schedule;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sch_id")
    private Long id;

    @Column(
            name = "sch_description",
            nullable = false
    )
    private String description;

    @Column(
            name = "sch_start_time",
            nullable = false
    )
    private LocalTime startTime;

    @Column(
            name = "sch_end_time",
            nullable = false
    )
    private LocalTime endTime;

    @Column(
            name = "sch_start_date"
    )
    private LocalDate startDate;

    @Column(
            name = "sch_end_date"
    )
    private LocalDate endDate;

    @Column(
            name = "sch_total_time"
    )
    private BigDecimal totalTime;

    @ManyToOne
    @JoinColumn(
            name = "cou_id",
            nullable = false
    )
    private Course course;
}
