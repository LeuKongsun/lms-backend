package com.kongsun.leanring.system.features.attendance_detail;

import com.kongsun.leanring.system.features.attendance.Attendance;
import com.kongsun.leanring.system.features.attendance.AttendanceStatus;
import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.features.student.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance_details")
public class AttendanceDetail extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "att_id")
    private Attendance attendance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stu_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AttendanceStatus status;

    @Column(name = "att_date")
    private LocalDate date;
}
