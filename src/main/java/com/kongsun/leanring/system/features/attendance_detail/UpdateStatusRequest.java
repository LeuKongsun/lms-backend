package com.kongsun.leanring.system.features.attendance_detail;

import com.kongsun.leanring.system.features.attendance.AttendanceStatus;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private AttendanceStatus status;
}
