package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.domain.PurchaseOrderDsctCode;
import com.biz2tech.app.security.AuthoritiesConstants;
import com.biz2tech.app.service.PurchaseOrderDsctCodeService;
import com.biz2tech.app.service.dto.PurchaseOrderDsctCodeDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.errors.EmailAlreadyUsedException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing PurchaseOrderDsctCode.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderDsctCodeResource {

	private final Logger log = LoggerFactory.getLogger(PurchaseOrderDsctCodeResource.class);

	private static final String ENTITY_NAME = "purchaseOrderDsctCode";

	private final PurchaseOrderDsctCodeService purchaseOrderDsctCodeService;

	public PurchaseOrderDsctCodeResource(PurchaseOrderDsctCodeService purchaseOrderDsctCodeService) {
		this.purchaseOrderDsctCodeService = purchaseOrderDsctCodeService;
	}

	/**
	 * POST /purchase-order-dsct-codes : Create a new purchaseOrderDsctCode.
	 *
	 * @param purchaseOrderDsctCodeDTO the purchaseOrderDsctCodeDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         purchaseOrderDsctCodeDTO, or with status 400 (Bad Request) if the
	 *         purchaseOrderDsctCode has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/purchase-order-dsct-codes")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<PurchaseOrderDsctCodeDTO> createPurchaseOrderDsctCode(
			@RequestBody PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to save PurchaseOrderDsctCode : {}", purchaseOrderDsctCodeDTO);
		Optional<PurchaseOrderDsctCode> existingCode = purchaseOrderDsctCodeService
				.findOneByCode(purchaseOrderDsctCodeDTO.getCode());
		if (existingCode.isPresent()) {
			throw new BadRequestAlertException("PurchaseOrderDsctCode should be unique", ENTITY_NAME, "codeexists");
		}

		if (purchaseOrderDsctCodeDTO.getId() != null) {
			throw new BadRequestAlertException("A new purchaseOrderDsctCode cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		PurchaseOrderDsctCodeDTO result = purchaseOrderDsctCodeService.save(purchaseOrderDsctCodeDTO);
		return ResponseEntity.created(new URI("/api/purchase-order-dsct-codes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /purchase-order-dsct-codes : Updates an existing purchaseOrderDsctCode.
	 *
	 * @param purchaseOrderDsctCodeDTO the purchaseOrderDsctCodeDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         purchaseOrderDsctCodeDTO, or with status 400 (Bad Request) if the
	 *         purchaseOrderDsctCodeDTO is not valid, or with status 500 (Internal
	 *         Server Error) if the purchaseOrderDsctCodeDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/purchase-order-dsct-codes")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<PurchaseOrderDsctCodeDTO> updatePurchaseOrderDsctCode(
			@RequestBody PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to update PurchaseOrderDsctCode : {}", purchaseOrderDsctCodeDTO);

//		Optional<PurchaseOrderDsctCode> existingCode = purchaseOrderDsctCodeService
//				.findOneByCode(purchaseOrderDsctCodeDTO.getCode());
//		if (existingCode.isPresent()) {
//			throw new BadRequestAlertException("PurchaseOrderDsctCode should be unique", ENTITY_NAME, "codeexists");
//		}

		if (purchaseOrderDsctCodeDTO.getId() == null) {
			return createPurchaseOrderDsctCode(purchaseOrderDsctCodeDTO, authorization);
		}
		PurchaseOrderDsctCodeDTO result = purchaseOrderDsctCodeService.save(purchaseOrderDsctCodeDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseOrderDsctCodeDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /purchase-order-dsct-codes : get all the purchaseOrderDsctCodes.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         purchaseOrderDsctCodes in body
	 */
	@GetMapping("/purchase-order-dsct-codes")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public List<PurchaseOrderDsctCodeDTO> getAllPurchaseOrderDsctCodes(
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get all PurchaseOrderDsctCodes");
		return purchaseOrderDsctCodeService.findAll();
	}

	/**
	 * GET /purchase-order-dsct-codes/:id : get the "id" purchaseOrderDsctCode.
	 *
	 * @param id the id of the purchaseOrderDsctCodeDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         purchaseOrderDsctCodeDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/purchase-order-dsct-codes/{id}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<PurchaseOrderDsctCodeDTO> getPurchaseOrderDsctCode(@PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to get PurchaseOrderDsctCode : {}", id);
		PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO = purchaseOrderDsctCodeService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(purchaseOrderDsctCodeDTO));
	}

	/**
	 * DELETE /purchase-order-dsct-codes/:id : delete the "id"
	 * purchaseOrderDsctCode.
	 *
	 * @param id the id of the purchaseOrderDsctCodeDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/purchase-order-dsct-codes/{id}")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<Void> deletePurchaseOrderDsctCode(@PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {
		log.debug("REST request to delete PurchaseOrderDsctCode : {}", id);
		purchaseOrderDsctCodeService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
