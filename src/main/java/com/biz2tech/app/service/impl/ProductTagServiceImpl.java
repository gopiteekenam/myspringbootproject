package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.ProductTag;
import com.biz2tech.app.repository.ProductTagRepository;
import com.biz2tech.app.service.ProductTagService;
import com.biz2tech.app.service.dto.ProductTagDTO;
import com.biz2tech.app.service.mapper.ProductTagMapper;

/**
 * Service Implementation for managing ProductTag.
 */
@Service
@Transactional
public class ProductTagServiceImpl implements ProductTagService {

    private final Logger log = LoggerFactory.getLogger(ProductTagServiceImpl.class);

    private final ProductTagRepository productTagRepository;

    private final ProductTagMapper productTagMapper;

    public ProductTagServiceImpl(ProductTagRepository productTagRepository, ProductTagMapper productTagMapper) {
        this.productTagRepository = productTagRepository;
        this.productTagMapper = productTagMapper;
    }

    /**
     * Save a productTag.
     *
     * @param productTagDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductTagDTO save(ProductTagDTO productTagDTO) {
        log.debug("Request to save ProductTag : {}", productTagDTO);
        ProductTag productTag = productTagMapper.toEntity(productTagDTO);
        productTag = productTagRepository.save(productTag);
        return productTagMapper.toDto(productTag);
    }

    /**
     * Get all the productTags.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductTagDTO> findAll() {
        log.debug("Request to get all ProductTags");
        return productTagRepository.findAll().stream()
            .map(productTagMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one productTag by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProductTagDTO findOne(Long id) {
        log.debug("Request to get ProductTag : {}", id);
        ProductTag productTag = productTagRepository.findOne(id);
        return productTagMapper.toDto(productTag);
    }

    /**
     * Delete the productTag by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductTag : {}", id);
        productTagRepository.delete(id);
    }

	@Override
	public List<Long> findProductTagsByTagName(List<String> tagName) {
		return productTagRepository.findByTagNameIn(tagName).stream()
	            .map(ProductTag::getId)
	            .collect(Collectors.toList());
	}
}
