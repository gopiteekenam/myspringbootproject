package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.TaxItemDTO;

/**
 * Service Interface for managing TaxItem.
 */
public interface TaxItemService {

    /**
     * Save a taxItem.
     *
     * @param taxItemDTO the entity to save
     * @return the persisted entity
     */
    TaxItemDTO save(TaxItemDTO taxItemDTO);

    /**
     * Get all the taxItems.
     *
     * @return the list of entities
     */
    List<TaxItemDTO> findAll();

    /**
     * Get the "id" taxItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TaxItemDTO findOne(Long id);

    /**
     * Delete the "id" taxItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
