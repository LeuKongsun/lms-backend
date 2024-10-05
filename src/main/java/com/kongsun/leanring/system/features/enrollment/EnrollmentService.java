package com.kongsun.leanring.system.features.enrollment;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.features.course.CourseResponse;
import com.kongsun.leanring.system.features.student.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentRequest enrollmentRequest);

    Enrollment getById(Long id);

    void deleteById(Long id);

    PageDTO getAll(Map<String ,String >params);

    List<CourseResponse> getStudentEnrollmentCourses(Long studentId);
    EnrollmentResponse update(Long id, EnrollmentRequest enrollmentRequest);
    List<Student> getStudentByCourse(Long courseId);
    Page<Student> getStudentByCourse(Long courseId, Map<String ,String> params);
}
