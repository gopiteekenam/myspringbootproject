package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
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
import com.biz2tech.app.service.NewsService;
import com.biz2tech.app.service.TestimonialsService;
import com.biz2tech.app.service.dto.NewsDTO;
import com.biz2tech.app.service.dto.TestimonialsDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for CRUD operations in News
 */
@RestController
@RequestMapping("/api")
public class NewsResource {

	private final Logger log = LoggerFactory.getLogger(NewsResource.class);

	private static final String ENTITY_NAME = "News";

	private final NewsService newsService;

	public NewsResource(NewsService newsService) {
		this.newsService = newsService;

	}

	/**
	 * POST /news : Create new news
	 *
	 * @param newsDTO the newsDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         newsDTO, or with status 400 (Bad Request) if the news
	 *         has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/news")
	@Timed
	public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) throws URISyntaxException {
		log.debug("REST request to save News : {}", newsDTO);
		if (newsDTO.getId() != null) {
			throw new BadRequestAlertException("A new testimonials cannot already have an ID", ENTITY_NAME, "idexists");
		}
		NewsDTO result = newsService.save(newsDTO);
		return ResponseEntity.created(new URI("/api/testimonials/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /news : Updates an existing news.
	 *
	 * @param newsDTO the newsDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         newsDTO, or with status 400 (Bad Request) if the
	 *         newsDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the newsDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/news")
	@Timed
	public ResponseEntity<NewsDTO> updateNews(@RequestBody NewsDTO newsDTO) throws URISyntaxException {
		log.debug("REST request to update Testimonials : {}", newsDTO);
		if (newsDTO.getId() == null) {
			return createNews(newsDTO);
		}
		NewsDTO result = newsService.save(newsDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newsDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /news : get all the news.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of news
	 *         in body
	 */
	@GetMapping("/news")
	@Timed
	public List<NewsDTO> getAllNews() {
		log.debug("REST request to get all News");
		return newsService.findAll();
	}

	/**
	 * GET /news/:id : get the "id" news.
	 *
	 * @param id the id of the newsDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         newsDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/news/{id}")
	@Timed
	public ResponseEntity<NewsDTO> getNews(@PathVariable Long id) {
		log.debug("REST request to get News : {}", id);
		NewsDTO newsDTO = newsService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(newsDTO));
	}

	/**
	 * GET /news-by-date.
	 *
	 * @param date of the newsDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         newsDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/news-by-date")
	@Timed
	public List<NewsDTO> getByNewsDate(@RequestParam(value = "newsdate") Instant newsDate) {
		log.debug("REST request to get news by Date : {}", newsDate);
		List<NewsDTO> lstNewsDTO = null;
		lstNewsDTO = newsService.findByNewsDate(newsDate);

		return lstNewsDTO;
	}

	/**
	 * DELETE /news/:id : delete the "id" news
	 *
	 * @param id the id of the newsDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */

	@DeleteMapping("/news/{id}")
	@Timed
	public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
		log.debug("REST request to delete News : {}", id);
		newsService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
