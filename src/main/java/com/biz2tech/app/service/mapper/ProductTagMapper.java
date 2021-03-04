package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;

import com.biz2tech.app.domain.ProductTag;
import com.biz2tech.app.service.dto.ProductTagDTO;

/**
 * Mapper for the entity ProductTag and its DTO ProductTagDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductTagMapper extends EntityMapper<ProductTagDTO, ProductTag> {


  @Override
  ProductTag toEntity(ProductTagDTO productTagDTO);

  default ProductTag fromId(Long id) {
    if (id == null) {
      return null;
    }
    ProductTag productTag = new ProductTag();
    productTag.setId(id);
    return productTag;
  }
}
