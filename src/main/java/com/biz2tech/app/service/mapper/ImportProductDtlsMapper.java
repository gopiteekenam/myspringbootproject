package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.ProductDtls;
import com.biz2tech.app.service.dto.ImportInventoryDetailDto;


@Mapper(componentModel = "spring", uses = {ImportInventoryDtlsMapper.class})
public interface ImportProductDtlsMapper
    extends EntityMapper<ImportInventoryDetailDto, ProductDtls> {
  @Override
  @Mapping(
      expression = "java( (importInventoryDetailDto.getBasePrice() == null)        ? ((null != importInventoryDetailDto.getSellPrice()) "
          + "           ? Double.parseDouble(importInventoryDetailDto.getSellPrice())            : null)      "
          + "  : ((null!=importInventoryDetailDto.getBasePrice())?Double.parseDouble(importInventoryDetailDto.getBasePrice()):null) )",
      target = "basePrice")

  @Mapping(
      expression = "java(org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase(\"true\", importInventoryDetailDto.getIsDropShipBoolean()))",
      target = "isDropShip")
  @Mapping(
      expression = "java(org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase(\"true\", importInventoryDetailDto.getClearence()))",
      target = "isClearence")
  @Mapping(
      expression = "java(org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase(\"true\", importInventoryDetailDto.getDiscontinued()))",
      target = "isDiscontinued")
  @Mapping(
      expression = "java(org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase(\"true\", importInventoryDetailDto.getSale()))",
      target = "isSale")
  @Mapping(
      expression = "java(org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase(\"true\", importInventoryDetailDto.getPremium()))",
      target = "isPremium")
  @Mapping(source = "type", target = "prdtType")
  @Mapping(source = "category", target = "prdtCategory")
  ProductDtls toEntity(ImportInventoryDetailDto importInventoryDetailDto);

}
