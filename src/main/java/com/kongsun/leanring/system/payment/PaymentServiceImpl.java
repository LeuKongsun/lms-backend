package com.kongsun.leanring.system.payment;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.enroll.Enroll;
import com.kongsun.leanring.system.enroll.EnrollRepository;
import com.kongsun.leanring.system.enroll.EnrollService;
import com.kongsun.leanring.system.enroll.EnrollStatus;
import com.kongsun.leanring.system.enrollment.Enrollment;
import com.kongsun.leanring.system.enrollment.EnrollmentRepository;
import com.kongsun.leanring.system.exception.ApiException;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import com.kongsun.leanring.system.student.Student;
import com.kongsun.leanring.system.student.StudentSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.kongsun.leanring.system.enrollment.EnrollmentStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
@CacheConfig(cacheNames = "paymentsCache")
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final EnrollRepository enrollRepository;

    @Override
    @CacheEvict(allEntries = true)
    public Payment create(Payment payment) {
        Enroll enroll = payment.getEnroll();
        checkPaymentStatus(enroll, payment.getAmount());
        enrollRepository.save(enroll);
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findByEnrollId(Long enrollId) {
        return paymentRepository.findByEnrollId(enrollId);
    }

    @Override
    public void deletePayments(List<Payment> payments) {
        paymentRepository.deleteAll(payments);
    }

    @Override
    @CachePut(key = "#id")
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("Payment", id)));
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteById(Long id) {
        Payment payment = getById(id);
        Enroll enroll = payment.getEnroll();

        BigDecimal remain = enroll.getRemain().add(payment.getAmount());
        enroll.setRemain(remain);

        if (remain.compareTo(enroll.getPrice()) == 0) {
            enroll.setStatus(EnrollStatus.UNPAID);

        } else if (remain.compareTo(enroll.getPrice()) < 0) {
            enroll.setStatus(EnrollStatus.PARTIAL);

        } else if (remain.compareTo(enroll.getPrice()) > 0) {
            enroll.setStatus(EnrollStatus.PAID);

        }

        enrollRepository.save(enroll);
        paymentRepository.deleteById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public PageDTO getAll(Map<String, String> params) {
        Specification<Payment> spec = Specification.where(null);

        if(params.containsKey("search")){
            spec = spec.and(PaymentSpec.containLastname(params.get("search")))
                    .or(PaymentSpec.containFirstname(params.get("search")));
        }
        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        return new PageDTO(paymentRepository.findAll(spec, pageable));
    }

    @Override
    public Page<Payment> findByEnrollmentId(Long enrollmentId, Map<String, String > params) {
        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        return paymentRepository.findByEnrollId(enrollmentId, pageable);
    }


    private void checkPaymentStatus(Enroll enroll, BigDecimal paidAmount) {
        BigDecimal cashback = BigDecimal.ZERO;
        BigDecimal remain = checkRemain(enroll.getRemain());

        if (paidAmount.compareTo(remain) < 0) {
            remain = remain.subtract(paidAmount);

            enroll.setStatus(EnrollStatus.PARTIAL);
            enroll.setRemain(remain);
        } else if (paidAmount.compareTo(remain) > 0) {
            cashback = paidAmount.subtract(remain);

            enroll.setStatus(EnrollStatus.PAID);
            enroll.setRemain(BigDecimal.ZERO);
        } else {
            enroll.setStatus(EnrollStatus.PAID);
            enroll.setRemain(BigDecimal.ZERO);
        }
    }

    private void checkPaymentStatus(Enrollment enrollment, BigDecimal paidAmount) {
        BigDecimal cashback = BigDecimal.ZERO;
        BigDecimal remain = checkRemain(enrollment.getRemain());

        if (paidAmount.compareTo(remain) < 0) {
            remain = remain.subtract(paidAmount);

            enrollment.setStatus(PARTIAL);
            enrollment.setRemain(remain);
        } else if (paidAmount.compareTo(remain) > 0) {
            cashback = paidAmount.subtract(remain);

            enrollment.setStatus(PAID);
            enrollment.setRemain(BigDecimal.ZERO);
        } else {
            enrollment.setStatus(PAID);
            enrollment.setRemain(BigDecimal.ZERO);
        }
    }

    private BigDecimal checkRemain(BigDecimal remain) {
        if (remain.compareTo(BigDecimal.ZERO) == 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Cannot make payment because remain = 0");
        }

        return remain;
    }
}
