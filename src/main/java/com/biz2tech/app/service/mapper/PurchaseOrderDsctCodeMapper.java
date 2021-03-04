package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.PurchaseOrderDsctCode;
import com.biz2tech.app.service.dto.PurchaseOrderDsctCodeDTO;

/**
 * Mapper for the entity PurchaseOrderDsctCode and its DTO PurchaseOrderDsctCodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PurchaseOrderDsctCodeMapper extends EntityMapper<PurchaseOrderDsctCodeDTO, PurchaseOrderDsctCode> {


    @Mapping(target = "purchaseOrders", ignore = true)
    PurchaseOrderDsctCode toEntity(PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO);

    default PurchaseOrderDsctCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        PurchaseOrderDsctCode purchaseOrderDsctCode = new PurchaseOrderDsctCode();
        purchaseOrderDsctCode.setId(id);
        return purchaseOrderDsctCode;
    }
}
