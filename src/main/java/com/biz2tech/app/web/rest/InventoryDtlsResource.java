package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
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
import com.biz2tech.app.service.InventoryDtlsService;
import com.biz2tech.app.service.dto.InventoryDtlsDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing InventoryDtls.
 */
@RestController
@RequestMapping("/api")
public class InventoryDtlsResource {

  private final Logger log = LoggerFactory.getLogger(InventoryDtlsResource.class);

  private static final String ENTITY_NAME = "inventoryDtls";

  private final InventoryDtlsService inventoryDtlsService;

  public InventoryDtlsResource(InventoryDtlsService inventoryDtlsService) {
    this.inventoryDtlsService = inventoryDtlsService;
  }

  /**
   * POST /inventory-dtls : Create a new inventoryDtls.
   *
   * @param inventoryDtlsDTO the inventoryDtlsDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new inventoryDtlsDTO, or
   *         with status 400 (Bad Request) if the inventoryDtls has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/inventory-dtls")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<InventoryDtlsDTO> createInventoryDtls(
      @RequestBody InventoryDtlsDTO inventoryDtlsDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to save InventoryDtls : {}", inventoryDtlsDTO);
    if (inventoryDtlsDTO.getId() != null) {
      throw new BadRequestAlertException("A new inventoryDtls cannot already have an ID",
          ENTITY_NAME, "idexists");
    }
    InventoryDtlsDTO result = inventoryDtlsService.save(inventoryDtlsDTO);
    return ResponseEntity.created(new URI("/api/inventory-dtls/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /inventory-dtls : Updates an existing inventoryDtls.
   *
   * @param inventoryDtlsDTO the inventoryDtlsDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated inventoryDtlsDTO, or
   *         with status 400 (Bad Request) if the inventoryDtlsDTO is not valid, or with status 500
   *         (Internal Server Error) if the inventoryDtlsDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/inventory-dtls")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<InventoryDtlsDTO> updateInventoryDtls(
      @RequestBody InventoryDtlsDTO inventoryDtlsDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to update InventoryDtls : {}", inventoryDtlsDTO);
    if (inventoryDtlsDTO.getId() == null) {
      return createInventoryDtls(inventoryDtlsDTO, authorization);
    }
    InventoryDtlsDTO result = inventoryDtlsService.save(inventoryDtlsDTO);
    return ResponseEntity.ok()
        .headers(
            HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, inventoryDtlsDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET /inventory-dtls : get all the inventoryDtls.
   *
   * @param filter the filter of the request
   * @return the ResponseEntity with status 200 (OK) and the list of inventoryDtls in body
   */
  /*
   * @GetMapping("/inventory-dtls")
   * 
   * @Timed public List<InventoryDtlsDTO> getAllInventoryDtls(@RequestParam(required = false) String
   * filter) { if ("productdtls-is-null".equals(filter)) {
   * log.debug("REST request to get all InventoryDtlss where productDtls is null"); return
   * inventoryDtlsService.findAllWhereProductDtlsIsNull(); }
   * log.debug("REST request to get all InventoryDtls"); return inventoryDtlsService.findAll(); }
   */

  /**
   * GET /inventory-dtls/:id : get the "id" inventoryDtls.
   *
   * @param id the id of the inventoryDtlsDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the inventoryDtlsDTO, or with
   *         status 404 (Not Found)
   */
  @GetMapping("/inventory-dtls/{id}")
  @Timed
  public ResponseEntity<InventoryDtlsDTO> getInventoryDtls(@PathVariable Long id) {
    log.debug("REST request to get InventoryDtls : {}", id);
    InventoryDtlsDTO inventoryDtlsDTO = inventoryDtlsService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(inventoryDtlsDTO));
  }

  /**
   * DELETE /inventory-dtls/:id : delete the "id" inventoryDtls.
   *
   * @param id the id of the inventoryDtlsDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  /*
   * @DeleteMapping("/inventory-dtls/{id}")
   * 
   * @Timed public ResponseEntity<Void> deleteInventoryDtls(@PathVariable Long id) {
   * log.debug("REST request to delete InventoryDtls : {}", id); inventoryDtlsService.delete(id);
   * return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
   * id.toString())).build(); }
   */
}
