package com.biz2tech.app.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"low", "high"})
public class PriceFilter {

  @JsonProperty("low")
  private Long low;
  @JsonProperty("high")
  private Long high;

  @JsonProperty("low")
  public Long getLow() {
    return low;
  }

  @JsonProperty("low")
  public void setLow(Long low) {
    this.low = low;
  }

  public PriceFilter withLow(Long low) {
    this.low = low;
    return this;
  }

  @JsonProperty("high")
  public Long getHigh() {
    return high;
  }

  @JsonProperty("high")
  public void setHigh(Long high) {
    this.high = high;
  }

  public PriceFilter withHigh(Long high) {
    this.high = high;
    return this;
  }

}
