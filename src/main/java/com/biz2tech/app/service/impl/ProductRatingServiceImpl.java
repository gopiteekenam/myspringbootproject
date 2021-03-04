package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.ProductRating;
import com.biz2tech.app.repository.ProductRatingRepository;
import com.biz2tech.app.service.ProductRatingService;
import com.biz2tech.app.service.dto.ProductRatingDTO;
import com.biz2tech.app.service.mapper.ProductRatingMapper;

/**
 * Service Implementation for managing ProductRating.
 */
@Service
@Transactional
public class ProductRatingServiceImpl implements ProductRatingService {

  private final Logger log = LoggerFactory.getLogger(ProductRatingServiceImpl.class);

  private final ProductRatingRepository productRatingRepository;

  private final ProductRatingMapper productRatingMapper;

  public ProductRatingServiceImpl(ProductRatingRepository productRatingRepository,
      ProductRatingMapper productRatingMapper) {
    this.productRatingRepository = productRatingRepository;
    this.productRatingMapper = productRatingMapper;
  }

  /**
   * Save a productRating.
   *
   * @param productRatingDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public ProductRatingDTO save(ProductRatingDTO productRatingDTO) {
    log.debug("Request to save ProductRating : {}", productRatingDTO);
    ProductRating productRating = productRatingMapper.toEntity(productRatingDTO);
    productRating = productRatingRepository.save(productRating);
    return productRatingMapper.toDto(productRating);
  }

  /**
   * Get all the productRatings.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<ProductRatingDTO> findAll() {
    log.debug("Request to get all ProductRatings");
    return productRatingRepository.findAll().stream().map(productRatingMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }



  /**
   * Get one productRating by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<ProductRatingDTO> findOne(Long id) {
    log.debug("Request to get ProductRating : {}", id);
    return Optional.ofNullable(productRatingRepository.findOne(id)).map(productRatingMapper::toDto);
  }

  /**
   * Delete the productRating by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete ProductRating : {}", id);
    productRatingRepository.delete(id);
  }
}
