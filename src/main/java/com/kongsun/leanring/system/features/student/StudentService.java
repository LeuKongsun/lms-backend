package com.kongsun.leanring.system.features.student;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.List;
import java.util.Map;


public interface StudentService {
    Student create(Student student);

    Student getById(Long id);

    Student update(Long id, Student student);

    void deleteById(Long id);

    PageDTO getAll(Map<String , String> params);
    List<Student> getStudentList();

    List<Student> getByIds(List<Long> studentIds);
}
