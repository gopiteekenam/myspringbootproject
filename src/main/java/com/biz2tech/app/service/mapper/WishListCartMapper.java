package com.biz2tech.app.service.mapper;

import com.biz2tech.app.domain.*;
import com.biz2tech.app.service.dto.WishListCartDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WishListCart and its DTO WishListCartDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WishListCartMapper extends EntityMapper<WishListCartDTO, WishListCart> {


    @Mapping(target = "wishListCartItems", ignore = true)
    WishListCart toEntity(WishListCartDTO wishListCartDTO);

    default WishListCart fromId(Long id) {
        if (id == null) {
            return null;
        }
        WishListCart wishListCart = new WishListCart();
        wishListCart.setId(id);
        return wishListCart;
    }
}
