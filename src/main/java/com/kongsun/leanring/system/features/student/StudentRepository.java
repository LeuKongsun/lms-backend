package com.kongsun.leanring.system.features.student;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> ,
        JpaSpecificationExecutor<Student> {
    boolean exists(Specification<Student> spec);

    @Query("SELECT s.code FROM Student  s ORDER BY s.id DESC LIMIT 1")
    String findLatestStudentCode();

    Optional<Student> findByCode(String code);

}
