package com.kongsun.leanring.system.features.qr;

import lombok.Data;

@Data
public class SubmitAttendanceRequest {
    private Long courseId;
    private String studentCode;
}
