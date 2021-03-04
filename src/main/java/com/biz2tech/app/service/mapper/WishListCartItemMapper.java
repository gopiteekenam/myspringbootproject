package com.biz2tech.app.service.mapper;

import com.biz2tech.app.domain.*;
import com.biz2tech.app.service.dto.WishListCartItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WishListCartItem and its DTO WishListCartItemDTO.
 */
@Mapper(componentModel = "spring", uses = {WishListCartMapper.class, ProductDtlsMapper.class})
public interface WishListCartItemMapper extends EntityMapper<WishListCartItemDTO, WishListCartItem> {

    @Mapping(source = "wishListCart.id", target = "wishListCartId")
    @Mapping(source = "productDtls.id", target = "productDtlsId")
    WishListCartItemDTO toDto(WishListCartItem wishListCartItem);

    @Mapping(source = "wishListCartId", target = "wishListCart")
    @Mapping(source = "productDtlsId", target = "productDtls")
    WishListCartItem toEntity(WishListCartItemDTO wishListCartItemDTO);

    default WishListCartItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        WishListCartItem wishListCartItem = new WishListCartItem();
        wishListCartItem.setId(id);
        return wishListCartItem;
    }
}
