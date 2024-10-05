package com.kongsun.leanring.system.features.payment;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.toPayment(paymentDTO);
        return ResponseEntity
                .status(CREATED)
                .body(ApiResponse.builder()
                        .data(paymentService.create(payment))
                        .message("create payment successfully")
                        .httpStatus(CREATED.value())
                        .build()
                );

    }

    @DeleteMapping("{paymentId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long paymentId) {
        paymentService.deleteById(paymentId);
        return ResponseEntity
                .status(OK)
                .body(ApiResponse.builder()
                        .data(null)
                        .message("delete payment successfully")
                        .httpStatus(OK.value())
                        .build()
                );

    }

    @GetMapping
    public ResponseEntity<PageDTO> getAll(@RequestParam Map<String ,String > params) {
        return ResponseEntity
                .ok(paymentService.getAll(params));

    }


}
