package com.biz2tech.app.service.mapper;

import com.biz2tech.app.domain.*;
import com.biz2tech.app.service.dto.PurchaseOrderItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PurchaseOrderItem and its DTO PurchaseOrderItemDTO.
 */
@Mapper(componentModel = "spring", uses = {PlacementMapper.class, ProductDtlsMapper.class, PurchaseOrderMapper.class})
public interface PurchaseOrderItemMapper extends EntityMapper<PurchaseOrderItemDTO, PurchaseOrderItem> {

    @Mapping(source = "placement.id", target = "placementId")
    @Mapping(source = "productDtls.id", target = "productDtlsId")
    @Mapping(source = "purchaseOrder.id", target = "purchaseOrderId")
    PurchaseOrderItemDTO toDto(PurchaseOrderItem purchaseOrderItem);

    @Mapping(source = "placementId", target = "placement")
    @Mapping(source = "productDtlsId", target = "productDtls")
    @Mapping(source = "purchaseOrderId", target = "purchaseOrder")
    PurchaseOrderItem toEntity(PurchaseOrderItemDTO purchaseOrderItemDTO);

    default PurchaseOrderItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();
        purchaseOrderItem.setId(id);
        return purchaseOrderItem;
    }
}
