package com.kongsun.leanring.system.features.teacher;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.features.course.Course;
import com.kongsun.leanring.system.features.course.CourseMapper;
import com.kongsun.leanring.system.features.course.CourseService;
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
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@RequestParam Map<String, String> params) {
        return ResponseEntity
                .ok(teacherService.getAll(params));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid TeacherRequest request) {
        Teacher teacher = teacherMapper.toTeacher(request);
        teacher = teacherService.create(teacher);

        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(teacherMapper.toTeacherResponse(teacher))
                        .message("create teacher successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);

        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(teacherMapper.toTeacherResponse(teacher))
                        .message("get teacher successfully")
                        .httpStatus(OK.value())
                        .build()
                );
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid TeacherRequest request) {
        Teacher teacher = teacherMapper.toTeacher(request);
        teacher = teacherService.update(id, teacher);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(teacherMapper.toTeacherResponse(teacher))
                        .message("update teacher successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete teacher successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping("{teacherId}/courses")
    public ResponseEntity<ApiResponse> getCoursesByTeacherId(@PathVariable Long teacherId) {
        List<Course> courses = courseService.getCoursesByTeacherId(teacherId);

        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(courses.stream().map(courseMapper::toCourseResponse).toList())
                        .message("get courses by teacher id successfully")
                        .httpStatus(OK.value())
                        .build()
                );
    }

}
