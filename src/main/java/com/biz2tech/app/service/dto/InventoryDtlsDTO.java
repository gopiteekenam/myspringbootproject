package com.biz2tech.app.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the InventoryDtls entity.
 */
public class InventoryDtlsDTO implements Serializable {

  private static final long serialVersionUID = -8519290914469915484L;

  private Long id;

  private String desc;
  private String inventoryIdentifier;

  private BigDecimal totalCnt;

  private BigDecimal avblCnt;

  private BigDecimal sellCnt;

  private Double basePrice;

  private Double markedPercentage;

  private Double sellPrice;

  private String currencyCode;

  private String createdBy;

  private Instant createdOn;

  private String lastUpdatedBy;

  private Instant lastUpdatedOn;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
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

  public void setTotalCnt(BigDecimal totalCnt) {
    this.totalCnt = totalCnt;
  }

  public BigDecimal getAvblCnt() {
    return avblCnt;
  }

  public void setAvblCnt(BigDecimal avblCnt) {
    this.avblCnt = avblCnt;
  }

  public BigDecimal getSellCnt() {
    return sellCnt;
  }

  public void setSellCnt(BigDecimal sellCnt) {
    this.sellCnt = sellCnt;
  }

  public Double getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  public Double getMarkedPercentage() {
    return markedPercentage;
  }

  public void setMarkedPercentage(Double markedPercentage) {
    this.markedPercentage = markedPercentage;
  }

  public Double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public Instant getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  public void setLastUpdatedOn(Instant lastUpdatedOn) {
    this.lastUpdatedOn = lastUpdatedOn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    InventoryDtlsDTO inventoryDtlsDTO = (InventoryDtlsDTO) o;
    if (inventoryDtlsDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), inventoryDtlsDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "InventoryDtlsDTO{" + "id=" + getId() + ", desc='" + getDesc() + "'" + ", totalCnt="
        + getTotalCnt() + ", avblCnt=" + getAvblCnt() + ", sellCnt=" + getSellCnt() + ", basePrice="
        + getBasePrice() + ", markedPercentage=" + getMarkedPercentage() + ", sellPrice="
        + getSellPrice() + ", currencyCode='" + getCurrencyCode() + "'" + ", createdBy='"
        + getCreatedBy() + "'" + ", createdOn='" + getCreatedOn() + "'" + ", lastUpdatedBy='"
        + getLastUpdatedBy() + "'" + ", lastUpdatedOn='" + getLastUpdatedOn() + "'" + "}";
  }
}
