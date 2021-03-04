package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.UserCouponCodeDTO;

/**
 * Service Interface for managing User coupon code
 */
public interface UserCouponCodeService {

	/**
	 * Save a user coupon code
	 *
	 * @param productTagDTO the entity to save
	 * @return the persisted entity
	 */
	UserCouponCodeDTO save(UserCouponCodeDTO userCouponCodeDTO);

	/**
	 * Get all user coupon codes.
	 *
	 * @return the list of entities
	 */
	List<UserCouponCodeDTO> findAll();

	List<UserCouponCodeDTO> findByUserIdIn(Long userId);

	List<UserCouponCodeDTO> findByCouponCodeIn(String couponCode);

	boolean findOneByUserIdAndCouponCode(Long userId, String couponCode);
}
