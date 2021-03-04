package com.biz2tech.app.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A ShoppingCart.
 */
@Entity
@Table(name = "shopping_cart")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShoppingCart extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description")
  private String description;


  @OneToMany(mappedBy = "shoppingCart")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<ShoppingCartItem> shoppingCartItems = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public ShoppingCart description(String description) {
    this.description = description;
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<ShoppingCartItem> getShoppingCartItems() {
    return shoppingCartItems;
  }

  public ShoppingCart shoppingCartItems(Set<ShoppingCartItem> shoppingCartItems) {
    this.shoppingCartItems = shoppingCartItems;
    return this;
  }

  public ShoppingCart addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
    this.shoppingCartItems.add(shoppingCartItem);
    shoppingCartItem.setShoppingCart(this);
    return this;
  }

  public ShoppingCart removeShoppingCartItem(ShoppingCartItem shoppingCartItem) {
    this.shoppingCartItems.remove(shoppingCartItem);
    shoppingCartItem.setShoppingCart(null);
    return this;
  }

  public void setShoppingCartItems(Set<ShoppingCartItem> shoppingCartItems) {
    this.shoppingCartItems = shoppingCartItems;
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
    ShoppingCart shoppingCart = (ShoppingCart) o;
    if (shoppingCart.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), shoppingCart.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "ShoppingCart [id=" + id + ", description=" + description + ", shoppingCartItems="
        + shoppingCartItems + "]";
  }


}
