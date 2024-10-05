package com.kongsun.leanring.system.features.attendance;

import java.util.List;
import java.util.Map;

public interface AttendanceService {
    AttendanceResponse create(AttendanceRequest attendanceRequest);

    List<?> getAll(Map<String ,String> params);

    AttendanceResponse getById(Long id);
}
