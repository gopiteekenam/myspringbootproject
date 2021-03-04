package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.InventoryDtls;
import com.biz2tech.app.service.dto.ImportInventoryDetailDto;

@Mapper(componentModel = "spring", uses = {})
public interface ImportInventoryDtlsMapper
    extends EntityMapper<ImportInventoryDetailDto, InventoryDtls> {


  @Override
  @Mapping(source = "itemId", target = "inventoryIdentifier")
  InventoryDtls toEntity(ImportInventoryDetailDto importInventoryDetailDto);

}
