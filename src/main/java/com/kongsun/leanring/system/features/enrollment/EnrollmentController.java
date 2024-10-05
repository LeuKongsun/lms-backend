package com.kongsun.leanring.system.features.enrollment;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.exception.ApiResponse;
import com.kongsun.leanring.system.features.payment.Payment;
import com.kongsun.leanring.system.features.payment.PaymentMapper;
import com.kongsun.leanring.system.features.payment.PaymentResponse;
import com.kongsun.leanring.system.features.payment.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final EnrollmentMapper enrollmentMapper;
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid EnrollmentRequest request) {

        EnrollmentResponse enrollmentResponse = enrollmentService.create(request);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(enrollmentResponse)
                        .message("create enrollment successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {

        enrollmentService.deleteById(id);

        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete enrollment successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@RequestParam Map<String, String> params) {
        return ResponseEntity
                .ok(enrollmentService.getAll(params));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(enrollmentService.getById(id))
                        .message("get enrollment successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody EnrollmentRequest request) {
        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(enrollmentService.update(id, request))
                        .message("get enrollment successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping("{enrollmentId}/payments")
    public ResponseEntity<PageDTO> getAll(@PathVariable Long enrollmentId, @RequestParam Map<String, String> params) {
        Page<Payment> paymentPage = paymentService.findByEnrollmentId(enrollmentId, params);
        List<PaymentResponse> list = paymentPage.stream().map(paymentMapper::toPaymentResponse).toList();

        return ResponseEntity
                .ok(new PageDTO(paymentPage, list));
    }

}
