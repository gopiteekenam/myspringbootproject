package com.biz2tech.app.service;

import java.util.List;
import java.util.Optional;

import com.biz2tech.app.service.dto.WishListCartItemDTO;

/**
 * Service Interface for managing WishListCartItem.
 */
public interface WishListCartItemService {

  /**
   * Save a wishListCartItem.
   *
   * @param wishListCartItemDTO the entity to save
   * @return the persisted entity
   */
  WishListCartItemDTO save(WishListCartItemDTO wishListCartItemDTO);

  /**
   * Get all the wishListCartItems.
   *
   * @return the list of entities
   */
  List<WishListCartItemDTO> findAll();


  /**
   * Get the "id" wishListCartItem.
   *
   * @param id the id of the entity
   * @return the entity
   */
  Optional<WishListCartItemDTO> findOne(Long id);

  /**
   * Delete the "id" wishListCartItem.
   *
   * @param id the id of the entity
   */
  void delete(Long id);

  List<WishListCartItemDTO> findByUserName(String username);

List<WishListCartItemDTO> findByWishListCartId(Long wishListCartId);
}
