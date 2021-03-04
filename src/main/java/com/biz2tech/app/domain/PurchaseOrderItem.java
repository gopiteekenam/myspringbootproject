package com.biz2tech.app.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biz2tech.app.domain.enumeration.OrderState;

/**
 * A PurchaseOrderItem.
 */
@Entity
@Table(name = "purchase_order_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PurchaseOrderItem extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "quantity")
  private Integer quantity;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status_code", nullable = false)
  private OrderState statusCode;

  @Column(name = "base_price")
  private Double basePrice;

  @Column(name = "tax_charge")
  private Double taxCharge;

  @Column(name = "shipment_charge")
  private Double shipmentCharge;

  @Column(name = "total_price")
  private Double totalPrice;


  @OneToOne
  @JoinColumn(unique = true)
  private Placement placement;

  @ManyToOne
  private ProductDtls productDtls;

  @ManyToOne
  private PurchaseOrder purchaseOrder;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public PurchaseOrderItem quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public OrderState getStatusCode() {
    return statusCode;
  }

  public PurchaseOrderItem statusCode(OrderState statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public void setStatusCode(OrderState statusCode) {
    this.statusCode = statusCode;
  }

  public Double getBasePrice() {
    return basePrice;
  }

  public PurchaseOrderItem basePrice(Double basePrice) {
    this.basePrice = basePrice;
    return this;
  }

  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  public Double getTaxCharge() {
    return taxCharge;
  }

  public PurchaseOrderItem taxCharge(Double taxCharge) {
    this.taxCharge = taxCharge;
    return this;
  }

  public void setTaxCharge(Double taxCharge) {
    this.taxCharge = taxCharge;
  }

  public Double getShipmentCharge() {
    return shipmentCharge;
  }

  public PurchaseOrderItem shipmentCharge(Double shipmentCharge) {
    this.shipmentCharge = shipmentCharge;
    return this;
  }

  public void setShipmentCharge(Double shipmentCharge) {
    this.shipmentCharge = shipmentCharge;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public PurchaseOrderItem totalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }


  public Placement getPlacement() {
    return placement;
  }

  public PurchaseOrderItem placement(Placement placement) {
    this.placement = placement;
    return this;
  }

  public void setPlacement(Placement placement) {
    this.placement = placement;
  }

  public ProductDtls getProductDtls() {
    return productDtls;
  }

  public PurchaseOrderItem productDtls(ProductDtls productDtls) {
    this.productDtls = productDtls;
    return this;
  }

  public void setProductDtls(ProductDtls productDtls) {
    this.productDtls = productDtls;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public PurchaseOrderItem purchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
    return this;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
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
    PurchaseOrderItem purchaseOrderItem = (PurchaseOrderItem) o;
    if (purchaseOrderItem.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), purchaseOrderItem.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }


}
