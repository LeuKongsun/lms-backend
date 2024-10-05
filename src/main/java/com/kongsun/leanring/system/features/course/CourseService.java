package com.kongsun.leanring.system.features.course;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface CourseService {
    Course create(Course course);

    Course getById(Long id);

    Course update(Long id, Course course);

    void deleteById(Long id);

    PageDTO getAll(Map<String, String> params);
    List<Course> getAll();

    Course addTeacherToCourse(Long courseId, Long teacherId);
    void removeTeacherFromCourse(Long courseId);
    List<Course> getCoursesByTeacherId(Long teacherId);
    Set<Course> findCoursesByIds(Set<Long> courseIds);

}
