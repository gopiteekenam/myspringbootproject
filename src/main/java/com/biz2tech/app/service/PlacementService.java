package com.biz2tech.app.service;

import com.biz2tech.app.service.dto.PlacementDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Placement.
 */
public interface PlacementService {

    /**
     * Save a placement.
     *
     * @param placementDTO the entity to save
     * @return the persisted entity
     */
    PlacementDTO save(PlacementDTO placementDTO);

    /**
     * Get all the placements.
     *
     * @return the list of entities
     */
    List<PlacementDTO> findAll();
    /**
     * Get all the PlacementDTO where PurchaseOrder is null.
     *
     * @return the list of entities
     */
    List<PlacementDTO> findAllWherePurchaseOrderIsNull();
    /**
     * Get all the PlacementDTO where PurchaseOrderItem is null.
     *
     * @return the list of entities
     */
    List<PlacementDTO> findAllWherePurchaseOrderItemIsNull();


    /**
     * Get the "id" placement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlacementDTO> findOne(Long id);

    /**
     * Delete the "id" placement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
