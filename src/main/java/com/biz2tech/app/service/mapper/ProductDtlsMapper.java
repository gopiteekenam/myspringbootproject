package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.ProductDtls;
import com.biz2tech.app.service.dto.ProductDtlsDTO;

/**
 * Mapper for the entity ProductDtls and its DTO ProductDtlsDTO.
 */
@Mapper(componentModel = "spring", uses = {InventoryDtlsMapper.class, SellerDetailsMapper.class,
    DropShipDtlsMapper.class, ProductTagMapper.class})
public interface ProductDtlsMapper extends EntityMapper<ProductDtlsDTO, ProductDtls> {

  @Override
  @Mapping(source = "inventoryDtls.id", target = "inventoryDtlsId")
  @Mapping(source = "inventoryDtls.inventoryIdentifier", target = "inventoryIdentifier")
  @Mapping(source = "sellerDetails.id", target = "sellerDetailsId")
  @Mapping(source = "dropShipDtls.id", target = "dropShipDtlsId")
  @Mapping(
      expression = "java(org.apache.commons.collections.CollectionUtils.isNotEmpty(productDtls.getProductTags())?productDtls.getProductTags().stream().map(item->item.getTagName()).collect(java.util.stream.Collectors.joining(\",\")):null)",
      target = "prdtTagNames")
  @Mapping(target = "hasInventory",
      expression = "java(productDtls.getInventoryDtls().getAvblCnt()!=null && productDtls.getInventoryDtls().getAvblCnt().intValue()>0)")
  ProductDtlsDTO toDto(ProductDtls productDtls);

  @Override
  @Mapping(source = "inventoryDtlsId", target = "inventoryDtls")
  @Mapping(source = "inventoryIdentifier", target = "inventoryDtls.inventoryIdentifier")
  @Mapping(source = "sellerDetailsId", target = "sellerDetails")
  @Mapping(source = "dropShipDtlsId", target = "dropShipDtls")
  ProductDtls toEntity(ProductDtlsDTO productDtlsDTO);

  default ProductDtls fromId(Long id) {
    if (id == null) {
      return null;
    }
    ProductDtls productDtls = new ProductDtls();
    productDtls.setId(id);
    return productDtls;
  }
}
