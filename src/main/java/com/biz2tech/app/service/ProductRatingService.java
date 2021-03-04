package com.biz2tech.app.service;

import java.util.List;
import java.util.Optional;

import com.biz2tech.app.service.dto.ProductRatingDTO;

/**
 * Service Interface for managing ProductRating.
 */
public interface ProductRatingService {

  /**
   * Save a productRating.
   *
   * @param productRatingDTO the entity to save
   * @return the persisted entity
   */
  ProductRatingDTO save(ProductRatingDTO productRatingDTO);

  /**
   * Get all the productRatings.
   *
   * @return the list of entities
   */
  List<ProductRatingDTO> findAll();


  /**
   * Get the "id" productRating.
   *
   * @param id the id of the entity
   * @return the entity
   */
  Optional<ProductRatingDTO> findOne(Long id);

  /**
   * Delete the "id" productRating.
   *
   * @param id the id of the entity
   */
  void delete(Long id);
}
