package com.kongsun.leanring.system.student;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> ,
        JpaSpecificationExecutor<Student> {
    boolean exists(Specification<Student> spec);
}
