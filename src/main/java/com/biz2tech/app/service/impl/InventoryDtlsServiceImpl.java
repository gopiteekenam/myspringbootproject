package com.biz2tech.app.service.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.InventoryDtls;
import com.biz2tech.app.repository.InventoryDtlsRepository;
import com.biz2tech.app.service.InventoryDtlsService;
import com.biz2tech.app.service.dto.InventoryDtlsDTO;
import com.biz2tech.app.service.mapper.InventoryDtlsMapper;

/**
 * Service Implementation for managing InventoryDtls.
 */
@Service
@Transactional
public class InventoryDtlsServiceImpl implements InventoryDtlsService {

  private final Logger log = LoggerFactory.getLogger(InventoryDtlsServiceImpl.class);

  private final InventoryDtlsRepository inventoryDtlsRepository;

  private final InventoryDtlsMapper inventoryDtlsMapper;

  public InventoryDtlsServiceImpl(InventoryDtlsRepository inventoryDtlsRepository,
      InventoryDtlsMapper inventoryDtlsMapper) {
    this.inventoryDtlsRepository = inventoryDtlsRepository;
    this.inventoryDtlsMapper = inventoryDtlsMapper;
  }

  /**
   * Save a inventoryDtls.
   *
   * @param inventoryDtlsDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public InventoryDtlsDTO save(InventoryDtlsDTO inventoryDtlsDTO) {
    log.debug("Request to save InventoryDtls : {}", inventoryDtlsDTO);
    InventoryDtls inventoryDtls = inventoryDtlsMapper.toEntity(inventoryDtlsDTO);
    inventoryDtls = inventoryDtlsRepository.save(inventoryDtls);
    return inventoryDtlsMapper.toDto(inventoryDtls);
  }

  /**
   * Get all the inventoryDtls.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<InventoryDtlsDTO> findAll() {
    log.debug("Request to get all InventoryDtls");
    return inventoryDtlsRepository.findAll().stream().map(inventoryDtlsMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * get all the inventoryDtls where ProductDtls is null.
   * 
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<InventoryDtlsDTO> findAllWhereProductDtlsIsNull() {
    log.debug("Request to get all inventoryDtls where ProductDtls is null");
    return StreamSupport.stream(inventoryDtlsRepository.findAll().spliterator(), false)
        .filter(inventoryDtls -> inventoryDtls.getProductDtls() == null)
        .map(inventoryDtlsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one inventoryDtls by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public InventoryDtlsDTO findOne(Long id) {
    log.debug("Request to get InventoryDtls : {}", id);
    InventoryDtls inventoryDtls = inventoryDtlsRepository.findOne(id);
    return inventoryDtlsMapper.toDto(inventoryDtls);
  }

  /**
   * Delete the inventoryDtls by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete InventoryDtls : {}", id);
    inventoryDtlsRepository.delete(id);
  }

  @Override
  public void reduceInvenotyQuantityByProductId(List<Long> productIds, int quantity) {
    List<Long> inventoryIds = inventoryDtlsRepository.getInventoryIds(productIds);
    List<InventoryDtls> inventoryDtlsList = inventoryDtlsRepository.findAll(inventoryIds);
    inventoryDtlsList.forEach(inventoryDtl -> {
      if (inventoryDtl.getTotalCnt() == null) {
        inventoryDtl.setTotalCnt(BigDecimal.valueOf(1));
      }
      if (inventoryDtl.getAvblCnt() == null) {
        inventoryDtl.setAvblCnt(BigDecimal.valueOf(1));
      }
      if (inventoryDtl.getSellCnt() == null) {
        inventoryDtl.setSellCnt(BigDecimal.valueOf(0));
      }
      inventoryDtl.setSellCnt(BigDecimal.valueOf(inventoryDtl.getSellCnt().longValue() + quantity));
      inventoryDtl.setAvblCnt(BigDecimal.valueOf(inventoryDtl.getAvblCnt().longValue() - quantity));
    });
    inventoryDtlsRepository.save(inventoryDtlsList);
  }

  @Override
  public boolean isInventoryAvailable(List<Long> productIds) {
    List<InventoryDtls> inventoryDtlsList =
        inventoryDtlsRepository.findAll(inventoryDtlsRepository.getInventoryIds(productIds));

    for (InventoryDtls inventoryDtls : inventoryDtlsList) {
      if (!(inventoryDtls.getAvblCnt() != null && inventoryDtls.getAvblCnt().intValue() > 0)) {
        return false;
      }
    }
    return true;
  }


}
