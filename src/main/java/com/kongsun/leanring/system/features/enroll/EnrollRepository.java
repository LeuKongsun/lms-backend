package com.kongsun.leanring.system.features.enroll;

import com.kongsun.leanring.system.features.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long>,
        JpaSpecificationExecutor<Enroll> {

    List<Enroll> findByStudentId(Long studentId);

    @Query("SELECT e.student FROM Enroll e WHERE e.course.id = :courseId")
    Page<Student> findStudentsByCourseId(Long courseId, Pageable pageable) ;

    @Query("SELECT e FROM Enroll e where e.student.id = :studentId")
    List<Enroll> findEnrollByStudentId(@Param("studentId") Long studentId);
}
