package com.kongsun.leanring.system.features.schedule;

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
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;


    @GetMapping
    public ResponseEntity<PageDTO> getAll(@RequestParam Map<String, String> params) {
        return ResponseEntity
                .ok(scheduleService.getAll(params));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid ScheduleRequest request) {
        Schedule schedule = scheduleMapper.toSchedule(request);
        schedule = scheduleService.create(schedule);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(scheduleMapper.toScheduleResponse(schedule))
                        .message("create schedule successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getById(id);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(scheduleMapper.toScheduleResponse(schedule))
                        .message("get schedule successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid ScheduleRequest request) {
        Schedule schedule = scheduleMapper.toSchedule(request);
        schedule = scheduleService.update(id, schedule);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(scheduleMapper.toScheduleResponse(schedule))
                        .message("update schedule successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete schedule successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

}
