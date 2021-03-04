package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;

import com.biz2tech.app.domain.ProductColor;
import com.biz2tech.app.service.dto.ProductColorDTO;

/**
 * Mapper for the entity ProductColor and its DTO ProductColorDTO.
 */
@Mapper(componentModel = "spring")
public interface ProductColorMapper extends EntityMapper<ProductColorDTO, ProductColor> {

  @Override
  ProductColorDTO toDto(ProductColor productColor);

  @Override
  ProductColor toEntity(ProductColorDTO productColorDTO);

  default ProductColor fromId(Long id) {
    if (id == null) {
      return null;
    }
    ProductColor productColor = new ProductColor();
    productColor.setId(id);
    return productColor;
  }
}
