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
 * A ProductColor.
 */
@Entity
@Table(name = "product_user_color")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductColor extends AbstractAuditingEntity {


  private static final long serialVersionUID = 6052079008731443235L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "prdt_id")
  private BigDecimal prdtId;

  @Column(name = "color_position")
  private BigDecimal colorPosition;

  @Column(name = "user_rating")
  private Double userRating;


  @Column(name = "user_review_description")
  private String userReviewDescription;

  @Column(name = "color_mood_level")
  private BigDecimal colorMoodLevel;



  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPrdtId() {
    return prdtId;
  }

  public ProductColor prdtId(BigDecimal prdtId) {
    this.prdtId = prdtId;
    return this;
  }

  public void setPrdtId(BigDecimal prdtId) {
    this.prdtId = prdtId;
  }

  public BigDecimal getColorPosition() {
    return colorPosition;
  }

  public ProductColor colorPosition(BigDecimal colorPosition) {
    this.colorPosition = colorPosition;
    return this;
  }

  public void setColorPosition(BigDecimal colorPosition) {
    this.colorPosition = colorPosition;
  }

  public ProductColor userReviewDescription(String userReviewDescription) {
    this.userReviewDescription = userReviewDescription;
    return this;
  }

  public String getUserReviewDescription() {
    return userReviewDescription;
  }

  public void setUserReviewDescription(String userReviewDescription) {
    this.userReviewDescription = userReviewDescription;
  }

  public Double getUserRating() {
    return userRating;
  }

  public ProductColor userRating(Double userRating) {
    this.userRating = userRating;
    return this;
  }

  public void setUserRating(Double userRating) {
    this.userRating = userRating;
  }

  public BigDecimal getColorMoodLevel() {
    return colorMoodLevel;
  }

  public ProductColor colorMoodLevel(BigDecimal colorMoodLevel) {
    this.colorMoodLevel = colorMoodLevel;
    return this;
  }

  public void setColorMoodLevel(BigDecimal colorMoodLevel) {
    this.colorMoodLevel = colorMoodLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductColor productColor = (ProductColor) o;
    if (productColor.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), productColor.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "ProductColor [id=" + id + ", prdtId=" + prdtId + ", colorPosition=" + colorPosition
        + ", userRating=" + userRating + ", colorMoodLevel=" + colorMoodLevel + "]";
  }

}
