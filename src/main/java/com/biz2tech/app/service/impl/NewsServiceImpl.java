package com.biz2tech.app.service.impl;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.biz2tech.app.domain.News;
import com.biz2tech.app.repository.NewsRepository;
import com.biz2tech.app.service.NewsService;
import com.biz2tech.app.service.dto.NewsDTO;
import com.biz2tech.app.service.mapper.NewsMapper;

/**
 * Service Implementation for managing Testimonials.
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	private final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

	private final NewsRepository newsRepository;

	private final NewsMapper newsMapper;

	public NewsServiceImpl(NewsRepository newsRepository,
			NewsMapper newsMapper) {
		this.newsRepository = newsRepository;
		this.newsMapper = newsMapper;
	}

	/**
	 * Save news.
	 *
	 * @param News to save
	 * @return the persisted entity
	 */
	@Override
	public NewsDTO save(NewsDTO newsDTO) {
		log.debug("Request to save News : {}", newsDTO);
		News news = newsMapper.toEntity(newsDTO);
		news = newsRepository.save(news);
		return newsMapper.toDto(news);
	}

	/**
	 * Get all the News.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<NewsDTO> findAll() {
		log.debug("Request to get all News");
		return newsRepository.findAll().stream().map(newsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one news by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public NewsDTO findOne(Long id) {
		log.debug("Request to get News : {}", id);
		News news = newsRepository.findOne(id);
		return newsMapper.toDto(news);
	}

	/**
	 * Delete the News by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Testimonials : {}", id);
		newsRepository.delete(id);
	}

	@Override
	public List<NewsDTO> findByNewsDate(Instant newsDate) {
		return newsRepository.findByNewsDate(newsDate).stream().map(newsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}
}
