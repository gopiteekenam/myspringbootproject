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

import com.biz2tech.app.domain.Payment;
import com.biz2tech.app.repository.PaymentRepository;
import com.biz2tech.app.service.PaymentService;
import com.biz2tech.app.service.dto.PaymentDTO;
import com.biz2tech.app.service.mapper.PaymentMapper;

/**
 * Service Implementation for managing Payment.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

  private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

  private final PaymentRepository paymentRepository;

  private final PaymentMapper paymentMapper;

  public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
    this.paymentRepository = paymentRepository;
    this.paymentMapper = paymentMapper;
  }

  /**
   * Save a payment.
   *
   * @param paymentDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public PaymentDTO save(PaymentDTO paymentDTO) {
    log.debug("Request to save Payment : {}", paymentDTO);
    Payment payment = paymentMapper.toEntity(paymentDTO);
    payment = paymentRepository.save(payment);
    return paymentMapper.toDto(payment);
  }

  /**
   * Get all the payments.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<PaymentDTO> findAll() {
    log.debug("Request to get all Payments");
    return paymentRepository.findAll().stream().map(paymentMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }



  /**
   * get all the payments where PurchaseOrder is null.
   * 
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<PaymentDTO> findAllWherePurchaseOrderIsNull() {
    log.debug("Request to get all payments where PurchaseOrder is null");
    return StreamSupport.stream(paymentRepository.findAll().spliterator(), false)
        .filter(payment -> payment.getPurchaseOrder() == null).map(paymentMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one payment by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<PaymentDTO> findOne(Long id) {
    log.debug("Request to get Payment : {}", id);
    return Optional.ofNullable(paymentRepository.findOne(id)).map(paymentMapper::toDto);
  }

  /**
   * Delete the payment by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Payment : {}", id);
    paymentRepository.delete(id);
  }
}
