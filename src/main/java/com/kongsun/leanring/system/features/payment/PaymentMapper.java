package com.kongsun.leanring.system.features.payment;

import com.kongsun.leanring.system.features.enroll.EnrollService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                EnrollService.class
        }
)
public interface PaymentMapper {
    @Mapping(
            target = "enroll", source = "enrollId"
    )
    Payment toPayment(PaymentDTO dto);

    @Mapping(target = "enrollId", source = "enroll.id")
    PaymentResponse toPaymentResponse(Payment payment);
}
