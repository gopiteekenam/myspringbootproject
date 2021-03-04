package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.biz2tech.app.domain.enumeration.OrderState;

/**
 * A DTO for the PurchaseOrderItem entity.
 */
public class PurchaseOrderItemDTO implements Serializable {

  private static final long serialVersionUID = 8940312432186219077L;

  private Long id;

  private Integer quantity;

  private OrderState statusCode;

  private Double basePrice;

  private Double taxCharge;

  private Double shipmentCharge;

  private Double totalPrice;

  private Long placementId;

  private Long productDtlsId;

  private Long purchaseOrderId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
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


  public Long getPlacementId() {
    return placementId;
  }

  public void setPlacementId(Long placementId) {
    this.placementId = placementId;
  }

  public Long getProductDtlsId() {
    return productDtlsId;
  }

  public void setProductDtlsId(Long productDtlsId) {
    this.productDtlsId = productDtlsId;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public OrderState getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(OrderState statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PurchaseOrderItemDTO purchaseOrderItemDTO = (PurchaseOrderItemDTO) o;
    if (purchaseOrderItemDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), purchaseOrderItemDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "PurchaseOrderItemDTO [id=" + id + ", quantity=" + quantity + ", statusCode="
        + statusCode + ", basePrice=" + basePrice + ", taxCharge=" + taxCharge + ", shipmentCharge="
        + shipmentCharge + ", totalPrice=" + totalPrice + ", placementId=" + placementId
        + ", productDtlsId=" + productDtlsId + ", purchaseOrderId=" + purchaseOrderId + "]";
  }


}
