package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;

import com.biz2tech.app.domain.ProductRating;
import com.biz2tech.app.service.dto.ProductRatingDTO;

/**
 * Mapper for the entity ProductRating and its DTO ProductRatingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductRatingMapper extends EntityMapper<ProductRatingDTO, ProductRating> {


  @Override
  ProductRating toEntity(ProductRatingDTO productRatingDTO);

  default ProductRating fromId(Long id) {
    if (id == null) {
      return null;
    }
    ProductRating productRating = new ProductRating();
    productRating.setId(id);
    return productRating;
  }
}
