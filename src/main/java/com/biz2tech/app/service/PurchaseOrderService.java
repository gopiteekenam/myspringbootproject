package com.biz2tech.app.service;

import java.util.List;
import java.util.Optional;

import com.biz2tech.app.service.dto.CreatePurchaseOrderDTO;
import com.biz2tech.app.service.dto.UpdatePurchaseOrderDTO;

/**
 * Service Interface for managing PurchaseOrder.
 */
public interface PurchaseOrderService {

  /**
   * Save a purchaseOrder.
   *
   * @param purchaseOrderDTO the entity to save
   * @return the persisted entity
   */
  CreatePurchaseOrderDTO save(CreatePurchaseOrderDTO purchaseOrderDTO);

  /**
   * Get all the purchaseOrders.
   *
   * @return the list of entities
   */
  List<CreatePurchaseOrderDTO> findAll();


  /**
   * Get the "id" purchaseOrder.
   *
   * @param id the id of the entity
   * @return the entity
   */
  Optional<CreatePurchaseOrderDTO> findOne(Long id);

  /**
   * Delete the "id" purchaseOrder.
   *
   * @param id the id of the entity
   */
  void delete(Long id);

  CreatePurchaseOrderDTO createPurchaseOrder(CreatePurchaseOrderDTO purchaseOrderDTO,
      String userLoginId);

  UpdatePurchaseOrderDTO updatePurchaseOrder(UpdatePurchaseOrderDTO purchaseOrderDTO,
      String userLoginId);

  List<CreatePurchaseOrderDTO> findByUserName(String userId);
}
