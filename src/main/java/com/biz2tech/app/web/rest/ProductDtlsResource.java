package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.biz2tech.app.service.dto.ProductDtlsDTO;
import com.biz2tech.app.service.dto.ProductSearchDTO;
import com.biz2tech.app.service.dto.ProductSearchPossibilitiesDTO;
import com.biz2tech.app.service.dto.ProductSearchResultsDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing ProductDtls.
 */
@RestController
@RequestMapping("/api")
public class ProductDtlsResource {

	private final Logger log = LoggerFactory.getLogger(ProductDtlsResource.class);

	private static final String ENTITY_NAME = "productDtls";

	private final ProductDtlsService productDtlsService;

	public ProductDtlsResource(ProductDtlsService productDtlsService) {
		this.productDtlsService = productDtlsService;
	}

	/**
	 * POST /product-dtls : Create a new productDtls.
	 *
	 * @param productDtlsDTO the productDtlsDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         productDtlsDTO, or with status 400 (Bad Request) if the productDtls
	 *         has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/product-dtls")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<ProductDtlsDTO> createProductDtls(@RequestBody ProductDtlsDTO productDtlsDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to save ProductDtls : {}", productDtlsDTO);
		if (productDtlsDTO.getId() != null) {
			throw new BadRequestAlertException("A new productDtls cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ProductDtlsDTO result = productDtlsService.save(productDtlsDTO);
		return ResponseEntity.created(new URI("/api/product-dtls/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /product-dtls : Updates an existing productDtls.
	 *
	 * @param productDtlsDTO the productDtlsDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         productDtlsDTO, or with status 400 (Bad Request) if the
	 *         productDtlsDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the productDtlsDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/product-dtls")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<ProductDtlsDTO> updateProductDtls(@RequestBody ProductDtlsDTO productDtlsDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to update ProductDtls : {}", productDtlsDTO);
		if (productDtlsDTO.getId() == null) {
			return createProductDtls(productDtlsDTO, authorization);
		}
		ProductDtlsDTO result = productDtlsService.save(productDtlsDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productDtlsDTO.getId().toString()))
				.body(result);
	}

	/**
	 * To get the product by id
	 * 
	 * @param productIds
	 * @return ProductDtlsDTO
	 */

	@GetMapping("/get-product-dtls-by-id/{id}")
	@Timed
	public ProductDtlsDTO getProductDtlsById(@PathVariable(name = "id") long id) {
		log.debug("REST request to get ProductDtls by its Ids: " + id);
		return productDtlsService.findOne(id);
	}

	@GetMapping("/get-product-dtls-by-ids")
	@Timed
	public List<ProductDtlsDTO> getProductDtlsByProductId(@RequestParam("productIds") List<Long> productIds) {
		log.debug("REST request to get ProductDtls by its Ids: " + productIds);
		return productDtlsService.findByProductIds(productIds);
	}

	/**
	 * obtain details by product details by its inventory ID
	 * 
	 * @param invId
	 * @return
	 */
	@GetMapping("/get-product-dtls-by-title")
	@Timed
	public List<ProductDtlsDTO> getProductDtlsByProductName(
			@RequestParam("prdtTitle") String prdtTitle) {
		log.debug("REST request to get all ProductDtls by Product Title");
		return productDtlsService.findByPrdtTitle(prdtTitle);
	}

	/**
	 * obtain details by product details by its inventory ID
	 * 
	 * @param invId
	 * @return
	 */
	@GetMapping("/get-product-dtls-by-inventory-id")
	@Timed
	public List<ProductDtlsDTO> getProductDtlsByItemId(
			@RequestParam("inventoryIdentifiers") List<String> inventoryIdentifiers) {
		log.debug("REST request to get all ProductDtls");
		return productDtlsService.findOneByInventoryIdentifier(inventoryIdentifiers);
	}

	/**
	 * DELETE /product-dtls/:id : delete the "id" productDtls.
	 *
	 * @param id the id of the productDtlsDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	/*
	 * @DeleteMapping("/product-dtls/{id}")
	 * 
	 * @Timed public ResponseEntity<Void> deleteProductDtls(@PathVariable Long id) {
	 * log.debug("REST request to delete ProductDtls : {}", id);
	 * productDtlsService.delete(id); return ResponseEntity.ok()
	 * .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
	 * id.toString())).build(); }
	 */

	@GetMapping("/product-dtls")
	@Timed
	public ProductSearchResultsDTO getProductDtlsBySearchCrieteria(@ModelAttribute ProductSearchDTO productSearchDTO) {
		ProductSearchResultsDTO productSearchResultsDTO = productDtlsService
				.findAllByProductSearchCrieteria(productSearchDTO);
//    productSearchResultsDTO.setPageNumber(1L);
		return productSearchResultsDTO;
	}

	@GetMapping("/get-allbrands-with-count")
	@Timed
	public Map<String,Long> getBrandwithCount() {
		Map<String,Long> brandsWithCount= productDtlsService.getbrandsWithCount();
		return brandsWithCount;
	}

	@GetMapping("/get-product-price-range-by-search-crieteria")
	@Timed
	public List<ProductDtlsDTO> getProductPriceRanceBySearchCrieteria(@RequestParam("low") Double low,  @RequestParam("high") Double high) {
		return productDtlsService.getProductPriceRangeBySearchCrieteria(low,high);
	}
	
	@GetMapping("/get-product-price-min-max")
	@Timed
	public Map<Double, Double> getProductPriceRanceBySearchCrieteria() {
		return productDtlsService.getProductPriceMinandMax();
	}

	@GetMapping("/get-product-discount-min-max")
	@Timed
	public Map<Double, Double> getProductDiscountRanceBySearchCrieteria() {
		return productDtlsService.getProductDiscountMinandMax();
	}
	
	@GetMapping("/get-product-discount-range-by-search-crieteria")
	@Timed
	public List<ProductDtlsDTO> getProductDiscountRanceBySearchCrieteria(@RequestParam("low") Double low,  @RequestParam("high") Double high) {
		return productDtlsService.getProductDiscountRangeBySearchCrieteria(low,high);
	}

	@GetMapping("/get-product-search-possibilities")
	@Timed
	public ProductSearchPossibilitiesDTO getProductSearchPossibilities() {
		log.debug("REST request to get all ProductDtls");
		return productDtlsService.getProductSearchPossibilities();
	}
	
	/**
	 * Adbance search to get list of products based on the search by title, description, brand, type and category
	 * @param search
	 * @return List<ProductDtlsDTO>
	 */
	@GetMapping("/advancesearch")
	@Timed
	public List<ProductDtlsDTO> getAllProductsByAdvanceSearch(@RequestParam("search") String search) {
		log.debug("REST request to get all ProductDtls by advance search");
		return productDtlsService.getAllProductsByAdvanceSearch(search);
	}
	
	/**
	 * To get list of products based on color category
	 * @param categoryName
	 * @return List<ProductDtlsDTO>
	 */
	@GetMapping("/get-product-dtls-by-color-category")
	@Timed
	public List<ProductDtlsDTO> findByColorCategory_CategoryNameIn(@RequestParam("categoryName") String categoryName){
		log.debug("REST request to get all ProductDtls by color category");
		return productDtlsService.findByColorCategory_CategoryNameIn(categoryName);
	}
	
}
