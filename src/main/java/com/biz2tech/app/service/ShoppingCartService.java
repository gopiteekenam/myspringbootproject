package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.ShoppingCartDTO;

/**
 * Service Interface for managing ShoppingCart.
 */
public interface ShoppingCartService {

  /**
   * Save a shoppingCart.
   *
   * @param shoppingCartDTO the entity to save
   * @return the persisted entity
   */
  ShoppingCartDTO save(ShoppingCartDTO shoppingCartDTO);

  /**
   * Get all the shoppingCarts.
   *
   * @return the list of entities
   */
  List<ShoppingCartDTO> findAll();

  /**
   * Get the "id" shoppingCart.
   *
   * @param id the id of the entity
   * @return the entity
   */
  ShoppingCartDTO findOne(Long id);

  /**
   * Delete the "id" shoppingCart.
   *
   * @param id the id of the entity
   */
  void delete(Long id);

  ShoppingCartDTO findShoppingCartByUserName(String userName);
}
