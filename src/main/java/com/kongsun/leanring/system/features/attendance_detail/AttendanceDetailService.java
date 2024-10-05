package com.kongsun.leanring.system.features.attendance_detail;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.Map;

public interface AttendanceDetailService {
    PageDTO getAttendanceDetailsByCourse(Map<String, String> params);

    AttendanceDetail getById(Long attendanceDetailId);

    void delete(Long attendanceDetailId);

    void updateStatus(Long attendanceDetailId, UpdateStatusRequest request);
}
