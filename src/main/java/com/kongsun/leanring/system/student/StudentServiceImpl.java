package com.kongsun.leanring.system.student;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "studentsCache")
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    @CacheEvict(allEntries = true)
    public Student create(Student student) {
        if(student.getPhone() != null && !student.getPhone().isEmpty()){
            if(studentRepository.exists(StudentSpec.hasPhone(student.getPhone()))){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Phone already exists");
            }
        }

        if(student.getEmail() != null && !student.getEmail().isEmpty()){
            if(studentRepository.exists(StudentSpec.hasEmail((student.getEmail())))){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Email already exists");
            }
        }

        return studentRepository.save(student);
    }

    @Override
    @Cacheable(key = "#id")
    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));
    }

    @Override
    @CacheEvict(allEntries = true)
    public Student update(Long id, Student student) {
        //before update check phone
        if (student.getPhone() != null && !student.getPhone().isEmpty()) {
            boolean phoneExists = studentRepository.exists(StudentSpec.hasPhoneExcludingId(student.getPhone(), id));
            if (phoneExists) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Phone already exists");
            }
        }
        if (student.getEmail() != null && !student.getEmail().isEmpty()) {
            boolean existsEmail = studentRepository.exists(StudentSpec.hasEmailExcludingId(student.getEmail(), id));
            if(existsEmail){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Email already exists");
            }
        }

        Student byId = getById(id);
        byId.setFirstname(student.getFirstname());
        byId.setLastname(student.getLastname());
        byId.setGender(student.getGender());
        byId.setPhone(student.getPhone());
        byId.setEmail(student.getEmail());
        byId.setType(student.getType());
        byId.setPosition(student.getPosition());
        byId.setFrom(student.getFrom());
        return studentRepository.save(byId);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(Long id) {
        getById(id);
        studentRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public PageDTO getAll(Map<String , String> params) {
        Specification<Student> spec = Specification.where(null);

        if(params.containsKey("search")){
            spec = spec.and(StudentSpec.containLastname(params.get("search")))
                    .or(StudentSpec.containFirstname(params.get("search")))
                    .or(StudentSpec.containPhone(params.get("search")))
                    .or(StudentSpec.containEmail(params.get("search")));
        }
        if(params.containsKey("gender")){
            spec = spec.and(StudentSpec.containGender(params.get("gender")));
        }

        if(params.containsKey("all")){
            Sort sort = Sort.by(Sort.Order.desc("createdAt")); // Sorting by 'createdAt' field
            List<Student> sortedStudents = studentRepository.findAll(spec, sort);
            return new PageDTO(sortedStudents);
        }

        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        Page<Student> studentPage = studentRepository.findAll(spec, pageable);

        return new PageDTO(studentPage);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    @Cacheable(key = "#studentIds")
    public List<Student> getByIds(List<Long> studentIds) {
        return studentRepository.findAllById(studentIds);
    }

}
