package com.kongsun.leanring.system.enrollment;

import com.kongsun.leanring.system.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>,
        JpaSpecificationExecutor<Enrollment> {
    @Query("SELECT e FROM Enrollment e JOIN e.courses c WHERE e.student.id = :studentId AND c.id IN :courseIds")
    List<Enrollment> findByStudentIdAndCourseIds(Long studentId, Set<Long> courseIds);

    List<Enrollment> findByStudentId(Long studentId);
    @Query("SELECT DISTINCT e.student FROM Enrollment e JOIN e.courses c WHERE c.id = :courseId")
    List<Student> findStudentByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT e.student FROM Enrollment e JOIN e.courses c WHERE c.id = :courseId")
    Page<Student> findStudentByCourseId(@Param("courseId") Long courseId, Pageable pageable);
}
