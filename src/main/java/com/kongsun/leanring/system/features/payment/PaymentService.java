package com.kongsun.leanring.system.features.payment;

import com.kongsun.leanring.system.common.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    Payment create(Payment payment);
    List<Payment> findByEnrollId(Long enrollId);
    void  deletePayments(List<Payment> payments);

    Payment getById(Long id);

    void deleteById(Long id);

    PageDTO getAll(Map<String, String> params);

    Page<Payment> findByEnrollmentId(Long enrollmentId, Map<String, String > params);

}
