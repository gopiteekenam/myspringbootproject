package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.ShoppingCart;
import com.biz2tech.app.service.dto.ShoppingCartDTO;

/**
 * Mapper for the entity ShoppingCart and its DTO ShoppingCartDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShoppingCartMapper extends EntityMapper<ShoppingCartDTO, ShoppingCart> {


    @Mapping(target = "shoppingCartItems", ignore = true)
    ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO);

    default ShoppingCart fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(id);
        return shoppingCart;
    }
}
