package com.kongsun.leanring.system.teacher;

import com.kongsun.leanring.system.common.PageDTO;

import java.util.Map;


public interface TeacherService {
    Teacher create(Teacher teacher);

    Teacher getById(Long id);

    Teacher update(Long id, Teacher teacher);

    void deleteById(Long id);

    PageDTO getAll(Map<String, String> params);

}
