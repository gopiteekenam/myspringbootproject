package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.security.AuthoritiesConstants;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.PurchaseOrderService;
import com.biz2tech.app.service.dto.CreatePurchaseOrderDTO;
import com.biz2tech.app.service.dto.UpdatePurchaseOrderDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing PurchaseOrder.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderResource {

  private final Logger log = LoggerFactory.getLogger(PurchaseOrderResource.class);

  private static final String ENTITY_NAME = "fragrancenetservicePurchaseOrder";

  private final PurchaseOrderService purchaseOrderService;
  private TokenProvider tokenProvider;

  public PurchaseOrderResource(PurchaseOrderService purchaseOrderService,
      TokenProvider tokenProvider) {
    this.purchaseOrderService = purchaseOrderService;
    this.tokenProvider = tokenProvider;
  }

  /**
   * POST /purchase-orders : Create a new purchaseOrder.
   *
   * @param purchaseOrderDTO the purchaseOrderDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new purchaseOrderDTO, or
   *         with status 400 (Bad Request) if the purchaseOrder has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  /*
   * @PostMapping("/purchase-orders")
   * 
   * @Timed public ResponseEntity<CreatePurchaseOrderDTO> createPurchaseOrder(
   * 
   * @Valid @RequestBody CreatePurchaseOrderDTO purchaseOrderDTO) throws URISyntaxException {
   * log.debug("REST request to save PurchaseOrder : {}", purchaseOrderDTO); if
   * (purchaseOrderDTO.getId() != null) { throw new
   * BadRequestAlertException("A new purchaseOrder cannot already have an ID", ENTITY_NAME,
   * "idexists"); } CreatePurchaseOrderDTO result = purchaseOrderService.save(purchaseOrderDTO);
   * return ResponseEntity.created(new URI("/api/purchase-orders/" + result.getId()))
   * .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
   * .body(result); }
   */

  /**
   * PUT /purchase-orders : Updates an existing purchaseOrder.
   *
   * @param purchaseOrderDTO the purchaseOrderDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated purchaseOrderDTO, or
   *         with status 400 (Bad Request) if the purchaseOrderDTO is not valid, or with status 500
   *         (Internal Server Error) if the purchaseOrderDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  /*
   * @PutMapping("/purchase-orders")
   * 
   * @Timed public ResponseEntity<CreatePurchaseOrderDTO> updatePurchaseOrder(
   * 
   * @Valid @RequestBody CreatePurchaseOrderDTO purchaseOrderDTO) throws URISyntaxException {
   * log.debug("REST request to update PurchaseOrder : {}", purchaseOrderDTO); if
   * (purchaseOrderDTO.getId() == null) { throw new BadRequestAlertException("Invalid id",
   * ENTITY_NAME, "idnull"); } CreatePurchaseOrderDTO result =
   * purchaseOrderService.save(purchaseOrderDTO); return ResponseEntity.ok() .headers(
   * HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseOrderDTO.getId().toString()))
   * .body(result); }
   */

  /**
   * GET /purchase-orders : get all the purchaseOrders.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of purchaseOrders in body
   */
  /*
   * @GetMapping("/purchase-orders")
   * 
   * @Timed public List<CreatePurchaseOrderDTO> getAllPurchaseOrders() {
   * log.debug("REST request to get all PurchaseOrders"); return purchaseOrderService.findAll(); }
   */

  /**
   * GET /purchase-orders/:id : get the "id" purchaseOrder.
   *
   * @param id the id of the purchaseOrderDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the purchaseOrderDTO, or with
   *         status 404 (Not Found)
   */
  @GetMapping("/purchase-orders/{id}")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<CreatePurchaseOrderDTO> getPurchaseOrder(@PathVariable Long id,
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to get PurchaseOrder : {}", id);
    Optional<CreatePurchaseOrderDTO> purchaseOrderDTO = purchaseOrderService.findOne(id);
    return ResponseUtil.wrapOrNotFound(purchaseOrderDTO);
  }


  @GetMapping("/get-logged-in-user-purchase-orders")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public List<CreatePurchaseOrderDTO> getLoggedInUserPurchaseOrders(
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to get logged in user's purchase orders: ");
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    if (StringUtils.isEmpty(userName)) {
      userName = tokenProvider.getAuthentication(authorization).getName();
    }
    List<CreatePurchaseOrderDTO> purchaseOrderDTO = purchaseOrderService.findByUserName(userName);
    return purchaseOrderDTO;
  }


  /**
   * DELETE /purchase-orders/:id : delete the "id" purchaseOrder.
   *
   * @param id the id of the purchaseOrderDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  /*
   * @DeleteMapping("/purchase-orders/{id}")
   * 
   * @Timed public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
   * log.debug("REST request to delete PurchaseOrder : {}", id); purchaseOrderService.delete(id);
   * return ResponseEntity.ok() .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
   * id.toString())).build(); }
   */

  @Timed
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @PostMapping("/create-logged-user-purchase-order")
  public ResponseEntity<CreatePurchaseOrderDTO> createLoggedUserPurchaseOrder(
      @RequestHeader("Authorization") String authorization,
      @Valid @RequestBody CreatePurchaseOrderDTO purchaseOrderDTO) throws URISyntaxException {
    log.debug("REST request to save PurchaseOrder : {}", purchaseOrderDTO);
    if (purchaseOrderDTO.getId() != null
        || CollectionUtils.isEmpty(purchaseOrderDTO.getPurchaseOrderItemDTOs())
        || StringUtils.isBlank(purchaseOrderDTO.getDeliveryAddress())) {
      throw new BadRequestAlertException(
          "A new purchaseOrder cannot already have an ID or does not contain any order items or does not have delivery address",
          ENTITY_NAME, "idexists");
    }

    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    if (StringUtils.isEmpty(userName)) {
      userName = tokenProvider.getAuthentication(authorization).getName();
    }

    CreatePurchaseOrderDTO result =
        purchaseOrderService.createPurchaseOrder(purchaseOrderDTO, userName);
    return ResponseEntity.created(new URI("/api/purchase-orders/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  @Timed
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @PostMapping("/update-logged-user-purchase-order")
  public ResponseEntity<UpdatePurchaseOrderDTO> updateLoggedUserPurchaseOrder(
      @RequestHeader("Authorization") String authorization,
      @Valid @RequestBody UpdatePurchaseOrderDTO purchaseOrderDTO) throws URISyntaxException {
    log.debug("REST request to save PurchaseOrder : {}", purchaseOrderDTO);

    if (purchaseOrderDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    if (StringUtils.isEmpty(userName)) {
      userName = tokenProvider.getAuthentication(authorization).getName();
    }

    UpdatePurchaseOrderDTO result =
        purchaseOrderService.updatePurchaseOrder(purchaseOrderDTO, userName);

    return ResponseEntity.ok()
        .headers(
            HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseOrderDTO.getId().toString()))
        .body(result);

  }
}
