package com.kongsun.leanring.system.features.teacher;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "teachersCache")
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    @CacheEvict(allEntries = true)
    public Teacher create(Teacher teacher) {
        if(teacher.getPhone() != null && !teacher.getPhone().isEmpty()){
            if(teacherRepository.exists(TeacherSpec.hasPhone(teacher.getPhone()))){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Phone already exists");
            }
        }

        if(teacher.getEmail() != null && !teacher.getEmail().isEmpty()){
            if(teacherRepository.exists(TeacherSpec.hasEmail((teacher.getEmail())))){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Email already exists");
            }
        }

        return teacherRepository.save(teacher);
    }

    @Override
    @Cacheable(key = "#id")
    public Teacher getById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", id));
    }

    @Override
    @CacheEvict(allEntries = true)
    public Teacher update(Long id, Teacher teacher) {
        Teacher byId = getById(id);

        if (teacher.getPhone() != null && !teacher.getPhone().isEmpty()) {
            boolean phoneExists = teacherRepository.exists(TeacherSpec.hasPhoneExcludingId(teacher.getPhone(), id));
            if (phoneExists) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Phone already exists");
            }
        }
        if (teacher.getEmail() != null && !teacher.getEmail().isEmpty()) {
            boolean existsEmail = teacherRepository.exists(TeacherSpec.hasEmailExcludingId(teacher.getEmail(), id));
            if(existsEmail){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Email already exists");
            }
        }

        byId.setCode(teacher.getCode());
        byId.setFirstname(teacher.getFirstname());
        byId.setLastname(teacher.getLastname());
        byId.setEmail(teacher.getEmail());
        byId.setPhone(teacher.getPhone());
        byId.setAddress(teacher.getAddress());
        byId.setGender(teacher.getGender());
        byId.setSalary(teacher.getSalary());
        byId.setHireDate(teacher.getHireDate());
        byId.setStopWork(teacher.isStopWork());
        return teacherRepository.save(byId);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(Long id) {
        getById(id);
        teacherRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public PageDTO getAll(Map<String, String> params) {
        Specification<Teacher> spec = Specification.where(null);

        if (params.containsKey("name")) {
            spec = spec.and(TeacherSpec.containLastname(params.get("name")))
                    .or(TeacherSpec.containFirstname(params.get("name")));
        }
        if (params.containsKey("gender")) {
            spec = spec.and(TeacherSpec.hasGender(params.get("gender")));
        }
        if (params.containsKey("stop")) {
            spec = spec.and(TeacherSpec.isStopWork(Boolean.parseBoolean(params.get("stop"))));
        } else {
            //get only teacher working
            spec = spec.and(TeacherSpec.isStopWork(false));
        }
        if(params.containsKey("all")){
            return new PageDTO(teacherRepository.findAll());
        }

        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        Page<Teacher> studentPage = teacherRepository.findAll(spec, pageable);

        return new PageDTO(studentPage);
    }

}
