package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.SellerDetailsDTO;

/**
 * Service Interface for managing SellerDetails.
 */
public interface SellerDetailsService {

    /**
     * Save a sellerDetails.
     *
     * @param sellerDetailsDTO the entity to save
     * @return the persisted entity
     */
    SellerDetailsDTO save(SellerDetailsDTO sellerDetailsDTO);

    /**
     * Get all the sellerDetails.
     *
     * @return the list of entities
     */
    List<SellerDetailsDTO> findAll();

    /**
     * Get the "id" sellerDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SellerDetailsDTO findOne(Long id);

    /**
     * Delete the "id" sellerDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
