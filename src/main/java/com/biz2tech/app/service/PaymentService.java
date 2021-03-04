package com.biz2tech.app.service;

import com.biz2tech.app.service.dto.PaymentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Payment.
 */
public interface PaymentService {

    /**
     * Save a payment.
     *
     * @param paymentDTO the entity to save
     * @return the persisted entity
     */
    PaymentDTO save(PaymentDTO paymentDTO);

    /**
     * Get all the payments.
     *
     * @return the list of entities
     */
    List<PaymentDTO> findAll();
    /**
     * Get all the PaymentDTO where PurchaseOrder is null.
     *
     * @return the list of entities
     */
    List<PaymentDTO> findAllWherePurchaseOrderIsNull();


    /**
     * Get the "id" payment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PaymentDTO> findOne(Long id);

    /**
     * Delete the "id" payment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
