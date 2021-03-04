package com.biz2tech.app.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.ShoppingCart;
import com.biz2tech.app.domain.ShoppingCartItem;
import com.biz2tech.app.repository.ShoppingCartItemRepository;
import com.biz2tech.app.repository.ShoppingCartRepository;
import com.biz2tech.app.service.ShoppingCartItemService;
import com.biz2tech.app.service.dto.ShoppingCartItemDTO;
import com.biz2tech.app.service.mapper.ShoppingCartItemMapper;

/**
 * Service Implementation for managing ShoppingCartItem.
 */
@Service
@Transactional
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

  private final Logger log = LoggerFactory.getLogger(ShoppingCartItemServiceImpl.class);

  private final ShoppingCartItemRepository shoppingCartItemRepository;
  private final ShoppingCartRepository shoppingCartRepository;

  private final ShoppingCartItemMapper shoppingCartItemMapper;

  public ShoppingCartItemServiceImpl(ShoppingCartItemRepository shoppingCartItemRepository,
      ShoppingCartItemMapper shoppingCartItemMapper,
      ShoppingCartRepository shoppingCartRepository) {
    this.shoppingCartItemRepository = shoppingCartItemRepository;
    this.shoppingCartItemMapper = shoppingCartItemMapper;
    this.shoppingCartRepository = shoppingCartRepository;
  }

  /**
   * Save a shoppingCartItem.
   *
   * @param shoppingCartItemDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public ShoppingCartItemDTO save(ShoppingCartItemDTO shoppingCartItemDTO) {
    log.debug("Request to save ShoppingCartItem : {}", shoppingCartItemDTO);
    ShoppingCartItem shoppingCartItem = shoppingCartItemMapper.toEntity(shoppingCartItemDTO);
    shoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
    return shoppingCartItemMapper.toDto(shoppingCartItem);
  }

  @Override
  public List<ShoppingCartItemDTO> save(List<ShoppingCartItemDTO> shoppingCartItemDTOList) {
    log.debug("Request to save ShoppingCartItem : {}", shoppingCartItemDTOList);
    List<ShoppingCartItemDTO> cartItemDTOs = new ArrayList<ShoppingCartItemDTO>();
    for (ShoppingCartItemDTO shoppingCartItemDTO : shoppingCartItemDTOList) {
      cartItemDTOs.add(save(shoppingCartItemDTO));
    }
    return cartItemDTOs;
  }

  /**
   * Get all the shoppingCartItems.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<ShoppingCartItemDTO> findAll() {
    log.debug("Request to get all ShoppingCartItems");
    return shoppingCartItemRepository.findAll().stream().map(shoppingCartItemMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ShoppingCartItemDTO> findByUserId(String userName) {
    log.debug("Request to get logged in user ShoppingCartItems");
    ShoppingCart shoppingCart = shoppingCartRepository.findByCreatedBy(userName);
    if (shoppingCart.getId() != null) {

      return shoppingCartItemRepository.findByShoppingCartId(shoppingCart.getId()).stream()
          .map(shoppingCartItemMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
    return null;
  }

  /**
   * Get one shoppingCartItem by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public ShoppingCartItemDTO findOne(Long id) {
    log.debug("Request to get ShoppingCartItem : {}", id);
    ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findOne(id);
    return shoppingCartItemMapper.toDto(shoppingCartItem);
  }

  /**
   * Delete the shoppingCartItem by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete ShoppingCartItem : {}", id);
    shoppingCartItemRepository.delete(id);
  }
}
