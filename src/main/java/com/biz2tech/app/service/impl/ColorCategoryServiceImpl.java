package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.ColorCategory;
import com.biz2tech.app.repository.ColorCategoryRepository;
import com.biz2tech.app.service.ColorCategoryService;
import com.biz2tech.app.service.dto.ColorCategoryDTO;
import com.biz2tech.app.service.mapper.ColorCategoryMapper;

/**
 * Service Implementation for managing Color Category.
 */
@Service
@Transactional
public class ColorCategoryServiceImpl implements ColorCategoryService {

	private final Logger log = LoggerFactory.getLogger(ColorCategoryServiceImpl.class);

	private final ColorCategoryRepository colorCategoryRepository;

	private final ColorCategoryMapper colorCategoryMapper;

	public ColorCategoryServiceImpl(ColorCategoryRepository colorCategoryRepository,
			ColorCategoryMapper colorCategoryMapper) {
		this.colorCategoryRepository = colorCategoryRepository;
		this.colorCategoryMapper = colorCategoryMapper;
	}

	/**
	 * Save a color category.
	 *
	 * @param ColorCategoryDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public ColorCategoryDTO save(ColorCategoryDTO ColorCategoryDTO) {
		log.debug("Request to save Color Category : {}", ColorCategoryDTO);
		ColorCategory colorCategory = colorCategoryMapper.toEntity(ColorCategoryDTO);
		colorCategory = colorCategoryRepository.save(colorCategory);
		return colorCategoryMapper.toDto(colorCategory);
	}

	/**
	 * Get all the color categories.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ColorCategoryDTO> findAll() {
		log.debug("Request to get all Color categories");
		return colorCategoryRepository.findAll().stream().map(colorCategoryMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one color category by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public ColorCategoryDTO findOne(Long id) {
		log.debug("Request to get Color Category : {}", id);
		ColorCategory colorCategory = colorCategoryRepository.findOne(id);
		return colorCategoryMapper.toDto(colorCategory);
	}

	/**
	 * Delete the productTag by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Color category : {}", id);
		colorCategoryRepository.delete(id);
	}

	@Override
	public List<Long> findByCategoryName(String categoryName) {
		return colorCategoryRepository.findByCategoryName(categoryName).stream()
	            .map(ColorCategory::getId)
	            .collect(Collectors.toList());
	}
}
