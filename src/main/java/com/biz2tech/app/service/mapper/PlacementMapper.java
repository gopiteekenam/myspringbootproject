package com.biz2tech.app.service.mapper;

import com.biz2tech.app.domain.*;
import com.biz2tech.app.service.dto.PlacementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Placement and its DTO PlacementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlacementMapper extends EntityMapper<PlacementDTO, Placement> {


    @Mapping(target = "purchaseOrder", ignore = true)
    @Mapping(target = "purchaseOrderItem", ignore = true)
    Placement toEntity(PlacementDTO placementDTO);

    default Placement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Placement placement = new Placement();
        placement.setId(id);
        return placement;
    }
}
