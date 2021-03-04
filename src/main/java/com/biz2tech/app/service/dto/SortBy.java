package com.biz2tech.app.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"direction", "sortOn"})
public class SortBy {

  @JsonProperty("direction")
  private String direction;
  @JsonProperty("sortOn")
  private String sortOn;

  @JsonProperty("direction")
  public String getDirection() {
    return direction;
  }

  @JsonProperty("direction")
  public void setDirection(String direction) {
    this.direction = direction;
  }

  @JsonProperty("sortOn")
  public String getSortOn() {
    return sortOn;
  }

  @JsonProperty("sortOn")
  public void setSortOn(String sortOn) {
    this.sortOn = sortOn;
  }

}
