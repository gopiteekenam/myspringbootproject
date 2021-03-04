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
 * A ProductTag.
 */
@Entity
@Table(name = "product_tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductTag extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "tag_name")
  private String tagName;

  @Column(name = "tag_description")
  private String tagDescription;

  @Column(name = "product_tag_parent_id", precision = 10, scale = 2)
  private BigDecimal productTagParentId;



  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTagName() {
    return tagName;
  }

  public ProductTag tagName(String tagName) {
    this.tagName = tagName;
    return this;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public String getTagDescription() {
    return tagDescription;
  }

  public ProductTag tagDescription(String tagDescription) {
    this.tagDescription = tagDescription;
    return this;
  }

  public void setTagDescription(String tagDescription) {
    this.tagDescription = tagDescription;
  }

  public BigDecimal getProductTagParentId() {
    return productTagParentId;
  }

  public ProductTag productTagParentId(BigDecimal productTagParentId) {
    this.productTagParentId = productTagParentId;
    return this;
  }

  public void setProductTagParentId(BigDecimal productTagParentId) {
    this.productTagParentId = productTagParentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductTag productTag = (ProductTag) o;
    if (productTag.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), productTag.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "ProductTag [id=" + id + ", tagName=" + tagName + ", tagDescription=" + tagDescription
        + ", productTagParentId=" + productTagParentId + "]";
  }

}
