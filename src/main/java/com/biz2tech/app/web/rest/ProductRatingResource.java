package com.biz2tech.app.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing ProductRating.
 */
@RestController
@RequestMapping("/api")
public class ProductRatingResource {
  /*
   * 
   * private final Logger log = LoggerFactory.getLogger(ProductRatingResource.class);
   * 
   * private static final String ENTITY_NAME = "fragrancenetserviceProductRating";
   * 
   * private final ProductRatingService productRatingService;
   * 
   * public ProductRatingResource(ProductRatingService productRatingService) {
   * this.productRatingService = productRatingService; }
   * 
   *//**
      * POST /product-ratings : Create a new productRating.
      *
      * @param productRatingDTO the productRatingDTO to create
      * @return the ResponseEntity with status 201 (Created) and with body the new productRatingDTO,
      *         or with status 400 (Bad Request) if the productRating has already an ID
      * @throws URISyntaxException if the Location URI syntax is incorrect
      */
  /*
   * @PostMapping("/product-ratings")
   * 
   * @Timed public ResponseEntity<ProductRatingDTO> createProductRating(
   * 
   * @RequestBody ProductRatingDTO productRatingDTO) throws URISyntaxException {
   * log.debug("REST request to save ProductRating : {}", productRatingDTO); if
   * (productRatingDTO.getId() != null) { throw new
   * BadRequestAlertException("A new productRating cannot already have an ID", ENTITY_NAME,
   * "idexists"); } ProductRatingDTO result = productRatingService.save(productRatingDTO); return
   * ResponseEntity.created(new URI("/api/product-ratings/" + result.getId()))
   * .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
   * .body(result); }
   * 
   *//**
      * PUT /product-ratings : Updates an existing productRating.
      *
      * @param productRatingDTO the productRatingDTO to update
      * @return the ResponseEntity with status 200 (OK) and with body the updated productRatingDTO,
      *         or with status 400 (Bad Request) if the productRatingDTO is not valid, or with
      *         status 500 (Internal Server Error) if the productRatingDTO couldn't be updated
      * @throws URISyntaxException if the Location URI syntax is incorrect
      */
  /*
   * @PutMapping("/product-ratings")
   * 
   * @Timed public ResponseEntity<ProductRatingDTO> updateProductRating(
   * 
   * @RequestBody ProductRatingDTO productRatingDTO) throws URISyntaxException {
   * log.debug("REST request to update ProductRating : {}", productRatingDTO); if
   * (productRatingDTO.getId() == null) { throw new BadRequestAlertException("Invalid id",
   * ENTITY_NAME, "idnull"); } ProductRatingDTO result =
   * productRatingService.save(productRatingDTO); return ResponseEntity.ok() .headers(
   * HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productRatingDTO.getId().toString()))
   * .body(result); }
   * 
   *//**
      * GET /product-ratings : get all the productRatings.
      *
      * @param filter the filter of the request
      * @return the ResponseEntity with status 200 (OK) and the list of productRatings in body
      */
  /*
   * @GetMapping("/product-ratings")
   * 
   * @Timed public List<ProductRatingDTO> getAllProductRatings() {
   * 
   * log.debug("REST request to get all ProductRatings"); return productRatingService.findAll(); }
   * 
   *//**
      * GET /product-ratings/:id : get the "id" productRating.
      *
      * @param id the id of the productRatingDTO to retrieve
      * @return the ResponseEntity with status 200 (OK) and with body the productRatingDTO, or with
      *         status 404 (Not Found)
      */
  /*
   * @GetMapping("/product-ratings/{id}")
   * 
   * @Timed public ResponseEntity<ProductRatingDTO> getProductRating(@PathVariable Long id) {
   * log.debug("REST request to get ProductRating : {}", id); Optional<ProductRatingDTO>
   * productRatingDTO = productRatingService.findOne(id); return
   * ResponseUtil.wrapOrNotFound(productRatingDTO); }
   * 
   *//**
      * DELETE /product-ratings/:id : delete the "id" productRating.
      *
      * @param id the id of the productRatingDTO to delete
      * @return the ResponseEntity with status 200 (OK)
      */
  /*
   * @DeleteMapping("/product-ratings/{id}")
   * 
   * @Timed public ResponseEntity<Void> deleteProductRating(@PathVariable Long id) {
   * log.debug("REST request to delete ProductRating : {}", id); productRatingService.delete(id);
   * return ResponseEntity.ok() .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
   * id.toString())).build(); }
   */}
