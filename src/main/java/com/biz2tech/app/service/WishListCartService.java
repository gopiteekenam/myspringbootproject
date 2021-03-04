package com.biz2tech.app.service;

import java.util.List;
import java.util.Optional;

import com.biz2tech.app.service.dto.WishListCartDTO;

/**
 * Service Interface for managing WishListCart.
 */
public interface WishListCartService {

	/**
	 * Save a wishListCart.
	 *
	 * @param wishListCartDTO the entity to save
	 * @return the persisted entity
	 */
	WishListCartDTO save(WishListCartDTO wishListCartDTO);

	/**
	 * Get all the wishListCarts.
	 *
	 * @return the list of entities
	 */
	List<WishListCartDTO> findAll();

	/**
	 * Get the "id" wishListCart.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	Optional<WishListCartDTO> findOne(Long id);

	/**
	 * Delete the "id" wishListCart.
	 *
	 * @param id       the id of the entity
	 * @param userName
	 */
	void delete(Long id, String userName);

	/**
	 * <p>
	 * To Get list of wishlist carts for logged in user
	 * 
	 * @param userName
	 * @return WishListCartDTO
	 */
	WishListCartDTO findWishListCartByUserName(String userName);

	Optional<WishListCartDTO> findByIdAndUser(Long id, String userName);
}
