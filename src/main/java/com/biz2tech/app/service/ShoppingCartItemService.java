package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.ShoppingCartItemDTO;

/**
 * Service Interface for managing ShoppingCartItem.
 */
public interface ShoppingCartItemService {

  /**
   * Save a shoppingCartItem.
   *
   * @param shoppingCartItemDTO the entity to save
   * @return the persisted entity
   */
  ShoppingCartItemDTO save(ShoppingCartItemDTO shoppingCartItemDTO);

  /**
   * Get all the shoppingCartItems.
   *
   * @return the list of entities
   */
  List<ShoppingCartItemDTO> findAll();

  /**
   * Get the "id" shoppingCartItem.
   *
   * @param id the id of the entity
   * @return the entity
   */
  ShoppingCartItemDTO findOne(Long id);

  /**
   * Delete the "id" shoppingCartItem.
   *
   * @param id the id of the entity
   */
  void delete(Long id);

  List<ShoppingCartItemDTO> save(List<ShoppingCartItemDTO> shoppingCartItemDTO);

  List<ShoppingCartItemDTO> findByUserId(String UserId);
}
