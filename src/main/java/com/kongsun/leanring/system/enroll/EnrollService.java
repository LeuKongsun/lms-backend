package com.kongsun.leanring.system.enroll;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.course.Course;
import com.kongsun.leanring.system.student.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface EnrollService {
    EnrollResponse create(EnrollRequest enrollmentRequest);

    Enroll getById(Long id);
    List<Enroll> getByStudentId(Long studentId);

    void deleteById(Long id);

    PageDTO getAll(Map<String, String> params);

    Page<Student> getStudentsByCourseId(Long courseId, Map<String, String> params);

    List<Enroll> getEnrollsByStudentId(Long studentId);

}
