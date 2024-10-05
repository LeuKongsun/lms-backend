package com.kongsun.leanring.system.features.schedule;

import com.kongsun.leanring.system.common.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface ScheduleService {
    Schedule create(Schedule schedule);

    Schedule getById(Long id);

    Schedule update(Long id, Schedule schedule);

    void deleteById(Long id);

    PageDTO getAll(Map<String,String> params);
    List<Schedule> getSchedulesByCourseId(Long courseId);
    Page<Schedule> getSchedulesByCourseId(Long courseId, Map<String, String> params);

}
