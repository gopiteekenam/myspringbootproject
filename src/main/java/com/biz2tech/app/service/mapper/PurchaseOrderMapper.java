package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.PurchaseOrder;
import com.biz2tech.app.service.dto.CreatePurchaseOrderDTO;
import com.biz2tech.app.service.dto.UpdatePurchaseOrderDTO;

/**
 * Mapper for the entity PurchaseOrder and its DTO PurchaseOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {PlacementMapper.class, PaymentMapper.class,
    PurchaseOrderDsctCodeMapper.class, PurchaseOrderItemMapper.class})
public interface PurchaseOrderMapper extends EntityMapper<CreatePurchaseOrderDTO, PurchaseOrder> {

  @Override
  @Mapping(target = "purchaseOrderItemDTOs", source = "purchaseOrderItems")
  @Mapping(source = "purchaseOrderDsctCode.id", target = "purchaseOrderDsctCodeId")
  CreatePurchaseOrderDTO toDto(PurchaseOrder purchaseOrder);

  @Override
  @Mapping(target = "purchaseOrderItems", source = "purchaseOrderItemDTOs")
  @Mapping(source = "purchaseOrderDsctCodeId", target = "purchaseOrderDsctCode")
  PurchaseOrder toEntity(CreatePurchaseOrderDTO purchaseOrderDTO);

  @Mapping(source = "purchaseOrderDsctCodeId", target = "purchaseOrderDsctCode")
  @Mapping(source = "bankName", target = "payment.bankName")
  @Mapping(source = "transactionReference", target = "payment.transactionReference")
  @Mapping(source = "paymentStatusCode", target = "payment.statusCode")
  @Mapping(source = "paymentNotes", target = "payment.paymentNotes")
  PurchaseOrder toEntity(UpdatePurchaseOrderDTO purchaseOrderDTO);

  @Mapping(source = "purchaseOrderDsctCode.id", target = "purchaseOrderDsctCodeId")
  @Mapping(target = "bankName", source = "payment.bankName")
  @Mapping(target = "transactionReference", source = "payment.transactionReference")
  @Mapping(target = "paymentStatusCode", source = "payment.statusCode")
  @Mapping(target = "paymentNotes", source = "payment.paymentNotes")
  UpdatePurchaseOrderDTO toUpdatePurchaseOrderDTO(PurchaseOrder purchaseOrder);

  default PurchaseOrder fromId(Long id) {
    if (id == null) {
      return null;
    }
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setId(id);
    return purchaseOrder;
  }
}
