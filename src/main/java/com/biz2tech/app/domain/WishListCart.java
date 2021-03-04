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

/**
 * A WishListCart.
 */
@Entity
@Table(name = "wish_list_cart")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WishListCart extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description")
  private String description;


  @OneToMany(mappedBy = "wishListCart")
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<WishListCartItem> wishListCartItems = new HashSet<>();

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

  public WishListCart description(String description) {
    this.description = description;
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<WishListCartItem> getWishListCartItems() {
    return wishListCartItems;
  }

  public WishListCart wishListCartItems(Set<WishListCartItem> wishListCartItems) {
    this.wishListCartItems = wishListCartItems;
    return this;
  }

  public WishListCart addWishListCartItem(WishListCartItem wishListCartItem) {
    this.wishListCartItems.add(wishListCartItem);
    wishListCartItem.setWishListCart(this);
    return this;
  }

  public WishListCart removeWishListCartItem(WishListCartItem wishListCartItem) {
    this.wishListCartItems.remove(wishListCartItem);
    wishListCartItem.setWishListCart(null);
    return this;
  }

  public void setWishListCartItems(Set<WishListCartItem> wishListCartItems) {
    this.wishListCartItems = wishListCartItems;
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
    WishListCart wishListCart = (WishListCart) o;
    if (wishListCart.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), wishListCart.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "WishListCart [id=" + id + ", description=" + description + ", wishListCartItems="
        + wishListCartItems + "]";
  }


}
