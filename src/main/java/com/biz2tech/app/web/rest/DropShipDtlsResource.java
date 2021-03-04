package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.service.DropShipDtlsService;
import com.biz2tech.app.service.dto.DropShipDtlsDTO;
import com.biz2tech.app.web.rest.errors.BadRequestAlertException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing DropShipDtls.
 */
@RestController
@RequestMapping("/api")
public class DropShipDtlsResource {

    private final Logger log = LoggerFactory.getLogger(DropShipDtlsResource.class);

    private static final String ENTITY_NAME = "dropShipDtls";

    private final DropShipDtlsService dropShipDtlsService;

    public DropShipDtlsResource(DropShipDtlsService dropShipDtlsService) {
        this.dropShipDtlsService = dropShipDtlsService;
    }

    /**
     * POST  /drop-ship-dtls : Create a new dropShipDtls.
     *
     * @param dropShipDtlsDTO the dropShipDtlsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dropShipDtlsDTO, or with status 400 (Bad Request) if the dropShipDtls has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/drop-ship-dtls")
    @Timed
    public ResponseEntity<DropShipDtlsDTO> createDropShipDtls(@RequestBody DropShipDtlsDTO dropShipDtlsDTO) throws URISyntaxException {
        log.debug("REST request to save DropShipDtls : {}", dropShipDtlsDTO);
        if (dropShipDtlsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dropShipDtls cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DropShipDtlsDTO result = dropShipDtlsService.save(dropShipDtlsDTO);
        return ResponseEntity.created(new URI("/api/drop-ship-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /drop-ship-dtls : Updates an existing dropShipDtls.
     *
     * @param dropShipDtlsDTO the dropShipDtlsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dropShipDtlsDTO,
     * or with status 400 (Bad Request) if the dropShipDtlsDTO is not valid,
     * or with status 500 (Internal Server Error) if the dropShipDtlsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/drop-ship-dtls")
    @Timed
    public ResponseEntity<DropShipDtlsDTO> updateDropShipDtls(@RequestBody DropShipDtlsDTO dropShipDtlsDTO) throws URISyntaxException {
        log.debug("REST request to update DropShipDtls : {}", dropShipDtlsDTO);
        if (dropShipDtlsDTO.getId() == null) {
            return createDropShipDtls(dropShipDtlsDTO);
        }
        DropShipDtlsDTO result = dropShipDtlsService.save(dropShipDtlsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dropShipDtlsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /drop-ship-dtls : get all the dropShipDtls.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dropShipDtls in body
     */
    @GetMapping("/drop-ship-dtls")
    @Timed
    public List<DropShipDtlsDTO> getAllDropShipDtls() {
        log.debug("REST request to get all DropShipDtls");
        return dropShipDtlsService.findAll();
        }

    /**
     * GET  /drop-ship-dtls/:id : get the "id" dropShipDtls.
     *
     * @param id the id of the dropShipDtlsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dropShipDtlsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/drop-ship-dtls/{id}")
    @Timed
    public ResponseEntity<DropShipDtlsDTO> getDropShipDtls(@PathVariable Long id) {
        log.debug("REST request to get DropShipDtls : {}", id);
        DropShipDtlsDTO dropShipDtlsDTO = dropShipDtlsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dropShipDtlsDTO));
    }

    /**
     * DELETE  /drop-ship-dtls/:id : delete the "id" dropShipDtls.
     *
     * @param id the id of the dropShipDtlsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/drop-ship-dtls/{id}")
    @Timed
    public ResponseEntity<Void> deleteDropShipDtls(@PathVariable Long id) {
        log.debug("REST request to delete DropShipDtls : {}", id);
        dropShipDtlsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
