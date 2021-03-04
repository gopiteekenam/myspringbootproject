package com.biz2tech.app.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
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
 * A TaxItem.
 */
@Entity
@Table(name = "tax_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TaxItem implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "pincode", precision = 10, scale = 2)
  private BigDecimal pincode;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "percentage")
  private Double percentage;

  @Column(name = "applicable_from")
  private Instant applicableFrom;

  @Column(name = "applicable_till")
  private Instant applicableTill;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPincode() {
    return pincode;
  }

  public TaxItem pincode(BigDecimal pincode) {
    this.pincode = pincode;
    return this;
  }

  public void setPincode(BigDecimal pincode) {
    this.pincode = pincode;
  }

  public String getName() {
    return name;
  }

  public TaxItem name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public TaxItem description(String description) {
    this.description = description;
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPercentage() {
    return percentage;
  }

  public TaxItem percentage(Double percentage) {
    this.percentage = percentage;
    return this;
  }

  public void setPercentage(Double percentage) {
    this.percentage = percentage;
  }

  public Instant getApplicableFrom() {
    return applicableFrom;
  }

  public TaxItem applicableFrom(Instant applicableFrom) {
    this.applicableFrom = applicableFrom;
    return this;
  }

  public void setApplicableFrom(Instant applicableFrom) {
    this.applicableFrom = applicableFrom;
  }

  public Instant getApplicableTill() {
    return applicableTill;
  }

  public TaxItem applicableTill(Instant applicableTill) {
    this.applicableTill = applicableTill;
    return this;
  }

  public void setApplicableTill(Instant applicableTill) {
    this.applicableTill = applicableTill;
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
    TaxItem taxItem = (TaxItem) o;
    if (taxItem.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), taxItem.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "TaxItem{" + "id=" + getId() + ", pincode=" + getPincode() + ", name='" + getName() + "'"
        + ", description='" + getDescription() + "'" + ", percentage=" + getPercentage()
        + ", applicableFrom='" + getApplicableFrom() + "'" + ", applicableTill='"
        + getApplicableTill() + "'" + "}";
  }
}
