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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.security.AuthoritiesConstants;
import com.biz2tech.app.service.ProductDtlsService;
import com.biz2tech.app.service.ProductTagService;
import com.biz2tech.app.service.dto.ProductDtlsDTO;
import com.biz2tech.app.service.dto.ProductTagDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ProductTag.
 */
@RestController
@RequestMapping("/api")
public class ProductTagResource {

  private final Logger log = LoggerFactory.getLogger(ProductTagResource.class);

  private static final String ENTITY_NAME = "productTag";

  private final ProductTagService productTagService;
  
  private final ProductDtlsService productDtlsService;

  public ProductTagResource(ProductTagService productTagService,ProductDtlsService productDtlsService) {
    this.productTagService = productTagService;
    this.productDtlsService = productDtlsService;
    
  }

  /**
   * POST /product-tags : Create a new productTag.
   *
   * @param productTagDTO the productTagDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new productTagDTO, or
   *         with status 400 (Bad Request) if the productTag has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/product-tags")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ProductTagDTO> createProductTag(@RequestBody ProductTagDTO productTagDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to save ProductTag : {}", productTagDTO);
    if (productTagDTO.getId() != null) {
      throw new BadRequestAlertException("A new productTag cannot already have an ID", ENTITY_NAME,
          "idexists");
    }
    ProductTagDTO result = productTagService.save(productTagDTO);
    return ResponseEntity.created(new URI("/api/product-tags/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /product-tags : Updates an existing productTag.
   *
   * @param productTagDTO the productTagDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated productTagDTO, or
   *         with status 400 (Bad Request) if the productTagDTO is not valid, or with status 500
   *         (Internal Server Error) if the productTagDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/product-tags")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ProductTagDTO> updateProductTag(@RequestBody ProductTagDTO productTagDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to update ProductTag : {}", productTagDTO);
    if (productTagDTO.getId() == null) {
      return createProductTag(productTagDTO, authorization);
    }
    ProductTagDTO result = productTagService.save(productTagDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productTagDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET /product-tags : get all the productTags.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of productTags in body
   */
  @GetMapping("/product-tags")
//  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public List<ProductTagDTO> getAllProductTags() {
    log.debug("REST request to get all ProductTags");
    return productTagService.findAll();
  }

  /**
   * GET /product-tags/:id : get the "id" productTag.
   *
   * @param id the id of the productTagDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the productTagDTO, or with status
   *         404 (Not Found)
   */
  @GetMapping("/product-tags/{id}")
//  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ProductTagDTO> getProductTag(@PathVariable Long id) {
    log.debug("REST request to get ProductTag : {}", id);
    ProductTagDTO productTagDTO = productTagService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productTagDTO));
  }
  
  /**
   * GET /product-tags/:tagname.
   *
   * @param tagname of the productTagDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the productTagDTO, or with status
   *         404 (Not Found)
   */
	@GetMapping("/get-product-dtls-by-tag-name")
//	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public List<ProductDtlsDTO> getProductTagsbyTagName(@RequestParam(value = "pageNumber") int page,
			@RequestParam(value = "tagName") List<String> tagname) {
		log.debug("REST request to get ProductTag by tag name : {}", tagname);
		List<ProductTagDTO> lstProductTagDTO = null;
		List<ProductDtlsDTO> lstProductDtlsDTO = null;
		List<Long> productTagIds = null;
		if (tagname.contains("all")) {
			lstProductTagDTO = productTagService.findAll();
		} else {
			lstProductDtlsDTO= productDtlsService.findByProductTagNames(tagname);
		}
		
		return lstProductDtlsDTO;
	}

  /**
   * DELETE /product-tags/:id : delete the "id" productTag.
   *
   * @param id the id of the productTagDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  /*
   * @DeleteMapping("/product-tags/{id}")
   * 
   * @Timed public ResponseEntity<Void> deleteProductTag(@PathVariable Long id) {
   * log.debug("REST request to delete ProductTag : {}", id); productTagService.delete(id); return
   * ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
   * id.toString())).build(); }
   */
}
