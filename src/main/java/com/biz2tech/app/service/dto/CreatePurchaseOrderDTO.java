package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.biz2tech.app.domain.enumeration.OrderState;
import com.biz2tech.app.domain.enumeration.OrderType;

/**
 * A DTO for the PurchaseOrder entity.
 */
public class CreatePurchaseOrderDTO implements Serializable {

  /**
   *  
   */
  private static final long serialVersionUID = 5651181250235144763L;

  private Long id;

  private String orderDescription;

  private OrderType orderType;

  private OrderState statusCode;

  private Double basePrice;

  private Double taxCharge;

  private Double shipmentCharge;

  private Double totalPrice;

  private Instant orderDate;

  private String deliveryAddress;

  private String trackingNotes;

  private Long purchaseOrderDsctCodeId;

  private List<PurchaseOrderItemDTO> purchaseOrderItemDTOs = new ArrayList<PurchaseOrderItemDTO>();


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderDescription() {
    return orderDescription;
  }

  public void setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
  }

  public OrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(OrderType orderType) {
    this.orderType = orderType;
  }

  public OrderState getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(OrderState statusCode) {
    this.statusCode = statusCode;
  }

  public Double getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  public Double getTaxCharge() {
    return taxCharge;
  }

  public void setTaxCharge(Double taxCharge) {
    this.taxCharge = taxCharge;
  }

  public Double getShipmentCharge() {
    return shipmentCharge;
  }

  public void setShipmentCharge(Double shipmentCharge) {
    this.shipmentCharge = shipmentCharge;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Instant getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Instant orderDate) {
    this.orderDate = orderDate;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public String getTrackingNotes() {
    return trackingNotes;
  }

  public void setTrackingNotes(String trackingNotes) {
    this.trackingNotes = trackingNotes;
  }


  public List<PurchaseOrderItemDTO> getPurchaseOrderItemDTOs() {
    return purchaseOrderItemDTOs;
  }

  public void setPurchaseOrderItemDTOs(List<PurchaseOrderItemDTO> purchaseOrderItemDTOs) {
    this.purchaseOrderItemDTOs = purchaseOrderItemDTOs;
  }

  public Long getPurchaseOrderDsctCodeId() {
    return purchaseOrderDsctCodeId;
  }

  public void setPurchaseOrderDsctCodeId(Long purchaseOrderDsctCodeId) {
    this.purchaseOrderDsctCodeId = purchaseOrderDsctCodeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CreatePurchaseOrderDTO purchaseOrderDTO = (CreatePurchaseOrderDTO) o;
    if (purchaseOrderDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), purchaseOrderDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "CreatePurchaseOrderDTO [id=" + id + ", orderDescription=" + orderDescription
        + ", orderType=" + orderType + ", basePrice=" + basePrice + ", taxCharge=" + taxCharge
        + ", shipmentCharge=" + shipmentCharge + ", totalPrice=" + totalPrice + ", orderDate="
        + orderDate + ", deliveryAddress=" + deliveryAddress + ", trackingNotes=" + trackingNotes
        + ", purchaseOrderDsctCodeId=" + purchaseOrderDsctCodeId + ", purchaseOrderItemDTOs="
        + purchaseOrderItemDTOs + "]";
  }



}
