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
import com.biz2tech.app.service.ProductColorService;
import com.biz2tech.app.service.dto.ProductColorDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ProductColor.
 */
@RestController
@RequestMapping("/api")
public class ProductColorResource {

  private final Logger log = LoggerFactory.getLogger(ProductColorResource.class);

  private static final String ENTITY_NAME = "ProductColor";

  private final ProductColorService productColorService;

  public ProductColorResource(ProductColorService productColorService) {
    this.productColorService = productColorService;
  }

  /**
   * POST /product-colors : Create a new productColor.
   *
   * @param productColorDTO the productColorDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new productColorDTO, or
   *         with status 400 (Bad Request) if the productColor has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/product-colors")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ProductColorDTO> createProductColor(
      @RequestBody ProductColorDTO productColorDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to save ProductColor : {}", productColorDTO);
    if (productColorDTO.getId() != null) {
      throw new BadRequestAlertException("A new productColor cannot already have an ID",
          ENTITY_NAME, "idexists");
    }
    ProductColorDTO result = productColorService.save(productColorDTO);
    return ResponseEntity.created(new URI("/api/product-colors/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /product-colors : Updates an existing productColor.
   *
   * @param productColorDTO the productColorDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated productColorDTO, or
   *         with status 400 (Bad Request) if the productColorDTO is not valid, or with status 500
   *         (Internal Server Error) if the productColorDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/product-colors")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ProductColorDTO> updateProductColor(
      @RequestBody ProductColorDTO productColorDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to update ProductColor : {}", productColorDTO);
    if (productColorDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    ProductColorDTO result = productColorService.save(productColorDTO);
    return ResponseEntity.ok()
        .headers(
            HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productColorDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET /product-colors : get all the productColors.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of productColors in body
   */
  
	@GetMapping("/product-colors")

	@Timed
	public List<ProductColorDTO> getAllProductColors() {
		log.debug("REST request to get all ProductColors");
		return productColorService.findAll();
	}

  /**
   * GET /product-colors/:id : get the "id" productColor.
   *
   * @param id the id of the productColorDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the productColorDTO, or with
   *         status 404 (Not Found)
   */
  @GetMapping("/product-colors/{id}")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ProductColorDTO> getProductColor(@PathVariable Long id,
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to get ProductColor : {}", id);
    Optional<ProductColorDTO> productColorDTO = productColorService.findOne(id);
    return ResponseUtil.wrapOrNotFound(productColorDTO);
  }

  /**
   * DELETE /product-colors/:id : delete the "id" productColor.
   *
   * @param id the id of the productColorDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  /*
   * @DeleteMapping("/product-colors/{id}")
   * 
   * @Timed public ResponseEntity<Void> deleteProductColor(@PathVariable Long id) {
   * log.debug("REST request to delete ProductColor : {}", id); productColorService.delete(id);
   * return ResponseEntity.ok() .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
   * id.toString())).build(); }
   */
}
