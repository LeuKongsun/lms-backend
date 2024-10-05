package com.kongsun.leanring.system.features.attendance_detail;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/attendance-details")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AttendanceDetailController {
    private final AttendanceDetailService attendanceDetailService;

    @GetMapping
    public ResponseEntity<PageDTO> getAttendanceDetailByCourse(@RequestParam Map<String, String> params) {
        return ResponseEntity
                .ok(attendanceDetailService.getAttendanceDetailsByCourse(params));

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        attendanceDetailService.updateStatus(id, request);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(null)
                        .message("update attendanceDetail successfully")
                        .httpStatus(HttpStatus.OK.value())
                        .build()
                );

    }
}
