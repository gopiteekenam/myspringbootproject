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
import com.biz2tech.app.service.UserAddressService;
import com.biz2tech.app.service.dto.UserAddressDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserAddress.
 */
@RestController
@RequestMapping("/api")
public class UserAddressResource {

  private final Logger log = LoggerFactory.getLogger(UserAddressResource.class);

  private static final String ENTITY_NAME = "fragrancenetserviceUserAddress";

  private final UserAddressService userAddressService;
  private final TokenProvider tokenProvider;

  public UserAddressResource(UserAddressService userAddressService, TokenProvider tokenProvider) {
    this.userAddressService = userAddressService;
    this.tokenProvider = tokenProvider;
  }

  /**
   * POST /user-addresses : Create a new userAddress.
   *
   * @param userAddressDTO the userAddressDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new userAddressDTO, or
   *         with status 400 (Bad Request) if the userAddress has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/user-addresses")
  @Secured(value = {AuthoritiesConstants.USER})
  @Timed
  public ResponseEntity<UserAddressDTO> createUserAddress(
      @RequestBody UserAddressDTO userAddressDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to save UserAddress : {}", userAddressDTO);
    if (userAddressDTO.getId() != null) {
      throw new BadRequestAlertException("A new userAddress cannot already have an ID", ENTITY_NAME,
          "idexists");
    }
    if (userAddressDTO.getUserId() != null) {
      throw new BadRequestAlertException(
          "A new userAddress cannot user ID, token will determine user ID", ENTITY_NAME,
          "userIdexists");
    }
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    if (StringUtils.isEmpty(userName)) {
      userName = tokenProvider.getAuthentication(authorization).getName();
    }

    UserAddressDTO result = userAddressService.save(userAddressDTO, userName);
    return ResponseEntity.created(new URI("/api/user-addresses/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /user-addresses : Updates an existing userAddress.
   *
   * @param userAddressDTO the userAddressDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated userAddressDTO, or
   *         with status 400 (Bad Request) if the userAddressDTO is not valid, or with status 500
   *         (Internal Server Error) if the userAddressDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/user-addresses")
  @Secured(value = {AuthoritiesConstants.USER})
  @Timed
  public ResponseEntity<UserAddressDTO> updateUserAddress(
      @RequestBody UserAddressDTO userAddressDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to update UserAddress : {}", userAddressDTO);
    if (userAddressDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    if (StringUtils.isEmpty(userName)) {
      userName = tokenProvider.getAuthentication(authorization).getName();
    }
    UserAddressDTO result = userAddressService.save(userAddressDTO, userName);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userAddressDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET /user-addresses : get all the userAddresses.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of userAddresses in body
   */
  /*
   * @GetMapping("/user-addresses")
   * 
   * @Timed public List<UserAddressDTO> getAllUserAddresses() {
   * log.debug("REST request to get all UserAddresses"); return userAddressService.findAll(); }
   */

  /**
   * GET /user-addresses/:id : get the "id" userAddress.
   *
   * @param id the id of the userAddressDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the userAddressDTO, or with
   *         status 404 (Not Found)
   */
  @GetMapping("/user-addresses/{id}")
  @Secured(value = {AuthoritiesConstants.USER})
  @Timed
  public ResponseEntity<UserAddressDTO> getUserAddress(@PathVariable Long id,
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to get UserAddress : {}", id);
    Optional<UserAddressDTO> userAddressDTO = userAddressService.findOne(id);
    return ResponseUtil.wrapOrNotFound(userAddressDTO);
  }


  @GetMapping("/get-logged-in-user-addresses")
  @Secured(value = {AuthoritiesConstants.USER})
  @Timed
  public List<UserAddressDTO> getLoggedInUserAddresses(
      @RequestHeader("Authorization") String authorization) {

    log.debug("REST request to get logged in user's UserAddress ");
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    if (StringUtils.isEmpty(userName)) {
      userName = tokenProvider.getAuthentication(authorization).getName();
    }
    List<UserAddressDTO> userAddressDTOList = userAddressService.findByUserId(userName);
    return userAddressDTOList;
  }

  /**
   * DELETE /user-addresses/:id : delete the "id" userAddress.
   *
   * @param id the id of the userAddressDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/user-addresses/{id}")
  @Secured(value = {AuthoritiesConstants.USER})
  @Timed
  public ResponseEntity<Void> deleteUserAddress(@PathVariable Long id,
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to delete UserAddress : {}", id);
    userAddressService.delete(id);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }
}
