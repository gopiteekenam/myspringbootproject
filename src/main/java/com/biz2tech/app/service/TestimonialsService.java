package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.TestimonialsDTO;

/**
 * Service Interface for managing Testimonials.
 */
public interface TestimonialsService {

	/**
	 * Save Testimonials.
	 *
	 * @param TestimonialsDTO the entity to save
	 * @return the persisted entity
	 */
	TestimonialsDTO save(TestimonialsDTO testimonialsDTO);

	/**
	 * Get all the testimonials.
	 *
	 * @return the list of entities
	 */
	List<TestimonialsDTO> findAll();

	/**
	 * Get the testimonials by ID
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	TestimonialsDTO findOne(Long id);

	/**
	 * Delete the "id" Testimonials.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);

	List<TestimonialsDTO> findByCity(String city);

	public List<TestimonialsDTO> findByCommentorName(String commentorName);
}
