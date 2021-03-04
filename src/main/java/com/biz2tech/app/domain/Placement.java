package com.biz2tech.app.domain;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Placement.
 */
@Entity
@Table(name = "placement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Placement extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "transporter_name")
  private String transporterName;

  @NotNull
  @Column(name = "tracking_details", nullable = false)
  private String trackingDetails;

  @Column(name = "placement_date")
  private Instant placementDate;

  @Column(name = "estimate_delivery_date")
  private Instant estimateDeliveryDate;

  @Column(name = "actual_delivery_date")
  private Instant actualDeliveryDate;


  @OneToOne(mappedBy = "placement")
  @JsonIgnore
  private PurchaseOrder purchaseOrder;

  @OneToOne(mappedBy = "placement")
  @JsonIgnore
  private PurchaseOrderItem purchaseOrderItem;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTransporterName() {
    return transporterName;
  }

  public Placement transporterName(String transporterName) {
    this.transporterName = transporterName;
    return this;
  }

  public void setTransporterName(String transporterName) {
    this.transporterName = transporterName;
  }

  public String getTrackingDetails() {
    return trackingDetails;
  }

  public Placement trackingDetails(String trackingDetails) {
    this.trackingDetails = trackingDetails;
    return this;
  }

  public void setTrackingDetails(String trackingDetails) {
    this.trackingDetails = trackingDetails;
  }

  public Instant getPlacementDate() {
    return placementDate;
  }

  public Placement placementDate(Instant placementDate) {
    this.placementDate = placementDate;
    return this;
  }

  public void setPlacementDate(Instant placementDate) {
    this.placementDate = placementDate;
  }

  public Instant getEstimateDeliveryDate() {
    return estimateDeliveryDate;
  }

  public Placement estimateDeliveryDate(Instant estimateDeliveryDate) {
    this.estimateDeliveryDate = estimateDeliveryDate;
    return this;
  }

  public void setEstimateDeliveryDate(Instant estimateDeliveryDate) {
    this.estimateDeliveryDate = estimateDeliveryDate;
  }

  public Instant getActualDeliveryDate() {
    return actualDeliveryDate;
  }

  public Placement actualDeliveryDate(Instant actualDeliveryDate) {
    this.actualDeliveryDate = actualDeliveryDate;
    return this;
  }

  public void setActualDeliveryDate(Instant actualDeliveryDate) {
    this.actualDeliveryDate = actualDeliveryDate;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public Placement purchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
    return this;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public PurchaseOrderItem getPurchaseOrderItem() {
    return purchaseOrderItem;
  }

  public Placement purchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
    this.purchaseOrderItem = purchaseOrderItem;
    return this;
  }

  public void setPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
    this.purchaseOrderItem = purchaseOrderItem;
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
    Placement placement = (Placement) o;
    if (placement.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), placement.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Placement [id=" + id + ", transporterName=" + transporterName + ", trackingDetails="
        + trackingDetails + ", placementDate=" + placementDate + ", estimateDeliveryDate="
        + estimateDeliveryDate + ", actualDeliveryDate=" + actualDeliveryDate + ", purchaseOrder="
        + purchaseOrder + ", purchaseOrderItem=" + purchaseOrderItem + "]";
  }

}
