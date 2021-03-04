package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.InventoryDtlsDTO;

/**
 * Service Interface for managing InventoryDtls.
 */
public interface InventoryDtlsService {

  /**
   * Save a inventoryDtls.
   *
   * @param inventoryDtlsDTO the entity to save
   * @return the persisted entity
   */
  InventoryDtlsDTO save(InventoryDtlsDTO inventoryDtlsDTO);

  /**
   * Get all the inventoryDtls.
   *
   * @return the list of entities
   */
  List<InventoryDtlsDTO> findAll();

  /**
   * Get all the InventoryDtlsDTO where ProductDtls is null.
   *
   * @return the list of entities
   */
  List<InventoryDtlsDTO> findAllWhereProductDtlsIsNull();

  /**
   * Get the "id" inventoryDtls.
   *
   * @param id the id of the entity
   * @return the entity
   */
  InventoryDtlsDTO findOne(Long id);

  /**
   * Delete the "id" inventoryDtls.
   *
   * @param id the id of the entity
   */
  void delete(Long id);

  void reduceInvenotyQuantityByProductId(List<Long> productIds, int quantity);

  boolean isInventoryAvailable(List<Long> productIds);
}
