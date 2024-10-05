package com.kongsun.leanring.system.features.enroll;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.features.course.CourseMapper;
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
@RequestMapping("/api/v1/enrolls")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class EnrollController {
    private final EnrollService enrollService;
    private final EnrollMapper enrollMapper;
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final CourseMapper courseMapper;
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid EnrollRequest request) {
        System.out.println("Request"+ request);
        EnrollResponse enrollResponse = enrollService.create(request);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(enrollResponse)
                        .message("create enroll successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {

        enrollService.deleteById(id);

        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete enroll successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@RequestParam Map<String, String> params) {
        return ResponseEntity
                .ok(enrollService.getAll(params));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(enrollService.getById(id))
                        .message("get enroll successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody EnrollRequest request) {
        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("get enroll successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping("{enrollId}/payments")
    public ResponseEntity<PageDTO> getAll(@PathVariable Long enrollId, @RequestParam Map<String, String> params) {
        Page<Payment> paymentPage = paymentService.findByEnrollmentId(enrollId, params);
        List<PaymentResponse> list = paymentPage.stream().map(paymentMapper::toPaymentResponse).toList();

        return ResponseEntity
                .ok(new PageDTO(paymentPage, list));
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<ApiResponse> getCourseByStudentId(@PathVariable Long studentId) {
        List<Enroll> enrolls = enrollService.getEnrollsByStudentId(studentId);
        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(enrolls)
                        .message("get enroll successfully")
                        .httpStatus(OK.value())
                        .build()
                );
    }


}
