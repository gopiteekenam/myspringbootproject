package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.service.PlacementService;
import com.biz2tech.app.service.dto.PlacementDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Placement.
 */
@RestController
@RequestMapping("/api")
public class PlacementResource {
	private final Logger log = LoggerFactory.getLogger(PlacementResource.class);

	private static final String ENTITY_NAME = "fragrancenetservicePlacement";

	private final PlacementService placementService;

	public PlacementResource(PlacementService placementService) {
		this.placementService = placementService;
	}

	/**
	 * POST /placements : Create a new placement.
	 *
	 * @param placementDTO the placementDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         placementDTO, or with status 400 (Bad Request) if the placement has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */

	@PostMapping("/placements")
	@Timed
	public ResponseEntity<PlacementDTO> createPlacement(@Valid @RequestBody PlacementDTO placementDTO)
			throws URISyntaxException {
		log.debug("REST request to save Placement : {}", placementDTO);
		if (placementDTO.getId() != null) {
			throw new BadRequestAlertException("A new placement cannot already have an ID", ENTITY_NAME, "idexists");
		}
		PlacementDTO result = placementService.save(placementDTO);
		return ResponseEntity.created(new URI("/api/placements/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /placements : Updates an existing placement.
	 *
	 * @param placementDTO the placementDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         placementDTO, or with status 400 (Bad Request) if the placementDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         placementDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */

	@PutMapping("/placements")

	@Timed
	public ResponseEntity<PlacementDTO> updatePlacement(@Valid @RequestBody PlacementDTO placementDTO)
			throws URISyntaxException {
		log.debug("REST request to update Placement : {}", placementDTO);
		if (placementDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		PlacementDTO result = placementService.save(placementDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, placementDTO.getId().toString())).body(result);
	}

	/**
	 * GET /placements : get all the placements.
	 *
	 * @param filter the filter of the request
	 * @return the ResponseEntity with status 200 (OK) and the list of placements in
	 *         body
	 */

	@GetMapping("/placements")

	@Timed
	public List<PlacementDTO> getAllPlacements(@RequestParam(required = false) String filter) {
		if ("purchaseorder-is-null".equals(filter)) {
			log.debug("REST request to get all Placements where purchaseOrder is null");
			return placementService.findAllWherePurchaseOrderIsNull();
		}
		if ("purchaseorderitem-is-null".equals(filter)) {
			log.debug("REST request to get all Placements where purchaseOrderItem is null");
			return placementService.findAllWherePurchaseOrderItemIsNull();
		}
		log.debug("REST request to get all Placements");
		return placementService.findAll();
	}

	/**
	 * GET /placements/:id : get the "id" placement.
	 *
	 * @param id the id of the placementDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         placementDTO, or with status 404 (Not Found)
	 */

	@GetMapping("/placements/{id}")

	@Timed
	public ResponseEntity<PlacementDTO> getPlacement(@PathVariable Long id) {
		log.debug("REST request to get Placement : {}", id);
		Optional<PlacementDTO> placementDTO = placementService.findOne(id);
		return ResponseUtil.wrapOrNotFound(placementDTO);
	}

	/**
	 * DELETE /placements/:id : delete the "id" placement.
	 *
	 * @param id the id of the placementDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */

	@DeleteMapping("/placements/{id}")

	@Timed
	public ResponseEntity<Void> deletePlacement(@PathVariable Long id) {
		log.debug("REST request to delete Placement : {}", id);
		placementService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
