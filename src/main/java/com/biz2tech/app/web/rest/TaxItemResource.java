package com.biz2tech.app.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing TaxItem.
 */
@RestController
@RequestMapping("/api")
public class TaxItemResource {
  /*
   * 
   * private final Logger log = LoggerFactory.getLogger(TaxItemResource.class);
   * 
   * private static final String ENTITY_NAME = "taxItem";
   * 
   * private final TaxItemService taxItemService;
   * 
   * public TaxItemResource(TaxItemService taxItemService) { this.taxItemService = taxItemService; }
   * 
   *//**
      * POST /tax-items : Create a new taxItem.
      *
      * @param taxItemDTO the taxItemDTO to create
      * @return the ResponseEntity with status 201 (Created) and with body the new taxItemDTO, or
      *         with status 400 (Bad Request) if the taxItem has already an ID
      * @throws URISyntaxException if the Location URI syntax is incorrect
      */
  /*
   * @PostMapping("/tax-items")
   * 
   * @Timed public ResponseEntity<TaxItemDTO> createTaxItem(@RequestBody TaxItemDTO taxItemDTO)
   * throws URISyntaxException { log.debug("REST request to save TaxItem : {}", taxItemDTO); if
   * (taxItemDTO.getId() != null) { throw new
   * BadRequestAlertException("A new taxItem cannot already have an ID", ENTITY_NAME, "idexists"); }
   * TaxItemDTO result = taxItemService.save(taxItemDTO); return ResponseEntity.created(new
   * URI("/api/tax-items/" + result.getId()))
   * .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
   * .body(result); }
   * 
   *//**
      * PUT /tax-items : Updates an existing taxItem.
      *
      * @param taxItemDTO the taxItemDTO to update
      * @return the ResponseEntity with status 200 (OK) and with body the updated taxItemDTO, or
      *         with status 400 (Bad Request) if the taxItemDTO is not valid, or with status 500
      *         (Internal Server Error) if the taxItemDTO couldn't be updated
      * @throws URISyntaxException if the Location URI syntax is incorrect
      */
  /*
   * @PutMapping("/tax-items")
   * 
   * @Timed public ResponseEntity<TaxItemDTO> updateTaxItem(@RequestBody TaxItemDTO taxItemDTO)
   * throws URISyntaxException { log.debug("REST request to update TaxItem : {}", taxItemDTO); if
   * (taxItemDTO.getId() == null) { return createTaxItem(taxItemDTO); } TaxItemDTO result =
   * taxItemService.save(taxItemDTO); return ResponseEntity.ok()
   * .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taxItemDTO.getId().toString()))
   * .body(result); }
   * 
   *//**
      * GET /tax-items : get all the taxItems.
      *
      * @return the ResponseEntity with status 200 (OK) and the list of taxItems in body
      */
  /*
   * @GetMapping("/tax-items")
   * 
   * @Timed public List<TaxItemDTO> getAllTaxItems() {
   * log.debug("REST request to get all TaxItems"); return taxItemService.findAll(); }
   * 
   *//**
      * GET /tax-items/:id : get the "id" taxItem.
      *
      * @param id the id of the taxItemDTO to retrieve
      * @return the ResponseEntity with status 200 (OK) and with body the taxItemDTO, or with status
      *         404 (Not Found)
      */
  /*
   * @GetMapping("/tax-items/{id}")
   * 
   * @Timed public ResponseEntity<TaxItemDTO> getTaxItem(@PathVariable Long id) {
   * log.debug("REST request to get TaxItem : {}", id); TaxItemDTO taxItemDTO =
   * taxItemService.findOne(id); return
   * ResponseUtil.wrapOrNotFound(Optional.ofNullable(taxItemDTO)); }
   * 
   *//**
      * DELETE /tax-items/:id : delete the "id" taxItem.
      *
      * @param id the id of the taxItemDTO to delete
      * @return the ResponseEntity with status 200 (OK)
      */
  /*
   * @DeleteMapping("/tax-items/{id}")
   * 
   * @Timed public ResponseEntity<Void> deleteTaxItem(@PathVariable Long id) {
   * log.debug("REST request to delete TaxItem : {}", id); taxItemService.delete(id); return
   * ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
   * id.toString())).build(); }
   */}
