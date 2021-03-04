package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.security.AuthoritiesConstants;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.ShoppingCartService;
import com.biz2tech.app.service.dto.ShoppingCartDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ShoppingCart.
 */
@RestController
@RequestMapping("/api")
public class ShoppingCartResource {

  private final Logger log = LoggerFactory.getLogger(ShoppingCartResource.class);

  private static final String ENTITY_NAME = "shoppingCart";

  private final ShoppingCartService shoppingCartService;
  private TokenProvider tokenProvider;

  public ShoppingCartResource(ShoppingCartService shoppingCartService,
      TokenProvider tokenProvider) {
    this.shoppingCartService = shoppingCartService;
    this.tokenProvider = tokenProvider;
  }

  /**
   * POST /shopping-carts : Create a new shoppingCart.
   *
   * @param shoppingCartDTO the shoppingCartDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new shoppingCartDTO, or
   *         with status 400 (Bad Request) if the shoppingCart has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  // @PostMapping("/shopping-carts")
  // @Timed
  public ResponseEntity<ShoppingCartDTO> createShoppingCart(
      @RequestBody ShoppingCartDTO shoppingCartDTO) throws URISyntaxException {
    log.debug("REST request to save ShoppingCart : {}", shoppingCartDTO);
    if (shoppingCartDTO.getId() != null) {
      throw new BadRequestAlertException("A new shoppingCart cannot already have an ID",
          ENTITY_NAME, "idexists");
    }
    ShoppingCartDTO result = shoppingCartService.save(shoppingCartDTO);
    return ResponseEntity.created(new URI("/api/shopping-carts/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /shopping-carts : Updates an existing shoppingCart.
   *
   * @param shoppingCartDTO the shoppingCartDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated shoppingCartDTO, or
   *         with status 400 (Bad Request) if the shoppingCartDTO is not valid, or with status 500
   *         (Internal Server Error) if the shoppingCartDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/shopping-carts")
//  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ShoppingCartDTO> updateShoppingCart(
      @RequestBody ShoppingCartDTO shoppingCartDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to update ShoppingCart : {}", shoppingCartDTO);
    if (shoppingCartDTO.getId() == null) {
      return createShoppingCart(shoppingCartDTO);
    }
    ShoppingCartDTO result = shoppingCartService.save(shoppingCartDTO);
    return ResponseEntity.ok()
        .headers(
            HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shoppingCartDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET /shopping-carts : get all the shoppingCarts.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of shoppingCarts in body
   */
  /*
   * @GetMapping("/shopping-carts")
   * 
   * @Timed public List<ShoppingCartDTO> getAllShoppingCarts() {
   * log.debug("REST request to get all ShoppingCarts"); return shoppingCartService.findAll(); }
   */

  /**
   * GET /shopping-carts/:id : get the "id" shoppingCart.
   *
   * @param id the id of the shoppingCartDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the shoppingCartDTO, or with
   *         status 404 (Not Found)
   */
  @GetMapping("/shopping-carts/{id}")
//  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable Long id,
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to get ShoppingCart : {}", id);
    ShoppingCartDTO shoppingCartDTO = shoppingCartService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(shoppingCartDTO));
  }

  /**
   * DELETE /shopping-carts/:id : delete the "id" shoppingCart.
   *
   * @param id the id of the shoppingCartDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/shopping-carts/{id}")
//  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<Void> deleteShoppingCart(@PathVariable Long id,
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to delete ShoppingCart : {}", id);
    shoppingCartService.delete(id);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }

  /**
   * get shopping cart by logged in user
   *
   * @param id the id of the shoppingCartDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the shoppingCartDTO, or with
   *         status 404 (Not Found)
   */
  @GetMapping("/get-logged-user-shopping-cart")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ShoppingCartDTO> getLoggedInUserShoppingCart(
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to get logged in user's ShoppingCart ");
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    if (StringUtils.isEmpty(userName)) {
      userName = tokenProvider.getAuthentication(authorization).getName();
    }

    ShoppingCartDTO shoppingCartDTO = shoppingCartService.findShoppingCartByUserName(userName);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(shoppingCartDTO));
  }
}
