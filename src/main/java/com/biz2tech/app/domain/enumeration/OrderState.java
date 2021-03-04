package com.biz2tech.app.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The states of order and order items
 */
public enum OrderState {

  //J-
  CREATED("CREATED"), //order placed
  PAYMENT_SUCCESS("PAYMENT_SUCCESS"), //payment received 
  PAYMENT_DECLINED("PAYMENT_DECLINED"), //payment failed
  PLACED("PLACED"), //confirmed by company staff
  CANCELLED("CANCELLED"), //cancelled by company staff
  IN_TRANSIT("IN_TRANSIT"), //tracking information received and package on the way
  DELIVERED("DELIVERED"),  //delivered to customer
  MISDELIVERED("MISDELIVERED"), // delivery failed
  RETURN_INITIATED("RETURN_INITIATED"),  // customer initiated return
  RETURNED("RETURNED"),  // return completed
  REFUNDED("REFUNDED"),  // refund completed
  INDISPUTE("INDISPUTE"); // dispute with payment
  //J+
  private String shortName;

  OrderState(String shortName) {
    this.shortName = shortName;
  }

  private String getShortName() {
    return shortName;
  }

  @JsonCreator
  public static OrderState create(String value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    for (OrderState v : values()) {
      if (value.equalsIgnoreCase(v.getShortName())) {
        return v;
      }
    }
    throw new IllegalArgumentException();
  }
}
