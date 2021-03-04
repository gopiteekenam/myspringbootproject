package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.PurchaseOrderDsctCode;
import com.biz2tech.app.repository.PurchaseOrderDsctCodeRepository;
import com.biz2tech.app.service.PurchaseOrderDsctCodeService;
import com.biz2tech.app.service.dto.PurchaseOrderDsctCodeDTO;
import com.biz2tech.app.service.mapper.PurchaseOrderDsctCodeMapper;

/**
 * Service Implementation for managing PurchaseOrderDsctCode.
 */
@Service
@Transactional
public class PurchaseOrderDsctCodeServiceImpl implements PurchaseOrderDsctCodeService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderDsctCodeServiceImpl.class);

    private final PurchaseOrderDsctCodeRepository purchaseOrderDsctCodeRepository;

    private final PurchaseOrderDsctCodeMapper purchaseOrderDsctCodeMapper;

    public PurchaseOrderDsctCodeServiceImpl(PurchaseOrderDsctCodeRepository purchaseOrderDsctCodeRepository, PurchaseOrderDsctCodeMapper purchaseOrderDsctCodeMapper) {
        this.purchaseOrderDsctCodeRepository = purchaseOrderDsctCodeRepository;
        this.purchaseOrderDsctCodeMapper = purchaseOrderDsctCodeMapper;
    }

    /**
     * Save a purchaseOrderDsctCode.
     *
     * @param purchaseOrderDsctCodeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PurchaseOrderDsctCodeDTO save(PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO) {
        log.debug("Request to save PurchaseOrderDsctCode : {}", purchaseOrderDsctCodeDTO);
        PurchaseOrderDsctCode purchaseOrderDsctCode = purchaseOrderDsctCodeMapper.toEntity(purchaseOrderDsctCodeDTO);
        purchaseOrderDsctCode = purchaseOrderDsctCodeRepository.save(purchaseOrderDsctCode);
        return purchaseOrderDsctCodeMapper.toDto(purchaseOrderDsctCode);
    }

    /**
     * Get all the purchaseOrderDsctCodes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseOrderDsctCodeDTO> findAll() {
        log.debug("Request to get all PurchaseOrderDsctCodes");
        return purchaseOrderDsctCodeRepository.findAll().stream()
            .map(purchaseOrderDsctCodeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one purchaseOrderDsctCode by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderDsctCodeDTO findOne(Long id) {
        log.debug("Request to get PurchaseOrderDsctCode : {}", id);
        PurchaseOrderDsctCode purchaseOrderDsctCode = purchaseOrderDsctCodeRepository.findOne(id);
        return purchaseOrderDsctCodeMapper.toDto(purchaseOrderDsctCode);
    }

    /**
     * Delete the purchaseOrderDsctCode by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrderDsctCode : {}", id);
        purchaseOrderDsctCodeRepository.delete(id);
    }

	@Override
	public Optional<PurchaseOrderDsctCode> findOneByCode(String code) {
		return purchaseOrderDsctCodeRepository.findOneByCode(code);
	}
}
