package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.DropShipDtlsDTO;

/**
 * Service Interface for managing DropShipDtls.
 */
public interface DropShipDtlsService {

    /**
     * Save a dropShipDtls.
     *
     * @param dropShipDtlsDTO the entity to save
     * @return the persisted entity
     */
    DropShipDtlsDTO save(DropShipDtlsDTO dropShipDtlsDTO);

    /**
     * Get all the dropShipDtls.
     *
     * @return the list of entities
     */
    List<DropShipDtlsDTO> findAll();

    /**
     * Get the "id" dropShipDtls.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DropShipDtlsDTO findOne(Long id);

    /**
     * Delete the "id" dropShipDtls.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
