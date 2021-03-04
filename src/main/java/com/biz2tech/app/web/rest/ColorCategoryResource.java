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
import com.biz2tech.app.service.ColorCategoryService;
import com.biz2tech.app.service.ProductDtlsService;
import com.biz2tech.app.service.dto.ColorCategoryDTO;
import com.biz2tech.app.service.dto.ProductDtlsDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Color Catalog.
 */
@RestController
@RequestMapping("/api")
public class ColorCategoryResource {

  private final Logger log = LoggerFactory.getLogger(ColorCategoryResource.class);

  private static final String ENTITY_NAME = "colorCategory";

  private final ColorCategoryService colorCategoryService;
  
  private final ProductDtlsService productDtlsService;

  public ColorCategoryResource(ColorCategoryService colorCategoryService,ProductDtlsService productDtlsService) {
    this.colorCategoryService = colorCategoryService;
    this.productDtlsService = productDtlsService;
    
  }

  /**
   * POST /color-categories : Create a new Color Category.
   *
   * @param colorCategoryDTO the colorCategoryDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new colorCategoryDTO, or
   *         with status 400 (Bad Request) if the productTag has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/color-categories")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ColorCategoryDTO> createColorCategory(@RequestBody ColorCategoryDTO colorCategoryDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to save Color Category : {}", colorCategoryDTO);
    if (colorCategoryDTO.getId() != null) {
      throw new BadRequestAlertException("A new color category cannot already have an ID", ENTITY_NAME,
          "idexists");
    }
    ColorCategoryDTO result = colorCategoryService.save(colorCategoryDTO);
    return ResponseEntity.created(new URI("/api/color-categories/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /product-tags : Updates an existing productTag.
   *
   * @param ColorCategoryDTO the ColorCategoryDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated ColorCategoryDTO, or
   *         with status 400 (Bad Request) if the ColorCategoryDTO is not valid, or with status 500
   *         (Internal Server Error) if the ColorCategoryDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/color-categories")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<ColorCategoryDTO> updateColorCategory(@RequestBody ColorCategoryDTO ColorCategoryDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to update Color Category : {}", ColorCategoryDTO);
    if (ColorCategoryDTO.getId() == null) {
      return createColorCategory(ColorCategoryDTO, authorization);
    }
    ColorCategoryDTO result = colorCategoryService.save(ColorCategoryDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ColorCategoryDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET /color-categories : get all the productTags.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of productTags in body
   */
  @GetMapping("/color-categories")
  @Timed
  public List<ColorCategoryDTO> getAllColorCategories() {
    log.debug("REST request to get all Color Categories");
    return colorCategoryService.findAll();
  }

  /**
   * GET /color-categories/:id : get the "id" color category.
   *
   * @param id the id of the ColorCategoryDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the ColorCategoryDTO, or with status
   *         404 (Not Found)
   */
  @GetMapping("/color-categories/{id}")
  @Timed
  public ResponseEntity<ColorCategoryDTO> getColorCategory(@PathVariable Long id) {
    log.debug("REST request to get Color categories : {}", id);
    ColorCategoryDTO ColorCategoryDTO = colorCategoryService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ColorCategoryDTO));
  }
  
  /**
   * GET /color-categories/:categoryname.
   *
   * @param categoryname of the ColorCategoryDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the ColorCategoryDTO, or with status
   *         404 (Not Found)
   */
	@GetMapping("/color-categories-by-categoryname")
//	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public List<ProductDtlsDTO> getColorCategoriesByCategoryName(@RequestParam(value = "pageNumber") int page,
			@RequestParam(value = "categoryName") String categoryName) {
		log.debug("REST request to get Color category by category name : {}", categoryName);
		List<ProductDtlsDTO> lstProductDtlsDTO = null;
		List<Long> productIds = null;
		productIds= colorCategoryService.findByCategoryName(categoryName);
		lstProductDtlsDTO = productDtlsService.findByProductIds(productIds);
		
		return lstProductDtlsDTO;
	}

  /**
   * DELETE /product-tags/:id : delete the "id" productTag.
   *
   * @param id the id of the ColorCategoryDTO to delete
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
