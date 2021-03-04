package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.DropShipDtls;
import com.biz2tech.app.repository.DropShipDtlsRepository;
import com.biz2tech.app.service.DropShipDtlsService;
import com.biz2tech.app.service.dto.DropShipDtlsDTO;
import com.biz2tech.app.service.mapper.DropShipDtlsMapper;

/**
 * Service Implementation for managing DropShipDtls.
 */
@Service
@Transactional
public class DropShipDtlsServiceImpl implements DropShipDtlsService {

    private final Logger log = LoggerFactory.getLogger(DropShipDtlsServiceImpl.class);

    private final DropShipDtlsRepository dropShipDtlsRepository;

    private final DropShipDtlsMapper dropShipDtlsMapper;

    public DropShipDtlsServiceImpl(DropShipDtlsRepository dropShipDtlsRepository, DropShipDtlsMapper dropShipDtlsMapper) {
        this.dropShipDtlsRepository = dropShipDtlsRepository;
        this.dropShipDtlsMapper = dropShipDtlsMapper;
    }

    /**
     * Save a dropShipDtls.
     *
     * @param dropShipDtlsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DropShipDtlsDTO save(DropShipDtlsDTO dropShipDtlsDTO) {
        log.debug("Request to save DropShipDtls : {}", dropShipDtlsDTO);
        DropShipDtls dropShipDtls = dropShipDtlsMapper.toEntity(dropShipDtlsDTO);
        dropShipDtls = dropShipDtlsRepository.save(dropShipDtls);
        return dropShipDtlsMapper.toDto(dropShipDtls);
    }

    /**
     * Get all the dropShipDtls.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DropShipDtlsDTO> findAll() {
        log.debug("Request to get all DropShipDtls");
        return dropShipDtlsRepository.findAll().stream()
            .map(dropShipDtlsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dropShipDtls by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DropShipDtlsDTO findOne(Long id) {
        log.debug("Request to get DropShipDtls : {}", id);
        DropShipDtls dropShipDtls = dropShipDtlsRepository.findOne(id);
        return dropShipDtlsMapper.toDto(dropShipDtls);
    }

    /**
     * Delete the dropShipDtls by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DropShipDtls : {}", id);
        dropShipDtlsRepository.delete(id);
    }
}
