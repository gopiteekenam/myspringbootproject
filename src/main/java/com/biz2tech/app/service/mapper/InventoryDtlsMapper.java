package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.InventoryDtls;
import com.biz2tech.app.service.dto.InventoryDtlsDTO;

/**
 * Mapper for the entity InventoryDtls and its DTO InventoryDtlsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InventoryDtlsMapper extends EntityMapper<InventoryDtlsDTO, InventoryDtls> {


    @Mapping(target = "productDtls", ignore = true)
    InventoryDtls toEntity(InventoryDtlsDTO inventoryDtlsDTO);

    default InventoryDtls fromId(Long id) {
        if (id == null) {
            return null;
        }
        InventoryDtls inventoryDtls = new InventoryDtls();
        inventoryDtls.setId(id);
        return inventoryDtls;
    }
}
