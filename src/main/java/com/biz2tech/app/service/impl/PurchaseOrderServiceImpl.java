package com.biz2tech.app.service.impl;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.Payment;
import com.biz2tech.app.domain.PurchaseOrder;
import com.biz2tech.app.domain.PurchaseOrderItem;
import com.biz2tech.app.domain.ShoppingCart;
import com.biz2tech.app.domain.ShoppingCartItem;
import com.biz2tech.app.domain.enumeration.OrderState;
import com.biz2tech.app.domain.enumeration.OrderType;
import com.biz2tech.app.repository.PaymentRepository;
import com.biz2tech.app.repository.ProductDtlsRepository;
import com.biz2tech.app.repository.PurchaseOrderItemRepository;
import com.biz2tech.app.repository.PurchaseOrderRepository;
import com.biz2tech.app.repository.ShoppingCartItemRepository;
import com.biz2tech.app.repository.ShoppingCartRepository;
import com.biz2tech.app.repository.UserRepository;
import com.biz2tech.app.service.InventoryDtlsService;
import com.biz2tech.app.service.MailService;
import com.biz2tech.app.service.PurchaseOrderService;
import com.biz2tech.app.service.dto.CreatePurchaseOrderDTO;
import com.biz2tech.app.service.dto.UpdatePurchaseOrderDTO;
import com.biz2tech.app.service.mapper.PurchaseOrderMapper;
import com.biz2tech.app.web.rest.errors.InventoryNotAvailableException;

/**
 * Service Implementation for managing PurchaseOrder.
 */
