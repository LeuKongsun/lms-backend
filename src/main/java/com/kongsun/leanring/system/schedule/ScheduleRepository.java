package com.kongsun.leanring.system.schedule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>,
        JpaSpecificationExecutor<Schedule> {
    List<Schedule> findByCourseId(Long courseId);
    Page<Schedule> findByCourseId(Long courseId, Pageable pageable);
    List<Schedule> findByCourseName(String courseName);
}
