package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.TaxItem;
import com.biz2tech.app.repository.TaxItemRepository;
import com.biz2tech.app.service.TaxItemService;
import com.biz2tech.app.service.dto.TaxItemDTO;
import com.biz2tech.app.service.mapper.TaxItemMapper;

/**
 * Service Implementation for managing TaxItem.
 */
@Service
@Transactional
public class TaxItemServiceImpl implements TaxItemService {

    private final Logger log = LoggerFactory.getLogger(TaxItemServiceImpl.class);

    private final TaxItemRepository taxItemRepository;

    private final TaxItemMapper taxItemMapper;

    public TaxItemServiceImpl(TaxItemRepository taxItemRepository, TaxItemMapper taxItemMapper) {
        this.taxItemRepository = taxItemRepository;
        this.taxItemMapper = taxItemMapper;
    }

    /**
     * Save a taxItem.
     *
     * @param taxItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaxItemDTO save(TaxItemDTO taxItemDTO) {
        log.debug("Request to save TaxItem : {}", taxItemDTO);
        TaxItem taxItem = taxItemMapper.toEntity(taxItemDTO);
        taxItem = taxItemRepository.save(taxItem);
        return taxItemMapper.toDto(taxItem);
    }

    /**
     * Get all the taxItems.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TaxItemDTO> findAll() {
        log.debug("Request to get all TaxItems");
        return taxItemRepository.findAll().stream()
            .map(taxItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one taxItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TaxItemDTO findOne(Long id) {
        log.debug("Request to get TaxItem : {}", id);
        TaxItem taxItem = taxItemRepository.findOne(id);
        return taxItemMapper.toDto(taxItem);
    }

    /**
     * Delete the taxItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaxItem : {}", id);
        taxItemRepository.delete(id);
    }
}
