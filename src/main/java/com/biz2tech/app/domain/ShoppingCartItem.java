package com.biz2tech.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ShoppingCartItem.
 */
@Entity
@Table(name = "shopping_cart_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShoppingCartItem implements Serializable {

  private static final long serialVersionUID = -7697842437615825151L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "quantity")
  private Integer quantity;

  @ManyToOne
  private ShoppingCart shoppingCart;

  @ManyToOne
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

  public ShoppingCartItem quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  public ShoppingCartItem shoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
    return this;
  }

  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public ProductDtls getProductDtls() {
    return productDtls;
  }

  public ShoppingCartItem productDtls(ProductDtls productDtls) {
    this.productDtls = productDtls;
    return this;
  }

  public void setProductDtls(ProductDtls productDtls) {
    this.productDtls = productDtls;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not
  // remove

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShoppingCartItem shoppingCartItem = (ShoppingCartItem) o;
    if (shoppingCartItem.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), shoppingCartItem.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "ShoppingCartItem{" + "id=" + getId() + ", quantity=" + getQuantity() + "}";
  }
}
