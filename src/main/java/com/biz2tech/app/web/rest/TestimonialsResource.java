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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.security.AuthoritiesConstants;
import com.biz2tech.app.service.TestimonialsService;
import com.biz2tech.app.service.dto.TestimonialsDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for CRUD operations in Testimonials
 */
@RestController
@RequestMapping("/api")
public class TestimonialsResource {

	private final Logger log = LoggerFactory.getLogger(TestimonialsResource.class);

	private static final String ENTITY_NAME = "Testimonials";

	private final TestimonialsService testimonialsService;

	public TestimonialsResource(TestimonialsService testimonialsService) {
		this.testimonialsService = testimonialsService;

	}

	/**
	 * POST /testimonials : Create a new testimonial
	 *
	 * @param testimonialsDTO the testimonialsDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         testimonialsDTO, or with status 400 (Bad Request) if the testimonials
	 *         has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/testimonials")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<TestimonialsDTO> createTestimonials(@RequestBody TestimonialsDTO testimonialsDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to save Testimonials : {}", testimonialsDTO);
		if (testimonialsDTO.getId() != null) {
			throw new BadRequestAlertException("A new testimonials cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TestimonialsDTO result = testimonialsService.save(testimonialsDTO);
		return ResponseEntity.created(new URI("/api/testimonials/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /testimonials : Updates an existing testimonials.
	 *
	 * @param testimonialsDTO the testimonialsDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         testimonialsDTO, or with status 400 (Bad Request) if the
	 *         testimonialsDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the testimonialsDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/testimonials")
	@Secured(value = { AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN })
	@Timed
	public ResponseEntity<TestimonialsDTO> updateTestimonials(@RequestBody TestimonialsDTO testimonialsDTO,
			@RequestHeader("Authorization") String authorization) throws URISyntaxException {
		log.debug("REST request to update Testimonials : {}", testimonialsDTO);
		if (testimonialsDTO.getId() == null) {
			return createTestimonials(testimonialsDTO, authorization);
		}
		TestimonialsDTO result = testimonialsService.save(testimonialsDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, testimonialsDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /testimonials : get all the testimonials.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of testimonials
	 *         in body
	 */
	@GetMapping("/testimonials")
	@Timed
	public List<TestimonialsDTO> getAllTestimonials() {
		log.debug("REST request to get all Testimonials");
		return testimonialsService.findAll();
	}

	/**
	 * GET /testimonials/:id : get the "id" testimonials.
	 *
	 * @param id the id of the testimonialsDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         testimonialsDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/testimonials/{id}")
	@Timed
	public ResponseEntity<TestimonialsDTO> getTestimonial(@PathVariable Long id) {
		log.debug("REST request to get Testimonial : {}", id);
		TestimonialsDTO testimonialsDTO = testimonialsService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(testimonialsDTO));
	}

	/**
	 * GET /testimonials-by-city.
	 *
	 * @param tagname of the testimonialsDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         productTagDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/testimonials-by-city")
	@Timed
	public List<TestimonialsDTO> getByCity(@RequestParam(value = "pageNumber") int page,
			@RequestParam(value = "city") String city) {
		log.debug("REST request to get Testimonials by city : {}", city);
		List<TestimonialsDTO> lstTestimonialsDTO = null;
		lstTestimonialsDTO = testimonialsService.findByCity(city);

		return lstTestimonialsDTO;
	}

	/**
	 * GET /testimonials-by-city.
	 *
	 * @param tagname of the testimonialsDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         productTagDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/testimonials-by-commentorName")
	@Timed
	public List<TestimonialsDTO> getByCommentorName(@RequestParam(value = "pageNumber") int page,
			@RequestParam(value = "commentorName") String commentorName) {
		log.debug("REST request to get Testimonials by commentorName : {}", commentorName);
		List<TestimonialsDTO> lstTestimonialsDTO = null;
		lstTestimonialsDTO = testimonialsService.findByCommentorName(commentorName);

		return lstTestimonialsDTO;
	}

	/**
	 * DELETE /testimonials/:id : delete the "id" testimonials.
	 *
	 * @param id the id of the testimonialsDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */

	@DeleteMapping("/testimonials/{id}")
	@Timed
	public ResponseEntity<Void> deleteTestimonial(@PathVariable Long id) {
		log.debug("REST request to delete Testimonial : {}", id);
		testimonialsService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
