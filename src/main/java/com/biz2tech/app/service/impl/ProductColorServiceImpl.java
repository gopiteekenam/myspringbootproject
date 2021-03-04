package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.ProductColor;
import com.biz2tech.app.repository.ProductColorRepository;
import com.biz2tech.app.service.ProductColorService;
import com.biz2tech.app.service.dto.ProductColorDTO;
import com.biz2tech.app.service.mapper.ProductColorMapper;

/**
 * Service Implementation for managing ProductColor.
 */
@Service
@Transactional
public class ProductColorServiceImpl implements ProductColorService {

  private final Logger log = LoggerFactory.getLogger(ProductColorServiceImpl.class);

  private final ProductColorRepository productColorRepository;

  private final ProductColorMapper productColorMapper;

  public ProductColorServiceImpl(ProductColorRepository productColorRepository,
      ProductColorMapper productColorMapper) {
    this.productColorRepository = productColorRepository;
    this.productColorMapper = productColorMapper;
  }

  /**
   * Save a productColor.
   *
   * @param productColorDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public ProductColorDTO save(ProductColorDTO productColorDTO) {
    log.debug("Request to save ProductColor : {}", productColorDTO);
    ProductColor productColor = productColorMapper.toEntity(productColorDTO);
    productColor = productColorRepository.save(productColor);
    return productColorMapper.toDto(productColor);
  }

  /**
   * Get all the productColors.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<ProductColorDTO> findAll() {
    log.debug("Request to get all ProductColors");
    return productColorRepository.findAll().stream().map(productColorMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one productColor by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<ProductColorDTO> findOne(Long id) {
    log.debug("Request to get ProductColor : {}", id);
    return Optional.ofNullable(productColorRepository.findOne(id)).map(productColorMapper::toDto);
  }

  /**
   * Delete the productColor by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete ProductColor : {}", id);
    productColorRepository.delete(id);
  }
}
