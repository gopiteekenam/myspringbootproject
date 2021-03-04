package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the WishListCartItem entity.
 */
public class WishListCartItemDTO implements Serializable {

    private Long id;

    private Integer quantity;

    private Long wishListCartId;

    private Long productDtlsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getWishListCartId() {
        return wishListCartId;
    }

    public void setWishListCartId(Long wishListCartId) {
        this.wishListCartId = wishListCartId;
    }

    public Long getProductDtlsId() {
        return productDtlsId;
    }

    public void setProductDtlsId(Long productDtlsId) {
        this.productDtlsId = productDtlsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WishListCartItemDTO wishListCartItemDTO = (WishListCartItemDTO) o;
        if (wishListCartItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wishListCartItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WishListCartItemDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", wishListCart=" + getWishListCartId() +
            ", productDtls=" + getProductDtlsId() +
            "}";
    }
}
