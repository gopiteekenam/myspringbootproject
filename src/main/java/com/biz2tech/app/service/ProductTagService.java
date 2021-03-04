package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.ProductTagDTO;

/**
 * Service Interface for managing ProductTag.
 */
public interface ProductTagService {

    /**
     * Save a productTag.
     *
     * @param productTagDTO the entity to save
     * @return the persisted entity
     */
    ProductTagDTO save(ProductTagDTO productTagDTO);

    /**
     * Get all the productTags.
     *
     * @return the list of entities
     */
    List<ProductTagDTO> findAll();

    /**
     * Get the "id" productTag.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ProductTagDTO findOne(Long id);

    /**
     * Delete the "id" productTag.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	List<Long> findProductTagsByTagName(List<String> tagname);
}
