package com.biz2tech.app.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The OrderType enumeration.
 */
public enum OrderType {
  SOLO("SOLO"), CART("CART"), DEAL("DEAL");
  private String shortName;

  OrderType(String shortName) {
    this.shortName = shortName;
  }

  private String getShortName() {
    return shortName;
  }

  @JsonCreator
  public static OrderType create(String value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    for (OrderType v : values()) {
      if (value.equalsIgnoreCase(v.getShortName())) {
        return v;
      }
    }
    throw new IllegalArgumentException();
  }
}
