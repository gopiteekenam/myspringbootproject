package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.ShoppingCartItem;
import com.biz2tech.app.service.dto.ShoppingCartItemDTO;

/**
 * Mapper for the entity ShoppingCartItem and its DTO ShoppingCartItemDTO.
 */
@Mapper(componentModel = "spring", uses = {ShoppingCartMapper.class, ProductDtlsMapper.class})
public interface ShoppingCartItemMapper extends EntityMapper<ShoppingCartItemDTO, ShoppingCartItem> {

    @Mapping(source = "shoppingCart.id", target = "shoppingCartId")
    @Mapping(source = "productDtls.id", target = "productDtlsId")
    ShoppingCartItemDTO toDto(ShoppingCartItem shoppingCartItem);

    @Mapping(source = "shoppingCartId", target = "shoppingCart")
    @Mapping(source = "productDtlsId", target = "productDtls")
    ShoppingCartItem toEntity(ShoppingCartItemDTO shoppingCartItemDTO);

    default ShoppingCartItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setId(id);
        return shoppingCartItem;
    }
}
