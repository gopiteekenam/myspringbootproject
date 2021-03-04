package com.biz2tech.app.service;

import com.biz2tech.app.service.dto.PurchaseOrderItemDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PurchaseOrderItem.
 */
public interface PurchaseOrderItemService {

    /**
     * Save a purchaseOrderItem.
     *
     * @param purchaseOrderItemDTO the entity to save
     * @return the persisted entity
     */
    PurchaseOrderItemDTO save(PurchaseOrderItemDTO purchaseOrderItemDTO);

    /**
     * Get all the purchaseOrderItems.
     *
     * @return the list of entities
     */
    List<PurchaseOrderItemDTO> findAll();


    /**
     * Get the "id" purchaseOrderItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PurchaseOrderItemDTO> findOne(Long id);

    /**
     * Delete the "id" purchaseOrderItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
