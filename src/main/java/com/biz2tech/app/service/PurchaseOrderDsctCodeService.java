package com.biz2tech.app.service;

import java.util.List;
import java.util.Optional;

import com.biz2tech.app.domain.PurchaseOrderDsctCode;
import com.biz2tech.app.service.dto.PurchaseOrderDsctCodeDTO;

/**
 * Service Interface for managing PurchaseOrderDsctCode.
 */
public interface PurchaseOrderDsctCodeService {

    /**
     * Save a purchaseOrderDsctCode.
     *
     * @param purchaseOrderDsctCodeDTO the entity to save
     * @return the persisted entity
     */
    PurchaseOrderDsctCodeDTO save(PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO);

    /**
     * Get all the purchaseOrderDsctCodes.
     *
     * @return the list of entities
     */
    List<PurchaseOrderDsctCodeDTO> findAll();

    /**
     * Get the "id" purchaseOrderDsctCode.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PurchaseOrderDsctCodeDTO findOne(Long id);

    /**
     * Delete the "id" purchaseOrderDsctCode.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	Optional<PurchaseOrderDsctCode> findOneByCode(String code);
}
