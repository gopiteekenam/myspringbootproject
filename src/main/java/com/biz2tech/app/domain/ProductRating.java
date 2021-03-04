package com.biz2tech.app.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProductRating.
 */
@Entity
@Table(name = "product_rating")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductRating extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "prdt_id")
  private BigDecimal prdtId;

  @Column(name = "user_rating")
  private Double userRating;

  @Column(name = "user_review_count")
  private Double userReviewCount;

  @Column(name = "color_position")
  private BigDecimal colorPosition;

  @Column(name = "color_mood_level")
  private BigDecimal colorMoodLevel;



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductRating id(Long id) {
    this.id = id;
    return this;
  }

  public BigDecimal getPrdtId() {
    return prdtId;
  }

  public ProductRating prdtId(BigDecimal prdtId) {
    this.prdtId = prdtId;
    return this;
  }

  public void setPrdtId(BigDecimal prdtId) {
    this.prdtId = prdtId;
  }

  public Double getUserRating() {
    return userRating;
  }

  public ProductRating userRating(Double userRating) {
    this.userRating = userRating;
    return this;
  }

  public void setUserRating(Double userRating) {
    this.userRating = userRating;
  }

  public BigDecimal getColorPosition() {
    return colorPosition;
  }

  public ProductRating colorPosition(BigDecimal colorPosition) {
    this.colorPosition = colorPosition;
    return this;
  }

  public void setColorPosition(BigDecimal colorPosition) {
    this.colorPosition = colorPosition;
  }

  public BigDecimal getColorMoodLevel() {
    return colorMoodLevel;
  }

  public ProductRating colorMoodLevel(BigDecimal colorMoodLevel) {
    this.colorMoodLevel = colorMoodLevel;
    return this;
  }

  public void setColorMoodLevel(BigDecimal colorMoodLevel) {
    this.colorMoodLevel = colorMoodLevel;
  }



  public Double getUserReviewCount() {
    return userReviewCount;
  }

  public ProductRating userReviewCount(Double userReviewCount) {
    this.userReviewCount = userReviewCount;
    return this;
  }



  public void setUserReviewCount(Double userReviewCount) {
    this.userReviewCount = userReviewCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductRating productRating = (ProductRating) o;
    if (productRating.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), productRating.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "ProductRating [id=" + id + ", prdtId=" + prdtId + ", userRating=" + userRating
        + ", userReviewCount=" + userReviewCount + ", colorPosition=" + colorPosition
        + ", colorMoodLevel=" + colorMoodLevel + "]";
  }

}
