package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.security.AuthoritiesConstants;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.WishListCartService;
import com.biz2tech.app.service.dto.WishListCartDTO;
import com.biz2tech.app.service.util.LoggedInUserUtil;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing WishListCart.
 */
@Api(value = "wish-list-cart-resource")
@RestController
@RequestMapping("/api")
public class WishListCartResource {

	private final Logger log = LoggerFactory.getLogger(WishListCartResource.class);

	private static final String ENTITY_NAME = "fragrancenetserviceWishListCart";

	private final WishListCartService wishListCartService;
	private TokenProvider tokenProvider;

	/**
	 * Parameterized constructor with parameters wishListCartService and
	 * tokenProvider
	 * 
	 * @param wishListCartService
	 * @param tokenProvider
	 */
	public WishListCartResource(WishListCartService wishListCartService, TokenProvider tokenProvider) {
		this.wishListCartService = wishListCartService;
		this.tokenProvider = tokenProvider;
	}

	/**
	 * <p>
	 * To create new WishList for Logged in user
	 * </p>
	 * 
	 * @param wishListCartDTO
	 * @param authorization
	 * @return ResponseEntity<WishListCartDTO>
	 * @throws URISyntaxException
	 */
	@ApiOperation(value = "Create a new wishlist cart for logged in user", httpMethod = "POST", response = ResponseEntity.class)
	@PostMapping("/wish-list-carts")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<WishListCartDTO> createWishListCart(@RequestBody WishListCartDTO wishListCartDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to save WishListCart : {}", wishListCartDTO);
		if (wishListCartDTO.getId() != null) {
			throw new BadRequestAlertException("A new wishListCart cannot already have an ID", ENTITY_NAME, "idexists");
		}
		WishListCartDTO result = wishListCartService.save(wishListCartDTO);
		return ResponseEntity.created(new URI("/api/wish-list-carts/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * <p>
	 * To update the wishlist cart of logged in user
	 * </p>
	 * 
	 * @param wishListCartDTO
	 * @param authorization
	 * @return ResponseEntity<WishListCartDTO>
	 * @throws URISyntaxException
	 */

	@PutMapping("/wish-list-carts")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<WishListCartDTO> updateWishListCart(@RequestBody WishListCartDTO wishListCartDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to update WishListCart : {}", wishListCartDTO);
		if (wishListCartDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		WishListCartDTO result = wishListCartService.save(wishListCartDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wishListCartDTO.getId().toString()))
				.body(result);
	}

	/**
	 * <p>
	 * To Get wishlistcart by id
	 * 
	 * @param id
	 * @param authorization
	 * @return ResponseEntity<WishListCartDTO>
	 */
	@GetMapping("/wish-list-carts/{id}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<WishListCartDTO> getWishListCart(@PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get WishListCart : {}", id);
		String userName = LoggedInUserUtil.getLoggedInUser(tokenProvider, authorization);
		Optional<WishListCartDTO> wishListCartDTO = wishListCartService.findByIdAndUser(id, userName);
		return ResponseUtil.wrapOrNotFound(wishListCartDTO);
	}

	/**
	 * <p>
	 * To get the wishlists for logged in user
	 * </p>
	 * 
	 * @param authorization
	 * @return ResponseEntity<WishListCartDTO>
	 */
	@GetMapping("/wish-list-carts")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<WishListCartDTO> getLoggedInUserWishListCart(@RequestHeader("Authorization") String authorization) {

		log.debug("REST request to get logged in user's wishlist cart ");
		String userName = LoggedInUserUtil.getLoggedInUser(tokenProvider, authorization);
		WishListCartDTO wishListCartDTO = wishListCartService.findWishListCartByUserName(userName);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wishListCartDTO));
	}

	/**
	 * To delete the selected wishlist cart for logged in user
	 * 
	 * @param id
	 * @param authorization
	 * @return ResponseEntity<void>
	 */
	@DeleteMapping("/wish-list-carts/{id}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<Void> deleteWishListCart(@PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to delete WishListCart : {}", id);
		String userName = LoggedInUserUtil.getLoggedInUser(tokenProvider, authorization);
		wishListCartService.delete(id, userName);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}