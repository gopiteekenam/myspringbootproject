package com.biz2tech.app.service.impl;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.ShoppingCart;
import com.biz2tech.app.repository.ShoppingCartRepository;
import com.biz2tech.app.service.ShoppingCartService;
import com.biz2tech.app.service.dto.ShoppingCartDTO;
import com.biz2tech.app.service.mapper.ShoppingCartMapper;

/**
 * Service Implementation for managing ShoppingCart.
 */
@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

  private final ShoppingCartRepository shoppingCartRepository;
  private final ShoppingCartMapper shoppingCartMapper;

  public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
      ShoppingCartMapper shoppingCartMapper) {
    this.shoppingCartRepository = shoppingCartRepository;
    this.shoppingCartMapper = shoppingCartMapper;
  }

  /**
   * Save a shoppingCart.
   *
   * @param shoppingCartDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public ShoppingCartDTO save(ShoppingCartDTO shoppingCartDTO) {
    log.debug("Request to save ShoppingCart : {}", shoppingCartDTO);
    ShoppingCart shoppingCart = shoppingCartMapper.toEntity(shoppingCartDTO);
    shoppingCart = shoppingCartRepository.save(shoppingCart);
    return shoppingCartMapper.toDto(shoppingCart);
  }

  /**
   * Get all the shoppingCarts.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<ShoppingCartDTO> findAll() {
    log.debug("Request to get all ShoppingCarts");
    return shoppingCartRepository.findAll().stream().map(shoppingCartMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one shoppingCart by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public ShoppingCartDTO findOne(Long id) {
    log.debug("Request to get ShoppingCart : {}", id);
    ShoppingCart shoppingCart = shoppingCartRepository.findOne(id);
    return shoppingCartMapper.toDto(shoppingCart);
  }

  /**
   * Delete the shoppingCart by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete ShoppingCart : {}", id);
    shoppingCartRepository.delete(id);
  }

  @Override
  @Transactional
  public ShoppingCartDTO findShoppingCartByUserName(String userName) {
    log.debug("Request to get ShoppingCart : {}", userName);
    ShoppingCart shoppingCart = shoppingCartRepository.findByCreatedBy(userName);
    if (null == shoppingCart) {
      ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
      shoppingCartDTO.setDescription("automatically created to get user");
      shoppingCartDTO.setCreatedBy(userName);
      shoppingCartDTO.setLastUpdatedBy(userName);
      shoppingCartDTO.setCreatedOn(Instant.now());
      shoppingCartDTO.setLastUpdatedOn(Instant.now());
      shoppingCart = shoppingCartRepository.save(shoppingCartMapper.toEntity(shoppingCartDTO));
    }
    return shoppingCartMapper.toDto(shoppingCart);
  }
}
