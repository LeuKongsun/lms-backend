package com.kongsun.leanring.system.attendance_detail;

import com.kongsun.leanring.system.attendance.AttendanceStatus;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private AttendanceStatus status;
}
