package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.biz2tech.app.service.PaymentService;
import com.biz2tech.app.service.dto.PaymentDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Payment.
 */
@RestController
@RequestMapping("/api")
public class PaymentResource {

  private final Logger log = LoggerFactory.getLogger(PaymentResource.class);

  private static final String ENTITY_NAME = "fragrancenetservicePayment";

  private final PaymentService paymentService;

  public PaymentResource(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  /**
   * POST /payments : Create a new payment.
   *
   * @param paymentDTO the paymentDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new paymentDTO, or with
   *         status 400 (Bad Request) if the payment has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/payments")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to save Payment : {}", paymentDTO);
    if (paymentDTO.getId() != null) {
      throw new BadRequestAlertException("A new payment cannot already have an ID", ENTITY_NAME,
          "idexists");
    }
    PaymentDTO result = paymentService.save(paymentDTO);
    return ResponseEntity.created(new URI("/api/payments/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /payments : Updates an existing payment.
   *
   * @param paymentDTO the paymentDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated paymentDTO, or with
   *         status 400 (Bad Request) if the paymentDTO is not valid, or with status 500 (Internal
   *         Server Error) if the paymentDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/payments")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<PaymentDTO> updatePayment(@Valid @RequestBody PaymentDTO paymentDTO,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to update Payment : {}", paymentDTO);
    if (paymentDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    PaymentDTO result = paymentService.save(paymentDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paymentDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET /payments : get all the payments.
   *
   * @param filter the filter of the request
   * @return the ResponseEntity with status 200 (OK) and the list of payments in body
   */

	@GetMapping("/payments")

	@Timed
	public List<PaymentDTO> getAllPayments(@RequestParam(required = false) String filter) {
		if ("purchaseorder-is-null".equals(filter)) {
			log.debug("REST request to get all Payments where purchaseOrder is null");
			return paymentService.findAllWherePurchaseOrderIsNull();
		}
		log.debug("REST request to get all Payments");
		return paymentService.findAll();
	}

  /**
   * GET /payments/:id : get the "id" payment.
   *
   * @param id the id of the paymentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the paymentDTO, or with status
   *         404 (Not Found)
   */
  @GetMapping("/payments/{id}")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id,
      @RequestHeader("Authorization") String authorization) {
    log.debug("REST request to get Payment : {}", id);
    Optional<PaymentDTO> paymentDTO = paymentService.findOne(id);
    return ResponseUtil.wrapOrNotFound(paymentDTO);
  }

  /**
   * DELETE /payments/:id : delete the "id" payment.
   *
   * @param id the id of the paymentDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  /*
   * @DeleteMapping("/payments/{id}")
   * 
   * @Timed public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
   * log.debug("REST request to delete Payment : {}", id); paymentService.delete(id); return
   * ResponseEntity.ok() .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
   * id.toString())).build(); }
   */
}
