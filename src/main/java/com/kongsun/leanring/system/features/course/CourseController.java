package com.kongsun.leanring.system.features.course;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.features.enroll.EnrollService;
import com.kongsun.leanring.system.features.enrollment.EnrollmentService;
import com.kongsun.leanring.system.exception.ApiResponse;
import com.kongsun.leanring.system.features.schedule.Schedule;
import com.kongsun.leanring.system.features.schedule.ScheduleMapper;
import com.kongsun.leanring.system.features.schedule.ScheduleResponse;
import com.kongsun.leanring.system.features.schedule.ScheduleService;
import com.kongsun.leanring.system.features.student.Student;
import com.kongsun.leanring.system.features.student.StudentMapper;
import com.kongsun.leanring.system.features.student.StudentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CourseController {
    private final CourseService courseService;
    private final ScheduleService scheduleService;
    private final EnrollmentService enrollmentService;
    private final CourseMapper courseMapper;
    private final ScheduleMapper scheduleMapper;
    private final StudentMapper studentMapper;
    private final EnrollService enrollService;

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@RequestParam Map<String, String> params) {
        return ResponseEntity
                .ok(courseService.getAll(params));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid CourseRequest request) {
        Course course = courseMapper.toCourse(request);
        course = courseService.create(course);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(courseMapper.toCourseResponse(course))
                        .message("create course successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Course course = courseService.getById(id);
        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(courseMapper.toCourseResponse(course))
                        .message("get course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid CourseRequest request) {
        Course course = courseMapper.toCourse(request);

        course = courseService.update(id, course);
        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(courseMapper.toCourseResponse(course))
                        .message("update course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity
                .ok(ApiResponse.builder()
                        .data(null)
                        .message("delete course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PutMapping("{courseId}/teachers/{teacherId}")
    public ResponseEntity<ApiResponse> addTeacherToCourse(@PathVariable Long courseId, @PathVariable Long teacherId) {
        Course course = courseService.addTeacherToCourse(courseId, teacherId);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(courseMapper.toCourseResponse(course))
                        .message("add teacher to course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @DeleteMapping("{courseId}/teachers")
    public ResponseEntity<ApiResponse> removeTeacherFromCourse(@PathVariable Long courseId) {
        courseService.removeTeacherFromCourse(courseId);

        return ResponseEntity
                .ok()
                .body(ApiResponse.builder()
                        .data(null)
                        .message("remove teacher from course successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping("{courseId}/schedules")
    public ResponseEntity<PageDTO> getSchedulesByCourseId(@PathVariable Long courseId, @RequestParam Map<String, String> params) {
        Page<Schedule> schedulePage = scheduleService.getSchedulesByCourseId(courseId, params);
        List<ScheduleResponse> list = schedulePage.stream().map(scheduleMapper::toScheduleResponse).toList();
        return ResponseEntity
                .ok(new PageDTO(schedulePage, list));

    }

    @GetMapping("{courseId}/students")
    public ResponseEntity<PageDTO> getStudentByCourse(@PathVariable Long courseId, @RequestParam Map<String, String> params) {
//        Page<Student> studentPage = enrollmentService.getStudentByCourse(courseId, params);
//        List<StudentResponse> list = studentPage.stream().map(studentMapper::toStudentResponse).toList();

        Page<Student> studentPage = enrollService.getStudentsByCourseId(courseId, params);
        List<StudentResponse> list = studentPage.stream().map(studentMapper::toStudentResponse).toList();
        return ResponseEntity
                .ok(new PageDTO(studentPage, list));

    }

}
