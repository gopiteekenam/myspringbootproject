package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.ColorCategoryDTO;

/**
 * Service Interface for managing Color Category.
 */
public interface ColorCategoryService {

	/**
	 * Save a color category
	 *
	 * @param productTagDTO the entity to save
	 * @return the persisted entity
	 */
	ColorCategoryDTO save(ColorCategoryDTO colorCategoryDTO);

	/**
	 * Get all the color categories.
	 *
	 * @return the list of entities
	 */
	List<ColorCategoryDTO> findAll();

	/**
	 * Get the "id" colorCategory.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	ColorCategoryDTO findOne(Long id);

	/**
	 * Delete the "id" productTag.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);

	List findByCategoryName(String categoryName);
}
