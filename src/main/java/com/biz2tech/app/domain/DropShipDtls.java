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
 * A DropShipDtls.
 */
@Entity
@Table(name = "drop_ship_dtls")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DropShipDtls extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "vendor_name")
  private String vendorName;

  @Column(name = "vendor_url")
  private String vendorUrl;

  @Column(name = "total_chrg_to_cust")
  private Double totalChrgToCust;

  @Column(name = "currency_code")
  private String currencyCode;

  @Column(name = "margin")
  private Double margin;


  @OneToMany(mappedBy = "dropShipDtls")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<ProductDtls> productDtls = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getVendorName() {
    return vendorName;
  }

  public DropShipDtls vendorName(String vendorName) {
    this.vendorName = vendorName;
    return this;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getVendorUrl() {
    return vendorUrl;
  }

  public DropShipDtls vendorUrl(String vendorUrl) {
    this.vendorUrl = vendorUrl;
    return this;
  }

  public void setVendorUrl(String vendorUrl) {
    this.vendorUrl = vendorUrl;
  }

  public Double getTotalChrgToCust() {
    return totalChrgToCust;
  }

  public DropShipDtls totalChrgToCust(Double totalChrgToCust) {
    this.totalChrgToCust = totalChrgToCust;
    return this;
  }

  public void setTotalChrgToCust(Double totalChrgToCust) {
    this.totalChrgToCust = totalChrgToCust;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public DropShipDtls currencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Double getMargin() {
    return margin;
  }

  public DropShipDtls margin(Double margin) {
    this.margin = margin;
    return this;
  }

  public void setMargin(Double margin) {
    this.margin = margin;
  }


  public Set<ProductDtls> getProductDtls() {
    return productDtls;
  }

  public DropShipDtls productDtls(Set<ProductDtls> productDtls) {
    this.productDtls = productDtls;
    return this;
  }

  public DropShipDtls addProductDtls(ProductDtls productDtls) {
    this.productDtls.add(productDtls);
    productDtls.setDropShipDtls(this);
    return this;
  }

  public DropShipDtls removeProductDtls(ProductDtls productDtls) {
    this.productDtls.remove(productDtls);
    productDtls.setDropShipDtls(null);
    return this;
  }

  public void setProductDtls(Set<ProductDtls> productDtls) {
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
    DropShipDtls dropShipDtls = (DropShipDtls) o;
    if (dropShipDtls.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), dropShipDtls.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "DropShipDtls [id=" + id + ", vendorName=" + vendorName + ", vendorUrl=" + vendorUrl
        + ", totalChrgToCust=" + totalChrgToCust + ", currencyCode=" + currencyCode + ", margin="
        + margin + ", productDtls=" + productDtls + "]";
  }

}
