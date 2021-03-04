package com.biz2tech.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.UserCouponCode;
import com.biz2tech.app.domain.UserCouponCodeId;
import com.biz2tech.app.repository.UserCouponCodeRepository;
import com.biz2tech.app.service.UserCouponCodeService;
import com.biz2tech.app.service.dto.UserCouponCodeDTO;

/**
 * Service Implementation for managing Coupon Codes for each user
 */
@Service
@Transactional
public class UserCouponCodeServiceImpl implements UserCouponCodeService {

	private final Logger log = LoggerFactory.getLogger(UserCouponCodeServiceImpl.class);

	private final UserCouponCodeRepository userCouponCodeRepository;

	public UserCouponCodeServiceImpl(UserCouponCodeRepository userCouponCodeRepository) {
		this.userCouponCodeRepository = userCouponCodeRepository;

	}

	/**
	 * Save user coupon
	 *
	 * @param UserCouponCodeDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public UserCouponCodeDTO save(UserCouponCodeDTO userCouponCodeDTO) {
		log.debug("Request to save UserCouponCode : {}", userCouponCodeDTO);
		UserCouponCode userCouponCode = new UserCouponCode();
		UserCouponCodeId userCouponCodeId = new UserCouponCodeId();
		userCouponCodeId.setCouponCode(userCouponCodeDTO.getCouponCode());
		userCouponCodeId.setUserId(userCouponCodeDTO.getUserId());
		userCouponCode.setId(userCouponCodeId);
//		PurchaseOrderDsctCode purchaseOrderDsctCode = new PurchaseOrderDsctCode();
//		purchaseOrderDsctCode.setCode(userCouponCodeDTO.getCouponCode());
//		userCouponCode.setCouponCode(purchaseOrderDsctCode);
//		User user = new User();
//		user.setId(userCouponCodeDTO.getUserId());
//		userCouponCode.setUserId(user);
		userCouponCode = userCouponCodeRepository.save(userCouponCode);
		userCouponCodeDTO.setCouponCode(userCouponCode.getId().getCouponCode());
		userCouponCodeDTO.setUserId(userCouponCode.getId().getUserId());
		return userCouponCodeDTO;
	}

	/**
	 * Get one color category by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean findOneByUserIdAndCouponCode(Long userId, String couponCode) {
		log.debug("Request to get user and coupon by coupon code : {}", couponCode + userId);
		UserCouponCode userCouponCode = userCouponCodeRepository.findByUserIdAndCouponCodeIn(userId, couponCode);
		if (userCouponCode != null) {
			return true;
		}
		return false;
	}

	/**
	 * To get list of coupons for user
	 * 
	 * @return List<UserCouponCodeDTO>
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserCouponCodeDTO> findAll() {
		log.debug("Request to get all coupon codes");
		List<UserCouponCodeDTO> lstUserCouponCodeDTOs = new ArrayList<UserCouponCodeDTO>();
		List<UserCouponCode> lstUserCouponCode = userCouponCodeRepository.findAll();
		for (UserCouponCode userCouponCode : lstUserCouponCode) {
			UserCouponCodeDTO userCouponCodeDTO = new UserCouponCodeDTO();
			userCouponCodeDTO.setCouponCode(userCouponCode.getId().getCouponCode());
			userCouponCodeDTO.setUserId(userCouponCode.getId().getUserId());
			lstUserCouponCodeDTOs.add(userCouponCodeDTO);
		}
		return lstUserCouponCodeDTOs;
	}

	@Override
	public List<UserCouponCodeDTO> findByUserIdIn(Long userId) {
		log.debug("Request to get user and coupon by userId : {}", userId);
		List<UserCouponCodeDTO> lstUserCouponCodeDTO = new ArrayList<UserCouponCodeDTO>();
		List<UserCouponCode> lstUserCouponCode = userCouponCodeRepository.findByUserIdIn(userId);
		for (UserCouponCode userCouponCode : lstUserCouponCode) {
			UserCouponCodeDTO userCouponCodeDTO = new UserCouponCodeDTO();
			userCouponCodeDTO.setCouponCode(userCouponCode.getId().getCouponCode());
			userCouponCodeDTO.setUserId(userCouponCode.getId().getUserId());
			lstUserCouponCodeDTO.add(userCouponCodeDTO);
		}
		return lstUserCouponCodeDTO;
	}

	@Override
	public List<UserCouponCodeDTO> findByCouponCodeIn(String couponCode) {
		log.debug("Request to get user and coupon by coupon code : {}", couponCode);
		List<UserCouponCodeDTO> lstUserCouponCodeDTO = new ArrayList<UserCouponCodeDTO>();
		List<UserCouponCode> lstUserCouponCode = userCouponCodeRepository.findByCouponCodeIn(couponCode);
		for (UserCouponCode userCouponCode : lstUserCouponCode) {
			UserCouponCodeDTO userCouponCodeDTO = new UserCouponCodeDTO();
			userCouponCodeDTO.setCouponCode(userCouponCode.getId().getCouponCode());
			userCouponCodeDTO.setUserId(userCouponCode.getId().getUserId());
			lstUserCouponCodeDTO.add(userCouponCodeDTO);
		}
		return lstUserCouponCodeDTO;
	}
}