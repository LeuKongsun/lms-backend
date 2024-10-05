package com.kongsun.leanring.system.features.enroll;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.features.course.Course;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.features.payment.Payment;
import com.kongsun.leanring.system.features.payment.PaymentService;
import com.kongsun.leanring.system.features.student.Student;
import com.kongsun.leanring.system.features.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "enrollmentsCache")
public class EnrollServiceImpl implements EnrollService {

    private final EnrollRepository enrollRepository;
    private final StudentService studentService;
    private final PaymentService paymentService;
    private final EnrollMapper enrollMapper;

    @Override
    public EnrollResponse create(EnrollRequest enrollRequest) {
        Enroll enroll = enrollMapper.toEnroll(enrollRequest);
        Course course = enroll.getCourse();
        BigDecimal discountPrice = course.priceAfterDiscount();

        enroll.setPrice(discountPrice);
        enroll.setRemain(discountPrice);
        enroll.setStatus(EnrollStatus.UNPAID);
        enroll.setDate(LocalDate.now());
        enrollRepository.save(enroll);

        if(enrollRequest.getAmount() != null && enrollRequest.getAmount().compareTo(BigDecimal.ZERO) > 0){
            // make payment
            Payment payment = new Payment();
            payment.setEnroll(enroll);
            payment.setAmount(enrollRequest.getAmount());
            payment.setDate(enrollRequest.getDate());
            payment.setReceiver(enrollRequest.getReceiver());
            payment.setMethod(enrollRequest.getMethod());
            paymentService.create(payment);
        }

        return enrollMapper.toEnrollResponse(enroll);
    }

    @Override
    public Enroll getById(Long id) {
        return enrollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enroll", id));
    }

    @Override
    public List<Enroll> getByStudentId(Long studentId) {
        return enrollRepository.findByStudentId(studentId);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);
        List<Payment> payments = paymentService.findByEnrollId(id);

        if(payments != null && !payments.isEmpty()){
            System.out.println("Payments"+ payments);
            paymentService.deletePayments(payments);
        }
        enrollRepository.deleteById(id);
    }

    @Override
    public PageDTO getAll(Map<String, String> params) {
        Specification<Enroll> spec = Specification.where(null);
        if (params.containsKey("search")) {
            spec = EnrollSpec.containStudentName(params.get("search"));
        }
        if (params.containsKey("status")) {
            String status = params.get("status");
            if (status != null && !status.isEmpty()) {
                spec = spec.and(EnrollSpec.equalStatus(status));
            }
        }
        if (params.containsKey("course")) {
            String course = params.get("course");
            if (course != null && !course.isEmpty()) {
                spec = spec.and(EnrollSpec.hasCourseName(course));
            }
        }
        if (params.containsKey("all")) {
            return new PageDTO(enrollRepository.findAll());
        }

        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);

        return new PageDTO(enrollRepository.findAll(spec, pageable));
    }

    @Override
    public Page<Student> getStudentsByCourseId(Long courseId, Map<String, String> params) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        return enrollRepository.findStudentsByCourseId(courseId, pageable);
    }

    @Override
    public List<Enroll> getEnrollsByStudentId(Long studentId) {
        return enrollRepository.findEnrollByStudentId(studentId);
    }
}
