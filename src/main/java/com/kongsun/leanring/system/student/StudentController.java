package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.enroll.EnrollService;
import com.kongsun.leanring.system.enrollment.EnrollmentService;
import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final EnrollmentService enrollmentService;
    private final EnrollService enrollService;

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@RequestParam Map<String,String > params) {
        return ResponseEntity
                .ok(studentService.getAll(params));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid StudentRequest request) {
        Student student = studentMapper.toStudent(request);
        student = studentService.create(student);

        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(studentMapper.toStudentResponse(student))
                        .message("create student successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);

        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(studentMapper.toStudentResponse(student))
                        .message("get student successfully")
                        .httpStatus(OK.value())
                        .build()
                );
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid StudentRequest request) {
        Student student = studentMapper.toStudent(request);
        student = studentService.update(id, student);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(studentMapper.toStudentResponse(student))
                        .message("update student successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete student successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping("{studentId}/courses")
    public ResponseEntity<ApiResponse> getStudentEnrollmentCourses(@PathVariable Long studentId) {
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(enrollmentService.getStudentEnrollmentCourses(studentId))
                        .message("get student enrollment courses successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping("{studentId}/enrolls")
    public ResponseEntity<ApiResponse> getEnrollByStudent(@PathVariable Long studentId) {
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(enrollService.getByStudentId(studentId))
                        .message("get student enroll courses successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }
}
