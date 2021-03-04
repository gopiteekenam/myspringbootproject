package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.Testimonials;
import com.biz2tech.app.repository.TestimonialsRepository;
import com.biz2tech.app.service.TestimonialsService;
import com.biz2tech.app.service.dto.TestimonialsDTO;
import com.biz2tech.app.service.mapper.TestimonialsMapper;

/**
 * Service Implementation for managing Testimonials.
 */
@Service
@Transactional
public class TestimonialsServiceImpl implements TestimonialsService {

	private final Logger log = LoggerFactory.getLogger(TestimonialsServiceImpl.class);

	private final TestimonialsRepository testimonialsRepository;

	private final TestimonialsMapper testimonialsMapper;

	public TestimonialsServiceImpl(TestimonialsRepository testimonialsRepository,
			TestimonialsMapper testimonialsMapper) {
		this.testimonialsRepository = testimonialsRepository;
		this.testimonialsMapper = testimonialsMapper;
	}

	/**
	 * Save testimonials.
	 *
	 * @param Testimonials to save
	 * @return the persisted entity
	 */
	@Override
	public TestimonialsDTO save(TestimonialsDTO testimonialsDTO) {
		log.debug("Request to save Testimonials : {}", testimonialsDTO);
		Testimonials testimonials = testimonialsMapper.toEntity(testimonialsDTO);
		testimonials = testimonialsRepository.save(testimonials);
		return testimonialsMapper.toDto(testimonials);
	}

	/**
	 * Get all the Testimonials.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TestimonialsDTO> findAll() {
		log.debug("Request to get all Testimonials");
		return testimonialsRepository.findAll().stream().map(testimonialsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one Testimonials by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public TestimonialsDTO findOne(Long id) {
		log.debug("Request to get Testimonials : {}", id);
		Testimonials testimonials = testimonialsRepository.findOne(id);
		return testimonialsMapper.toDto(testimonials);
	}

	/**
	 * Delete the Testimonials by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Testimonials : {}", id);
		testimonialsRepository.delete(id);
	}

	@Override
	public List<TestimonialsDTO> findByCity(String city) {
		return testimonialsRepository.findByCity(city).stream().map(testimonialsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<TestimonialsDTO> findByCommentorName(String commentorName) {
		return testimonialsRepository.findByCommentorName(commentorName).stream().map(testimonialsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}
}
