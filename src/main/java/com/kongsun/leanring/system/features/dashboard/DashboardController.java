package com.kongsun.leanring.system.features.dashboard;

import com.kongsun.leanring.system.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/dashboards")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse> getData() {
        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(dashboardService.getDashboardData())
                        .message("get dashboard successfully")
                        .httpStatus(OK.value())
                        .build());

    }

}
