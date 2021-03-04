package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.SellerDetails;
import com.biz2tech.app.repository.SellerDetailsRepository;
import com.biz2tech.app.service.SellerDetailsService;
import com.biz2tech.app.service.dto.SellerDetailsDTO;
import com.biz2tech.app.service.mapper.SellerDetailsMapper;

/**
 * Service Implementation for managing SellerDetails.
 */
@Service
@Transactional
public class SellerDetailsServiceImpl implements SellerDetailsService {

    private final Logger log = LoggerFactory.getLogger(SellerDetailsServiceImpl.class);

    private final SellerDetailsRepository sellerDetailsRepository;

    private final SellerDetailsMapper sellerDetailsMapper;

    public SellerDetailsServiceImpl(SellerDetailsRepository sellerDetailsRepository, SellerDetailsMapper sellerDetailsMapper) {
        this.sellerDetailsRepository = sellerDetailsRepository;
        this.sellerDetailsMapper = sellerDetailsMapper;
    }

    /**
     * Save a sellerDetails.
     *
     * @param sellerDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SellerDetailsDTO save(SellerDetailsDTO sellerDetailsDTO) {
        log.debug("Request to save SellerDetails : {}", sellerDetailsDTO);
        SellerDetails sellerDetails = sellerDetailsMapper.toEntity(sellerDetailsDTO);
        sellerDetails = sellerDetailsRepository.save(sellerDetails);
        return sellerDetailsMapper.toDto(sellerDetails);
    }

    /**
     * Get all the sellerDetails.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SellerDetailsDTO> findAll() {
        log.debug("Request to get all SellerDetails");
        return sellerDetailsRepository.findAll().stream()
            .map(sellerDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one sellerDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SellerDetailsDTO findOne(Long id) {
        log.debug("Request to get SellerDetails : {}", id);
        SellerDetails sellerDetails = sellerDetailsRepository.findOne(id);
        return sellerDetailsMapper.toDto(sellerDetails);
    }

    /**
     * Delete the sellerDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SellerDetails : {}", id);
        sellerDetailsRepository.delete(id);
    }
}
