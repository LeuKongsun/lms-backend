package com.kongsun.leanring.system.dashboard;

import com.kongsun.leanring.system.course.CourseRepository;
import com.kongsun.leanring.system.student.StudentRepository;
import com.kongsun.leanring.system.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    @Override
    public DashboardResponse getDashboardData() {
        long totalCourses = courseRepository.count();
        long totalStudents = studentRepository.count();
        long totalTeachers = teacherRepository.count();

        return DashboardResponse.builder()
                .totalCourses(totalCourses)
                .totalStudents(totalStudents)
                .totalTeachers(totalTeachers)
                .build();
    }
}
