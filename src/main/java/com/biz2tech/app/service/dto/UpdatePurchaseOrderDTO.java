package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.biz2tech.app.domain.enumeration.OrderState;
import com.biz2tech.app.domain.enumeration.OrderType;
import com.biz2tech.app.domain.enumeration.PaymentState;

/**
 * A DTO for the PurchaseOrder entity.
 */
public class UpdatePurchaseOrderDTO implements Serializable {

  private static final long serialVersionUID = 8353158568132574241L;

  private Long id;

  private String orderDescription;

  private OrderType orderType;

  private OrderState statusCode;

  private Instant orderDate;

  private String deliveryAddress;

  private String trackingNotes;

  private Long purchaseOrderDsctCodeId;

  private String bankName;

  private String transactionReference;

  private PaymentState paymentStatusCode;

  private String paymentNotes;


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



  public Long getPurchaseOrderDsctCodeId() {
    return purchaseOrderDsctCodeId;
  }



  public void setPurchaseOrderDsctCodeId(Long purchaseOrderDsctCodeId) {
    this.purchaseOrderDsctCodeId = purchaseOrderDsctCodeId;
  }



  public String getBankName() {
    return bankName;
  }



  public void setBankName(String bankName) {
    this.bankName = bankName;
  }



  public String getTransactionReference() {
    return transactionReference;
  }



  public void setTransactionReference(String transactionReference) {
    this.transactionReference = transactionReference;
  }



  public PaymentState getPaymentStatusCode() {
    return paymentStatusCode;
  }



  public void setPaymentStatusCode(PaymentState paymentStatusCode) {
    this.paymentStatusCode = paymentStatusCode;
  }



  public String getPaymentNotes() {
    return paymentNotes;
  }



  public void setPaymentNotes(String paymentNotes) {
    this.paymentNotes = paymentNotes;
  }



  public OrderState getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(OrderState statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }



  @Override
  public String toString() {
    return "UpdatePurchaseOrderDTO [id=" + id + ", orderDescription=" + orderDescription
        + ", orderType=" + orderType + ", orderDate=" + orderDate + ", deliveryAddress="
        + deliveryAddress + ", trackingNotes=" + trackingNotes + ", purchaseOrderDsctCodeId="
        + purchaseOrderDsctCodeId + ", bankName=" + bankName + ", transactionReference="
        + transactionReference + ", paymentStatusCode=" + paymentStatusCode + ", paymentNotes="
        + paymentNotes + "]";
  }



}
