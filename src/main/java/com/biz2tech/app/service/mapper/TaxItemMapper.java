package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;

import com.biz2tech.app.domain.TaxItem;
import com.biz2tech.app.service.dto.TaxItemDTO;

/**
 * Mapper for the entity TaxItem and its DTO TaxItemDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaxItemMapper extends EntityMapper<TaxItemDTO, TaxItem> {



    default TaxItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        TaxItem taxItem = new TaxItem();
        taxItem.setId(id);
        return taxItem;
    }
}
