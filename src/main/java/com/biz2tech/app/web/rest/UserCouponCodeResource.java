package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.security.AuthoritiesConstants;
import com.biz2tech.app.service.UserCouponCodeService;
import com.biz2tech.app.service.dto.UserCouponCodeDTO;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing User coupon code
 */
@RestController
@RequestMapping("/api")
public class UserCouponCodeResource {

	private final Logger log = LoggerFactory.getLogger(UserCouponCodeResource.class);

	private static final String ENTITY_NAME = "userCouponCode";

	private final UserCouponCodeService userCouponCodeService;

	public UserCouponCodeResource(UserCouponCodeService userCouponCodeService) {
		this.userCouponCodeService = userCouponCodeService;
	}

	/**
	 * POST /userCouponCode : Create a new user coupon code
	 *
	 * @param userCouponCodeDTO the userCouponCodeDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         userCouponCodeDTO, or with status 400 (Bad Request)
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/usercouponcodes")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<UserCouponCodeDTO> createUserCouponCode(@RequestBody UserCouponCodeDTO userCouponCodeDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to save UserCouponCode : {}", userCouponCodeDTO);
		UserCouponCodeDTO result = userCouponCodeService.save(userCouponCodeDTO);
		return ResponseEntity.created(new URI("/api/usercouponcode/" + result.getCouponCode()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getCouponCode())).body(result);
	}

	/**
	 * PUT /usercouponcode : Updates an existing user coupon code
	 *
	 * @param UserCouponCodeDTO the UserCouponCodeDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         UserCouponCodeDTO, or with status 400 (Bad Request) if the
	 *         UserCouponCodeDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the UserCouponCodeDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/usercouponcodes")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<UserCouponCodeDTO> updateUserCouponCode(@RequestBody UserCouponCodeDTO userCouponCodeDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to update UserCouponCode : {}", userCouponCodeDTO);
		UserCouponCodeDTO result = userCouponCodeService.save(userCouponCodeDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userCouponCodeDTO.getCouponCode()))
				.body(result);
	}

	/**
	 * GET /usercouponcodes : get all the user coupon codes.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of user coupon
	 *         codes in body
	 */
	@GetMapping("/usercouponcodes")
	@Timed
	public List<UserCouponCodeDTO> getAllUserCouponCodes() {
		log.debug("REST request to get all User Coupon Codes");
		List<UserCouponCodeDTO> lstUserCouponCodeDTO = userCouponCodeService.findAll();
		return lstUserCouponCodeDTO;
	}

	/**
	 * GET /usercouponcodes/:userid/:couponCode : get the "UserCouponCode " object
	 *
	 * @param id the id of the UserCouponCodeDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         UserCouponCodeDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/usercouponcodes/{userId}/{couponCode}")
	@Timed
	public ResponseEntity<Boolean> findOneByUserIdAndCouponCode(@PathVariable Long userId,
			@PathVariable String couponCode) {
		log.debug("REST request to get coupon codes by userid and couponcode : {}", userId, couponCode);
		boolean blncouponCode = userCouponCodeService.findOneByUserIdAndCouponCode(userId, couponCode);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(blncouponCode));
	}

	/**
	 * GET /usercouponcodes/:userId : get the "id" user coupon codes
	 *
	 * @param id the id of the UserCouponCodesDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         UserCouponCodesDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/usercouponcodes/{userId}")
	@Timed
	public List<UserCouponCodeDTO> findOneByUserIdIn(@PathVariable Long userId) {
		log.debug("REST request to get coupon codes by userid and couponcode : {}", userId);
		List<UserCouponCodeDTO> lstUserCouponCodeDTO = userCouponCodeService.findByUserIdIn(userId);
		return lstUserCouponCodeDTO;
	}

	/**
	 * GET /user_couponcodes/:couponCode : get the object
	 *
	 * @param id the id of the UserCouponCodeDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         UserCouponCodeDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/user_couponcodes/{couponCode}")
	@Timed
	public List<UserCouponCodeDTO> findOneByCouponCodeIn(@PathVariable String couponCode) {
		log.debug("REST request to get coupon codes by couponcode : {}", couponCode);
		List<UserCouponCodeDTO> lstUserCouponCodeDTO = userCouponCodeService.findByCouponCodeIn(couponCode);
		return lstUserCouponCodeDTO;
	}
}
