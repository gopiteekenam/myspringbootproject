package com.biz2tech.app.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.SellerDetailsService;

/**
 * REST controller for managing SellerDetails.
 */
@RestController
@RequestMapping("/api")
public class SellerDetailsResource {

	private TokenProvider tokenProvider;
	private final SellerDetailsService sellerDetailsService;

	public SellerDetailsResource(SellerDetailsService sellerDetailsService, TokenProvider tokenProvider) {
		this.sellerDetailsService = sellerDetailsService;
		this.tokenProvider = tokenProvider;

	}
  /*
   * 
   * private final Logger log = LoggerFactory.getLogger(SellerDetailsResource.class);
   * 
   * private static final String ENTITY_NAME = "sellerDetails";
   * 
   * private final SellerDetailsService sellerDetailsService;
   * 
   * public SellerDetailsResource(SellerDetailsService sellerDetailsService) {
   * this.sellerDetailsService = sellerDetailsService; }
   * 
   *//**
      * POST /seller-details : Create a new sellerDetails.
      *
      * @param sellerDetailsDTO the sellerDetailsDTO to create
      * @return the ResponseEntity with status 201 (Created) and with body the new sellerDetailsDTO,
      *         or with status 400 (Bad Request) if the sellerDetails has already an ID
      * @throws URISyntaxException if the Location URI syntax is incorrect
      */
  /*
   * @PostMapping("/seller-details")
   * 
   * @Timed public ResponseEntity<SellerDetailsDTO> createSellerDetails(@RequestBody
   * SellerDetailsDTO sellerDetailsDTO) throws URISyntaxException {
   * log.debug("REST request to save SellerDetails : {}", sellerDetailsDTO); if
   * (sellerDetailsDTO.getId() != null) { throw new
   * BadRequestAlertException("A new sellerDetails cannot already have an ID", ENTITY_NAME,
   * "idexists"); } SellerDetailsDTO result = sellerDetailsService.save(sellerDetailsDTO); return
   * ResponseEntity.created(new URI("/api/seller-details/" + result.getId()))
   * .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
   * .body(result); }
   * 
   *//**
      * PUT /seller-details : Updates an existing sellerDetails.
      *
      * @param sellerDetailsDTO the sellerDetailsDTO to update
      * @return the ResponseEntity with status 200 (OK) and with body the updated sellerDetailsDTO,
      *         or with status 400 (Bad Request) if the sellerDetailsDTO is not valid, or with
      *         status 500 (Internal Server Error) if the sellerDetailsDTO couldn't be updated
      * @throws URISyntaxException if the Location URI syntax is incorrect
      */
  /*
   * @PutMapping("/seller-details")
   * 
   * @Timed public ResponseEntity<SellerDetailsDTO> updateSellerDetails(@RequestBody
   * SellerDetailsDTO sellerDetailsDTO) throws URISyntaxException {
   * log.debug("REST request to update SellerDetails : {}", sellerDetailsDTO); if
   * (sellerDetailsDTO.getId() == null) { return createSellerDetails(sellerDetailsDTO); }
   * SellerDetailsDTO result = sellerDetailsService.save(sellerDetailsDTO); return
   * ResponseEntity.ok() .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
   * sellerDetailsDTO.getId().toString())) .body(result); }
   * 
   *//**
      * GET /seller-details : get all the sellerDetails.
      *
      * @return the ResponseEntity with status 200 (OK) and the list of sellerDetails in body
      */
  /*
   * @GetMapping("/seller-details")
   * 
   * @Timed public List<SellerDetailsDTO> getAllSellerDetails() {
   * log.debug("REST request to get all SellerDetails"); return sellerDetailsService.findAll(); }
   * 
   *//**
      * GET /seller-details/:id : get the "id" sellerDetails.
      *
      * @param id the id of the sellerDetailsDTO to retrieve
      * @return the ResponseEntity with status 200 (OK) and with body the sellerDetailsDTO, or with
      *         status 404 (Not Found)
      */
  /*
   * @GetMapping("/seller-details/{id}")
   * 
   * @Timed public ResponseEntity<SellerDetailsDTO> getSellerDetails(@PathVariable Long id) {
   * log.debug("REST request to get SellerDetails : {}", id); SellerDetailsDTO sellerDetailsDTO =
   * sellerDetailsService.findOne(id); return
   * ResponseUtil.wrapOrNotFound(Optional.ofNullable(sellerDetailsDTO)); }
   * 
   *//**
      * DELETE /seller-details/:id : delete the "id" sellerDetails.
      *
      * @param id the id of the sellerDetailsDTO to delete
      * @return the ResponseEntity with status 200 (OK)
      */
  /*
   * @DeleteMapping("/seller-details/{id}")
   * 
   * @Timed public ResponseEntity<Void> deleteSellerDetails(@PathVariable Long id) {
   * log.debug("REST request to delete SellerDetails : {}", id); sellerDetailsService.delete(id);
   * return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
   * id.toString())).build(); }
   */}
