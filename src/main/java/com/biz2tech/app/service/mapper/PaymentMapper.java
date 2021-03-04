package com.biz2tech.app.service.mapper;

import com.biz2tech.app.domain.*;
import com.biz2tech.app.service.dto.PaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Payment and its DTO PaymentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {


    @Mapping(target = "purchaseOrder", ignore = true)
    Payment toEntity(PaymentDTO paymentDTO);

    default Payment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(id);
        return payment;
    }
}
