package com.biz2tech.app.service;

import com.biz2tech.app.service.dto.ProductColorDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ProductColor.
 */
public interface ProductColorService {

    /**
     * Save a productColor.
     *
     * @param productColorDTO the entity to save
     * @return the persisted entity
     */
    ProductColorDTO save(ProductColorDTO productColorDTO);

    /**
     * Get all the productColors.
     *
     * @return the list of entities
     */
    List<ProductColorDTO> findAll();


    /**
     * Get the "id" productColor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProductColorDTO> findOne(Long id);

    /**
     * Delete the "id" productColor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
