package com.biz2tech.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WishListCartItem.
 */
@Entity
@Table(name = "wish_list_cart_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WishListCartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JsonIgnoreProperties("wishListCartItems")
    private WishListCart wishListCart;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ProductDtls productDtls;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public WishListCartItem quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public WishListCart getWishListCart() {
        return wishListCart;
    }

    public WishListCartItem wishListCart(WishListCart wishListCart) {
        this.wishListCart = wishListCart;
        return this;
    }

    public void setWishListCart(WishListCart wishListCart) {
        this.wishListCart = wishListCart;
    }

    public ProductDtls getProductDtls() {
        return productDtls;
    }

    public WishListCartItem productDtls(ProductDtls productDtls) {
        this.productDtls = productDtls;
        return this;
    }

    public void setProductDtls(ProductDtls productDtls) {
        this.productDtls = productDtls;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WishListCartItem wishListCartItem = (WishListCartItem) o;
        if (wishListCartItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wishListCartItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WishListCartItem{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
