package com.kongsun.leanring.system.attendance;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid AttendanceRequest request) {
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(attendanceService.create(request))
                        .message("create attendance successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(@RequestParam Map<String ,String> params) {
        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(attendanceService.getAll(params))
                        .message("get attendance successfully")
                        .httpStatus(OK.value())
                        .build());

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity
                .ok(attendanceService.getById(id));

    }

}
