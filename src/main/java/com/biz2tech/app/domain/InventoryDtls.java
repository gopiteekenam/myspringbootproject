package com.biz2tech.app.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A InventoryDtls.
 */
@Entity
@Table(name = "inventory_dtls")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InventoryDtls extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "jhi_desc")
  private String desc;

  @Column(name = "inventory_identifier")
  private String inventoryIdentifier;

  @Column(name = "total_cnt", precision = 10, scale = 2)
  private BigDecimal totalCnt;

  @Column(name = "avbl_cnt", precision = 10, scale = 2)
  private BigDecimal avblCnt;

  @Column(name = "sell_cnt", precision = 10, scale = 2)
  private BigDecimal sellCnt;

  @Column(name = "base_price")
  private Double basePrice;

  @Column(name = "marked_percentage")
  private Double markedPercentage;

  @Column(name = "sell_price")
  private Double sellPrice;

  @Column(name = "currency_code")
  private String currencyCode;


  @OneToOne(mappedBy = "inventoryDtls")
  @JsonIgnore
  private ProductDtls productDtls;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDesc() {
    return desc;
  }

  public InventoryDtls desc(String desc) {
    this.desc = desc;
    return this;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public InventoryDtls inventoryIdentifier(String inventoryIdentifier) {
    this.inventoryIdentifier = inventoryIdentifier;
    return this;
  }

  public String getInventoryIdentifier() {
    return inventoryIdentifier;
  }

  public void setInventoryIdentifier(String inventoryIdentifier) {
    this.inventoryIdentifier = inventoryIdentifier;
  }

  public BigDecimal getTotalCnt() {
    return totalCnt;
  }

  public InventoryDtls totalCnt(BigDecimal totalCnt) {
    this.totalCnt = totalCnt;
    return this;
  }

  public void setTotalCnt(BigDecimal totalCnt) {
    this.totalCnt = totalCnt;
  }

  public BigDecimal getAvblCnt() {
    return avblCnt;
  }

  public InventoryDtls avblCnt(BigDecimal avblCnt) {
    this.avblCnt = avblCnt;
    return this;
  }

  public void setAvblCnt(BigDecimal avblCnt) {
    this.avblCnt = avblCnt;
  }

  public BigDecimal getSellCnt() {
    return sellCnt;
  }

  public InventoryDtls sellCnt(BigDecimal sellCnt) {
    this.sellCnt = sellCnt;
    return this;
  }

  public void setSellCnt(BigDecimal sellCnt) {
    this.sellCnt = sellCnt;
  }

  public Double getBasePrice() {
    return basePrice;
  }

  public InventoryDtls basePrice(Double basePrice) {
    this.basePrice = basePrice;
    return this;
  }

  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  public Double getMarkedPercentage() {
    return markedPercentage;
  }

  public InventoryDtls markedPercentage(Double markedPercentage) {
    this.markedPercentage = markedPercentage;
    return this;
  }

  public void setMarkedPercentage(Double markedPercentage) {
    this.markedPercentage = markedPercentage;
  }

  public Double getSellPrice() {
    return sellPrice;
  }

  public InventoryDtls sellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
    return this;
  }

  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public InventoryDtls currencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }


  public ProductDtls getProductDtls() {
    return productDtls;
  }

  public InventoryDtls productDtls(ProductDtls productDtls) {
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
    InventoryDtls inventoryDtls = (InventoryDtls) o;
    if (inventoryDtls.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), inventoryDtls.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "InventoryDtls [id=" + id + ", desc=" + desc + ", inventoryIdentifier="
        + inventoryIdentifier + ", totalCnt=" + totalCnt + ", avblCnt=" + avblCnt + ", sellCnt="
        + sellCnt + ", basePrice=" + basePrice + ", markedPercentage=" + markedPercentage
        + ", sellPrice=" + sellPrice + ", currencyCode=" + currencyCode + ", productDtls="
        + productDtls + "]";
  }

  public void merge(InventoryDtls provider) {
    setSellPrice(provider.getSellPrice());
    setBasePrice(provider.getBasePrice());
    setAvblCnt(provider.getAvblCnt());
  }

}
