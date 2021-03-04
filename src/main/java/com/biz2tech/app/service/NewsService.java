package com.biz2tech.app.service;

import java.time.Instant;
import java.util.List;

import com.biz2tech.app.service.dto.NewsDTO;

/**
 * Service Interface for managing s.
 */
public interface NewsService {

	/**
	 * Save News.
	 *
	 * @param NewsDTO the entity to save
	 * @return the persisted entity
	 */
	NewsDTO save(NewsDTO newsDTO);

	/**
	 * Get all the news.
	 *
	 * @return the list of entities
	 */
	List<NewsDTO> findAll();

	/**
	 * Get the news by ID
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	NewsDTO findOne(Long id);

	/**
	 * Delete the "id" News.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);

	List<NewsDTO> findByNewsDate(Instant newsDate);
	
}
