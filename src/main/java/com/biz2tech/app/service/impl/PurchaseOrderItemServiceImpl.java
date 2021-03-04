package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.PurchaseOrderItem;
import com.biz2tech.app.repository.PurchaseOrderItemRepository;
import com.biz2tech.app.service.PurchaseOrderItemService;
import com.biz2tech.app.service.dto.PurchaseOrderItemDTO;
import com.biz2tech.app.service.mapper.PurchaseOrderItemMapper;

/**
 * Service Implementation for managing PurchaseOrderItem.
 */
@Service
@Transactional
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {

  private final Logger log = LoggerFactory.getLogger(PurchaseOrderItemServiceImpl.class);

  private final PurchaseOrderItemRepository purchaseOrderItemRepository;

  private final PurchaseOrderItemMapper purchaseOrderItemMapper;

  public PurchaseOrderItemServiceImpl(PurchaseOrderItemRepository purchaseOrderItemRepository,
      PurchaseOrderItemMapper purchaseOrderItemMapper) {
    this.purchaseOrderItemRepository = purchaseOrderItemRepository;
    this.purchaseOrderItemMapper = purchaseOrderItemMapper;
  }

  /**
   * Save a purchaseOrderItem.
   *
   * @param purchaseOrderItemDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public PurchaseOrderItemDTO save(PurchaseOrderItemDTO purchaseOrderItemDTO) {
    log.debug("Request to save PurchaseOrderItem : {}", purchaseOrderItemDTO);
    PurchaseOrderItem purchaseOrderItem = purchaseOrderItemMapper.toEntity(purchaseOrderItemDTO);
    purchaseOrderItem = purchaseOrderItemRepository.save(purchaseOrderItem);
    return purchaseOrderItemMapper.toDto(purchaseOrderItem);
  }

  /**
   * Get all the purchaseOrderItems.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<PurchaseOrderItemDTO> findAll() {
    log.debug("Request to get all PurchaseOrderItems");
    return purchaseOrderItemRepository.findAll().stream().map(purchaseOrderItemMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one purchaseOrderItem by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<PurchaseOrderItemDTO> findOne(Long id) {
    log.debug("Request to get PurchaseOrderItem : {}", id);
    return Optional.ofNullable(purchaseOrderItemRepository.findOne(id))
        .map(purchaseOrderItemMapper::toDto);
  }

  /**
   * Delete the purchaseOrderItem by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete PurchaseOrderItem : {}", id);
    purchaseOrderItemRepository.delete(id);
  }
}
