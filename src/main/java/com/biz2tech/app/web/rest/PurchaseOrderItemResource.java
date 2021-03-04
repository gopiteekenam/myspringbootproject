package com.biz2tech.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.biz2tech.app.service.PurchaseOrderItemService;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.biz2tech.app.service.dto.PurchaseOrderItemDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PurchaseOrderItem.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderItemResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemResource.class);

    private static final String ENTITY_NAME = "fragrancenetservicePurchaseOrderItem";

    private final PurchaseOrderItemService purchaseOrderItemService;

    public PurchaseOrderItemResource(PurchaseOrderItemService purchaseOrderItemService) {
        this.purchaseOrderItemService = purchaseOrderItemService;
    }

    /**
     * POST  /purchase-order-items : Create a new purchaseOrderItem.
     *
     * @param purchaseOrderItemDTO the purchaseOrderItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new purchaseOrderItemDTO, or with status 400 (Bad Request) if the purchaseOrderItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/purchase-order-items")
    @Timed
    public ResponseEntity<PurchaseOrderItemDTO> createPurchaseOrderItem(@Valid @RequestBody PurchaseOrderItemDTO purchaseOrderItemDTO) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrderItem : {}", purchaseOrderItemDTO);
        if (purchaseOrderItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new purchaseOrderItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderItemDTO result = purchaseOrderItemService.save(purchaseOrderItemDTO);
        return ResponseEntity.created(new URI("/api/purchase-order-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /purchase-order-items : Updates an existing purchaseOrderItem.
     *
     * @param purchaseOrderItemDTO the purchaseOrderItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated purchaseOrderItemDTO,
     * or with status 400 (Bad Request) if the purchaseOrderItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the purchaseOrderItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/purchase-order-items")
    @Timed
    public ResponseEntity<PurchaseOrderItemDTO> updatePurchaseOrderItem(@Valid @RequestBody PurchaseOrderItemDTO purchaseOrderItemDTO) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrderItem : {}", purchaseOrderItemDTO);
        if (purchaseOrderItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PurchaseOrderItemDTO result = purchaseOrderItemService.save(purchaseOrderItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseOrderItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /purchase-order-items : get all the purchaseOrderItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of purchaseOrderItems in body
     */
    @GetMapping("/purchase-order-items")
    @Timed
    public List<PurchaseOrderItemDTO> getAllPurchaseOrderItems() {
        log.debug("REST request to get all PurchaseOrderItems");
        return purchaseOrderItemService.findAll();
    }

    /**
     * GET  /purchase-order-items/:id : get the "id" purchaseOrderItem.
     *
     * @param id the id of the purchaseOrderItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the purchaseOrderItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/purchase-order-items/{id}")
    @Timed
    public ResponseEntity<PurchaseOrderItemDTO> getPurchaseOrderItem(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrderItem : {}", id);
        Optional<PurchaseOrderItemDTO> purchaseOrderItemDTO = purchaseOrderItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrderItemDTO);
    }

    /**
     * DELETE  /purchase-order-items/:id : delete the "id" purchaseOrderItem.
     *
     * @param id the id of the purchaseOrderItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/purchase-order-items/{id}")
    @Timed
    public ResponseEntity<Void> deletePurchaseOrderItem(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrderItem : {}", id);
        purchaseOrderItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
