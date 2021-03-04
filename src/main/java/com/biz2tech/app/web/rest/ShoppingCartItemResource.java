package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.biz2tech.app.service.ShoppingCartItemService;
import com.biz2tech.app.service.dto.ShoppingCartItemDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ShoppingCartItem.
 */
@RestController
@RequestMapping("/api")
public class ShoppingCartItemResource {

	private final Logger log = LoggerFactory.getLogger(ShoppingCartItemResource.class);

	private static final String ENTITY_NAME = "shoppingCartItem";

	private final ShoppingCartItemService shoppingCartItemService;
	private TokenProvider tokenProvider;

	public ShoppingCartItemResource(ShoppingCartItemService shoppingCartItemService, TokenProvider tokenProvider) {
		this.shoppingCartItemService = shoppingCartItemService;
		this.tokenProvider = tokenProvider;
	}

	/**
	 * POST /shopping-cart-items : Create a new shoppingCartItem.
	 *
	 * @param shoppingCartItemDTOList the shoppingCartItemDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         shoppingCartItemDTO, or with status 400 (Bad Request) if the
	 *         shoppingCartItem has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/shopping-cart-items")
//	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<List<ShoppingCartItemDTO>> createShoppingCartItems(
			@RequestBody List<ShoppingCartItemDTO> shoppingCartItemDTOList,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to save ShoppingCartItem : {}", shoppingCartItemDTOList);
		if (shoppingCartItemDTOList.stream().anyMatch(item -> item.getId() != null)) {
			throw new BadRequestAlertException("A new shoppingCartItem cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		List<ShoppingCartItemDTO> result = shoppingCartItemService.save(shoppingCartItemDTOList);
		String identifiers = result.stream().map(item -> item.getId().toString()).collect(Collectors.joining(","));

		return ResponseEntity.created(new URI("/api/shopping-cart-items/" + identifiers))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, identifiers)).body(result);
	}

	/**
	 * PUT /shopping-cart-items : Updates an existing shoppingCartItem.
	 *
	 * @param shoppingCartItemDTOList the shoppingCartItemDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         shoppingCartItemDTO, or with status 400 (Bad Request) if the
	 *         shoppingCartItemDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the shoppingCartItemDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/shopping-cart-items")
//	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<List<ShoppingCartItemDTO>> updateShoppingCartItems(
			@RequestBody List<ShoppingCartItemDTO> shoppingCartItemDTOList,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to update ShoppingCartItem : {}", shoppingCartItemDTOList);
		List<ShoppingCartItemDTO> result = shoppingCartItemService.save(shoppingCartItemDTOList);
		String identifiers = result.stream().map(item -> item.getId().toString()).collect(Collectors.joining(","));
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, identifiers)).body(result);
	}

	/**
	 * GET /shopping-cart-items : get all the shoppingCartItems.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         shoppingCartItems in body
	 */
	
	  @GetMapping("/shopping-cart-items")
	  
	  @Timed public List<ShoppingCartItemDTO> getAllShoppingCartItems() {
	  log.debug("REST request to get all ShoppingCartItems"); return
	  shoppingCartItemService.findAll(); }
	 
	/**
	 * GET /shopping-cart-items/:id : get the "id" shoppingCartItem.
	 *
	 * @param id the id of the shoppingCartItemDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         shoppingCartItemDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/shopping-cart-items/{id}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<ShoppingCartItemDTO> getShoppingCartItem(@PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get ShoppingCartItem : {}", id);
		ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartItemService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(shoppingCartItemDTO));
	}

	@GetMapping("/get-logged-user-shopping-cart-items")
//	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public List<ShoppingCartItemDTO> getLoggedInUserShoppingCartItem(
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get logged in user's ShoppingCart ");
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (StringUtils.isEmpty(userName)) {
			userName = tokenProvider.getAuthentication(authorization).getName();
		}

		return shoppingCartItemService.findByUserId(userName);
	}

	/**
	 * DELETE /shopping-cart-items/:id : delete the "id" shoppingCartItem.
	 *
	 * @param id the id of the shoppingCartItemDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/shopping-cart-items/{id}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<Void> deleteShoppingCartItem(@PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to delete ShoppingCartItem : {}", id);
		shoppingCartItemService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
