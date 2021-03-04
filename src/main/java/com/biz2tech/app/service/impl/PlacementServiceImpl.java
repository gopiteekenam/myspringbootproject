package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.Placement;
import com.biz2tech.app.repository.PlacementRepository;
import com.biz2tech.app.service.PlacementService;
import com.biz2tech.app.service.dto.PlacementDTO;
import com.biz2tech.app.service.mapper.PlacementMapper;

/**
 * Service Implementation for managing Placement.
 */
@Service
@Transactional
public class PlacementServiceImpl implements PlacementService {

  private final Logger log = LoggerFactory.getLogger(PlacementServiceImpl.class);

  private final PlacementRepository placementRepository;

  private final PlacementMapper placementMapper;

  public PlacementServiceImpl(PlacementRepository placementRepository,
      PlacementMapper placementMapper) {
    this.placementRepository = placementRepository;
    this.placementMapper = placementMapper;
  }

  /**
   * Save a placement.
   *
   * @param placementDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public PlacementDTO save(PlacementDTO placementDTO) {
    log.debug("Request to save Placement : {}", placementDTO);
    Placement placement = placementMapper.toEntity(placementDTO);
    placement = placementRepository.save(placement);
    return placementMapper.toDto(placement);
  }

  /**
   * Get all the placements.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<PlacementDTO> findAll() {
    log.debug("Request to get all Placements");
    return placementRepository.findAll().stream().map(placementMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }



  /**
   * get all the placements where PurchaseOrder is null.
   * 
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<PlacementDTO> findAllWherePurchaseOrderIsNull() {
    log.debug("Request to get all placements where PurchaseOrder is null");
    return StreamSupport.stream(placementRepository.findAll().spliterator(), false)
        .filter(placement -> placement.getPurchaseOrder() == null).map(placementMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * get all the placements where PurchaseOrderItem is null.
   * 
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<PlacementDTO> findAllWherePurchaseOrderItemIsNull() {
    log.debug("Request to get all placements where PurchaseOrderItem is null");
    return StreamSupport.stream(placementRepository.findAll().spliterator(), false)
        .filter(placement -> placement.getPurchaseOrderItem() == null).map(placementMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one placement by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<PlacementDTO> findOne(Long id) {
    log.debug("Request to get Placement : {}", id);
    return Optional.ofNullable(placementRepository.findOne(id)).map(placementMapper::toDto);
  }

  /**
   * Delete the placement by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Placement : {}", id);
    placementRepository.delete(id);
  }
}