@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

  private final Logger log = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

  private final PurchaseOrderRepository purchaseOrderRepository;
  private final PurchaseOrderItemRepository purchaseOrderItemRepository;
  private final PaymentRepository paymentRepository;
  private final PurchaseOrderMapper purchaseOrderMapper;
  private final ShoppingCartRepository shoppingCartRepository;
  private final ShoppingCartItemRepository shoppingCartItemRepository;
  private final InventoryDtlsService inventoryDtlsService;
  private final UserRepository userRepository;
  private final MailService mailService;
  private final ProductDtlsRepository productDtlsRepository;

  public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
      PurchaseOrderItemRepository purchaseOrderItemRepository,
      PurchaseOrderMapper purchaseOrderMapper,
      ShoppingCartItemRepository shoppingCartItemRepository, PaymentRepository paymentRepository,
      ShoppingCartRepository shoppingCartRepository, InventoryDtlsService inventoryDtlsService,
      UserRepository userRepository, MailService mailService,
      ProductDtlsRepository productDtlsRepository) {
    this.purchaseOrderRepository = purchaseOrderRepository;
    this.purchaseOrderItemRepository = purchaseOrderItemRepository;
    this.purchaseOrderMapper = purchaseOrderMapper;
    this.shoppingCartItemRepository = shoppingCartItemRepository;
    this.paymentRepository = paymentRepository;
    this.shoppingCartRepository = shoppingCartRepository;
    this.inventoryDtlsService = inventoryDtlsService;
    this.userRepository = userRepository;
    this.mailService = mailService;
    this.productDtlsRepository = productDtlsRepository;
  }

  /**
   * Save a purchaseOrder.
   *
   * @param purchaseOrderDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public CreatePurchaseOrderDTO save(CreatePurchaseOrderDTO purchaseOrderDTO) {
    log.debug("Request to save PurchaseOrder : {}", purchaseOrderDTO);
    PurchaseOrder purchaseOrder = purchaseOrderMapper.toEntity(purchaseOrderDTO);
    purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
    return purchaseOrderMapper.toDto(purchaseOrder);
  }

  /**
   * Get all the purchaseOrders.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<CreatePurchaseOrderDTO> findAll() {
    log.debug("Request to get all PurchaseOrders");
    return purchaseOrderRepository.findAll().stream().map(purchaseOrderMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CreatePurchaseOrderDTO> findByUserName(String username) {
    log.debug("Request to get all PurchaseOrders");
    return purchaseOrderRepository.findByCreatedBy(username).stream()
        .map(purchaseOrderMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one purchaseOrder by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<CreatePurchaseOrderDTO> findOne(Long id) {
    log.debug("Request to get PurchaseOrder : {}", id);
    return Optional.ofNullable(purchaseOrderRepository.findOne(id)).map(purchaseOrderMapper::toDto);
  }

  /**
   * Delete the purchaseOrder by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete PurchaseOrder : {}", id);
    purchaseOrderRepository.delete(id);
  }


  @Override
  public CreatePurchaseOrderDTO createPurchaseOrder(CreatePurchaseOrderDTO purchaseOrderDTO,
      String userLoginId) {
    log.debug("Request to save PurchaseOrder : {}", purchaseOrderDTO);
    if (!inventoryDtlsService.isInventoryAvailable(purchaseOrderDTO.getPurchaseOrderItemDTOs()
        .stream().map(item -> item.getProductDtlsId()).collect(Collectors.toList()))) {
      throw new InventoryNotAvailableException();
    }

    PurchaseOrder purchaseOrder = purchaseOrderMapper.toEntity(purchaseOrderDTO);
    purchaseOrder.setStatusCode(OrderState.CREATED);
    purchaseOrder.setCreatedBy(userLoginId);
    purchaseOrder.setOrderType(OrderType.CART);
    purchaseOrder.setOrderDate(Instant.now());
    purchaseOrder = purchaseOrderRepository.save(purchaseOrder);

    // update purchase order item status "OrderState.CREATED"
    for (PurchaseOrderItem item : purchaseOrder.getPurchaseOrderItems()) {
      item.setCreatedBy(userLoginId);
      item.setStatusCode(OrderState.CREATED);
      item.setPurchaseOrder(purchaseOrder);
    }

    purchaseOrderItemRepository.save(purchaseOrder.getPurchaseOrderItems());
    purchaseOrder.getPurchaseOrderItems().forEach(item -> {
      item.setProductDtls(productDtlsRepository.findOne(item.getProductDtls().getId()));
    });

    return purchaseOrderMapper.toDto(purchaseOrder);
  }


  @Override
  public UpdatePurchaseOrderDTO updatePurchaseOrder(UpdatePurchaseOrderDTO purchaseOrderDTO,
      String userLoginId) {
    log.debug("Request to update PurchaseOrder : {}", purchaseOrderDTO);

    PurchaseOrder purchaseOrderFromDB = purchaseOrderRepository.findOne(purchaseOrderDTO.getId());
    // PurchaseOrder purchaseOrder = purchaseOrderMapper.toEntity(purchaseOrderDTO);
    purchaseOrderFromDB.setStatusCode(purchaseOrderDTO.getStatusCode());
    purchaseOrderFromDB.setCreatedBy(userLoginId);
    if (purchaseOrderFromDB.getPayment() == null) {
      purchaseOrderFromDB.setPayment(new Payment());
    }
    purchaseOrderFromDB.getPayment().setBankName(purchaseOrderDTO.getBankName());
    purchaseOrderFromDB.getPayment().setPaymentNotes(purchaseOrderDTO.getPaymentNotes());
    purchaseOrderFromDB.getPayment().setStatusCode(purchaseOrderDTO.getPaymentStatusCode());
    purchaseOrderFromDB.getPayment()
        .setTransactionReference(purchaseOrderDTO.getTransactionReference());
    paymentRepository.save(purchaseOrderFromDB.getPayment());
    purchaseOrderFromDB = purchaseOrderRepository.save(purchaseOrderFromDB);
    purchaseOrderFromDB.getPurchaseOrderItems().forEach(item -> {
      item.setProductDtls(productDtlsRepository.findOne(item.getProductDtls().getId()));
    });

    if (OrderState.PAYMENT_SUCCESS.equals(purchaseOrderFromDB.getStatusCode())) {
      processSuccessfulPayment(userLoginId);
      mailService.sendOrderSuccessEmail(purchaseOrderFromDB,
          userRepository.findOneByLogin(userLoginId).get());
    } else {
      mailService.sendOrderFailureEmail(purchaseOrderFromDB,
          userRepository.findOneByLogin(userLoginId).get());
    }

    return purchaseOrderMapper.toUpdatePurchaseOrderDTO(purchaseOrderFromDB);
  }

  private void processSuccessfulPayment(String userLoginId) {
    ShoppingCart shoppingCart = shoppingCartRepository.findByCreatedBy(userLoginId);
    // capturing list of cart items by quantity
    Map<Integer, List<ShoppingCartItem>> cartItemsByQuantityMap = shoppingCart
        .getShoppingCartItems().stream().collect(Collectors.groupingBy(item -> item.getQuantity()));
    // clear shopping cart items
    shoppingCartItemRepository.delete(shoppingCart.getShoppingCartItems());

    // reduce inventory quantity. calling inventory service once per quantity
    cartItemsByQuantityMap.forEach((qty, cartItemsList) -> {
      inventoryDtlsService.reduceInvenotyQuantityByProductId(cartItemsList.stream()
          .map(item -> item.getProductDtls().getId()).collect(Collectors.toList()), qty);
    });
  }


}
