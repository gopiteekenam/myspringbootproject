package com.biz2tech.app.domain;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biz2tech.app.domain.enumeration.OrderState;
import com.biz2tech.app.domain.enumeration.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A PurchaseOrder.
 */
@Entity
@Table(name = "purchase_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PurchaseOrder extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_description")
  private String orderDescription;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "order_type", nullable = false)
  private OrderType orderType;

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

  @Column(name = "order_date")
  private Instant orderDate;

  @Column(name = "delivery_address", nullable = false)
  private String deliveryAddress;

  @Column(name = "tracking_notes")
  private String trackingNotes;


  @OneToOne
  @JoinColumn(unique = true)
  private Placement placement;

  @OneToOne
  @JoinColumn(unique = true)
  private Payment payment;

  @OneToMany(mappedBy = "purchaseOrder")
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<PurchaseOrderItem> purchaseOrderItems = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties("purchaseOrders")
  private PurchaseOrderDsctCode purchaseOrderDsctCode;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderDescription() {
    return orderDescription;
  }

  public PurchaseOrder orderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
    return this;
  }

  public void setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
  }

  public OrderType getOrderType() {
    return orderType;
  }

  public PurchaseOrder orderType(OrderType orderType) {
    this.orderType = orderType;
    return this;
  }

  public void setOrderType(OrderType orderType) {
    this.orderType = orderType;
  }

  public OrderState getStatusCode() {
    return statusCode;
  }

  public PurchaseOrder statusCode(OrderState statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public void setStatusCode(OrderState statusCode) {
    this.statusCode = statusCode;
  }

  public Double getBasePrice() {
    return basePrice;
  }

  public PurchaseOrder basePrice(Double basePrice) {
    this.basePrice = basePrice;
    return this;
  }

  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  public Double getTaxCharge() {
    return taxCharge;
  }

  public PurchaseOrder taxCharge(Double taxCharge) {
    this.taxCharge = taxCharge;
    return this;
  }

  public void setTaxCharge(Double taxCharge) {
    this.taxCharge = taxCharge;
  }

  public Double getShipmentCharge() {
    return shipmentCharge;
  }

  public PurchaseOrder shipmentCharge(Double shipmentCharge) {
    this.shipmentCharge = shipmentCharge;
    return this;
  }

  public void setShipmentCharge(Double shipmentCharge) {
    this.shipmentCharge = shipmentCharge;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public PurchaseOrder totalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Instant getOrderDate() {
    return orderDate;
  }

  public PurchaseOrder orderDate(Instant orderDate) {
    this.orderDate = orderDate;
    return this;
  }

  public void setOrderDate(Instant orderDate) {
    this.orderDate = orderDate;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public PurchaseOrder deliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
    return this;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public String getTrackingNotes() {
    return trackingNotes;
  }

  public PurchaseOrder trackingNotes(String trackingNotes) {
    this.trackingNotes = trackingNotes;
    return this;
  }

  public void setTrackingNotes(String trackingNotes) {
    this.trackingNotes = trackingNotes;
  }

  public Placement getPlacement() {
    return placement;
  }

  public PurchaseOrder placement(Placement placement) {
    this.placement = placement;
    return this;
  }

  public void setPlacement(Placement placement) {
    this.placement = placement;
  }

  public Payment getPayment() {
    return payment;
  }

  public PurchaseOrder payment(Payment payment) {
    this.payment = payment;
    return this;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Set<PurchaseOrderItem> getPurchaseOrderItems() {
    return purchaseOrderItems;
  }

  public PurchaseOrder purchaseOrderItems(Set<PurchaseOrderItem> purchaseOrderItems) {
    this.purchaseOrderItems = purchaseOrderItems;
    return this;
  }

  public PurchaseOrder addPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
    this.purchaseOrderItems.add(purchaseOrderItem);
    purchaseOrderItem.setPurchaseOrder(this);
    return this;
  }

  public PurchaseOrder removePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
    this.purchaseOrderItems.remove(purchaseOrderItem);
    purchaseOrderItem.setPurchaseOrder(null);
    return this;
  }

  public void setPurchaseOrderItems(Set<PurchaseOrderItem> purchaseOrderItems) {
    this.purchaseOrderItems = purchaseOrderItems;
  }

  public PurchaseOrderDsctCode getPurchaseOrderDsctCode() {
    return purchaseOrderDsctCode;
  }

  public PurchaseOrder purchaseOrderDsctCode(PurchaseOrderDsctCode purchaseOrderDsctCode) {
    this.purchaseOrderDsctCode = purchaseOrderDsctCode;
    return this;
  }

  public void setPurchaseOrderDsctCode(PurchaseOrderDsctCode purchaseOrderDsctCode) {
    this.purchaseOrderDsctCode = purchaseOrderDsctCode;
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
    PurchaseOrder purchaseOrder = (PurchaseOrder) o;
    if (purchaseOrder.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), purchaseOrder.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }



}
