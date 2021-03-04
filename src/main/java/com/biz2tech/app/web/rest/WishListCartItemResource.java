package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.biz2tech.app.service.WishListCartItemService;
import com.biz2tech.app.service.dto.WishListCartItemDTO;
import com.biz2tech.app.service.util.LoggedInUserUtil;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * <p>
 * Controller implementation for WishlistResource
 * </p>
 * 
 * @author Gopi_Teekenam
 *
 */

@RestController
@RequestMapping("/api")
public class WishListCartItemResource {

	private final Logger log = LoggerFactory.getLogger(WishListCartItemResource.class);

	private static final String ENTITY_NAME = "fragrancenetserviceWishListCartItem";

	private final WishListCartItemService wishListCartItemService;
	private TokenProvider tokenProvider;

	public WishListCartItemResource(WishListCartItemService wishListCartItemService, TokenProvider tokenProvider) {
		this.wishListCartItemService = wishListCartItemService;
		this.tokenProvider = tokenProvider;
		;
	}
	
	/**
	 * <p>To  create wishlistcart items in a selected wishlist cart </p>
	 * @param wishListCartItemDTO
	 * @param authorization
	 * @return ResponseEntity<WishListCartItemDTO>
	 * @throws URISyntaxException
	 */

	@PostMapping("/wish-list-cart-items")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<WishListCartItemDTO> createWishListCartItem(
			@RequestBody WishListCartItemDTO wishListCartItemDTO, @RequestHeader("Authorization") String authorization)
			throws URISyntaxException {
		log.debug("REST request to save WishListCartItem : {}", wishListCartItemDTO);
		if (wishListCartItemDTO.getId() != null) {
			throw new BadRequestAlertException("A new wishListCartItem cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		WishListCartItemDTO result = wishListCartItemService.save(wishListCartItemDTO);
		return ResponseEntity.created(new URI("/api/wish-list-cart-items/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}
	
	/**
	 * <p>
	 * To update existing with list cart items of selected wishlist
	 * </p>
	 * 
	 * @param wishListCartItemDTO
	 * @param authorization
	 * @return ResponseEntity<WishListCartItemDTO>
	 * @throws URISyntaxException
	 */
	@PutMapping("/wish-list-cart-items")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<WishListCartItemDTO> updateWishListCartItem(
			@RequestBody WishListCartItemDTO wishListCartItemDTO, @RequestHeader("Authorization") String authorization)
			throws URISyntaxException {
		log.debug("REST request to update WishListCartItem : {}", wishListCartItemDTO);
		if (wishListCartItemDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		WishListCartItemDTO result = wishListCartItemService.save(wishListCartItemDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wishListCartItemDTO.getId().toString()))
				.body(result);
	}
	

	/**
	 * <p>Delete wishlistcartitem by id</p>
	 * @param id
	 * @param authorization
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping("/wish-list-cart-items/{id}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<Void> deleteWishListCartItem(@PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to delete WishListCartItem : {}", id);
		wishListCartItemService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * <p>To get list of Wishlist cart items for selected wishlistcart based on wishlistcartitem id
	 * @param id
	 * @param authorization
	 * @return
	 */
	@GetMapping("/wish-list-cart-items-by-wish-list-id/{wishListCartId}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public List<WishListCartItemDTO> getWishListCartItems(@PathVariable Long wishListCartId, @RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get WishListCartItems by wishlist cart id");
		String userName = LoggedInUserUtil.getLoggedInUser(tokenProvider, authorization);
		List<WishListCartItemDTO> lstWishListCartItem = wishListCartItemService.findByWishListCartId(wishListCartId);
		return lstWishListCartItem;
	}
	
	/**
	 * <p>To get list of Wishlist cart items for selected Wishlistcart based on wishlistcartitem id
	 * @param id
	 * @param authorization
	 * @return
	 */
	@GetMapping("/wish-list-cart-items/{id}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<WishListCartItemDTO> getWishListCartItem(@PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get WishListCartItem : {}", id);
		Optional<WishListCartItemDTO> wishListCartItemDTO = wishListCartItemService.findOne(id);
		return ResponseUtil.wrapOrNotFound(wishListCartItemDTO);
	}

	/**
	 * <p>
	 * To get list of Wishlist cart items for selected Wishlistcart based on
	 * wishlistcartitem id
	 * </p>
	 * <p>
	 * Need to check if this method is defintely required or not
	 * </p>
	 * 
	 * @param authorization
	 * @return List<WishListCartItemDTO
	 */
	@GetMapping("/wish-list-cart-items")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public List<WishListCartItemDTO> getWishListCartItems(@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get WishListCartItems");
		List<WishListCartItemDTO> lstWishListCartItem = wishListCartItemService.findAll();
		return lstWishListCartItem;
	}
	
	/**
	 * <p>To get list of wishlist cart items for logged in user </p> 
	 * @param authorization
	 * @return WishListCartItemDTO
	 */
	@GetMapping("/get-logged-user-wish-list-cart-items")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public List<WishListCartItemDTO> getLoggedInUserWishListCartItem(
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get logged in user's WishListCartItem ");
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (StringUtils.isEmpty(userName)) {
			userName = tokenProvider.getAuthentication(authorization).getName();
		}

		return wishListCartItemService.findByUserName(userName);
	}
}
