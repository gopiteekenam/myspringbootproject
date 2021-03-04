package com.biz2tech.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ShoppingCartItem entity.
 */
public class ShoppingCartItemDTO implements Serializable {

    private Long id;

    private Integer quantity;

    private Long shoppingCartId;

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

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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

        ShoppingCartItemDTO shoppingCartItemDTO = (ShoppingCartItemDTO) o;
        if(shoppingCartItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shoppingCartItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShoppingCartItemDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
