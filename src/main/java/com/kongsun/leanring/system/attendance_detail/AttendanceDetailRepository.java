package com.kongsun.leanring.system.attendance_detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceDetailRepository extends JpaRepository<AttendanceDetail, Long>,
        JpaSpecificationExecutor<AttendanceDetail> {
    List<AttendanceDetail> findByAttendanceId(Long attendanceId);

    List<AttendanceDetail> findByAttendanceIdIn(List<Long> attendanceIds);

}
