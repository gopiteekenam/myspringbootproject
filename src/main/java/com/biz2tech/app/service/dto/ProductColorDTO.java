package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the ProductColor entity.
 */
public class ProductColorDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -7865797018875684425L;

  private Long id;

  private BigDecimal prdtId;

  private BigDecimal colorPosition;

  private BigDecimal userRating;
  private String userReviewDescription;

  private BigDecimal colorMoodLevel;

  private String createdBy;

  private Instant createdOn;

  private String lastUpdatedBy;

  private Instant lastUpdatedOn;


  public String getUserReviewDescription() {
    return userReviewDescription;
  }

  public void setUserReviewDescription(String userReviewDescription) {
    this.userReviewDescription = userReviewDescription;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPrdtId() {
    return prdtId;
  }

  public void setPrdtId(BigDecimal prdtId) {
    this.prdtId = prdtId;
  }

  public BigDecimal getColorPosition() {
    return colorPosition;
  }

  public void setColorPosition(BigDecimal colorPosition) {
    this.colorPosition = colorPosition;
  }

  public BigDecimal getUserRating() {
    return userRating;
  }

  public void setUserRating(BigDecimal userRating) {
    this.userRating = userRating;
  }

  public BigDecimal getColorMoodLevel() {
    return colorMoodLevel;
  }

  public void setColorMoodLevel(BigDecimal colorMoodLevel) {
    this.colorMoodLevel = colorMoodLevel;
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

    ProductColorDTO productColorDTO = (ProductColorDTO) o;
    if (productColorDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), productColorDTO.getId());
  }


  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "ProductColorDTO{" + "id=" + getId() + ", prdtId=" + getPrdtId() + ", colorPosition="
        + getColorPosition() + ", userRating=" + getUserRating() + ", colorMoodLevel="
        + getColorMoodLevel() + ", createdBy='" + getCreatedBy() + "'" + ", createdOn='"
        + getCreatedOn() + "'" + ", lastUpdatedBy='" + getLastUpdatedBy() + "'"
        + ", lastUpdatedOn='" + getLastUpdatedOn() + "'" + "}";
  }
}
