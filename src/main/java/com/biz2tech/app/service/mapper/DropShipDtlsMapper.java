package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.DropShipDtls;
import com.biz2tech.app.service.dto.DropShipDtlsDTO;

/**
 * Mapper for the entity DropShipDtls and its DTO DropShipDtlsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DropShipDtlsMapper extends EntityMapper<DropShipDtlsDTO, DropShipDtls> {


  @Override
  @Mapping(target = "productDtls", ignore = true)
  DropShipDtls toEntity(DropShipDtlsDTO dropShipDtlsDTO);

  default DropShipDtls fromId(Long id) {
    if (id == null) {
      return null;
    }
    DropShipDtls dropShipDtls = new DropShipDtls();
    dropShipDtls.setId(id);
    return dropShipDtls;
  }
}
