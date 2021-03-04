package com.biz2tech.app.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"prdtTypes", "prdtCatgs", "prdtBrands", "prdtTags"})
public class ProductSearchPossibilitiesDTO {

  @JsonProperty("prdtTypes")
  private String prdtTypes;
  @JsonProperty("prdtCatgs")
  private String prdtCatgs;
  @JsonProperty("prdtBrands")
  private String prdtBrands;
  @JsonProperty("prdtTags")
  private String prdtTags;

  @JsonProperty("prdtTypes")
  public String getPrdtTypes() {
    return prdtTypes;
  }

  @JsonProperty("prdtTypes")
  public void setPrdtTypes(String prdtTypes) {
    this.prdtTypes = prdtTypes;
  }

  public ProductSearchPossibilitiesDTO withPrdtTypes(String prdtTypes) {
    this.prdtTypes = prdtTypes;
    return this;
  }

  @JsonProperty("prdtCatgs")
  public String getPrdtCatgs() {
    return prdtCatgs;
  }

  @JsonProperty("prdtCatgs")
  public void setPrdtCatgs(String prdtCatgs) {
    this.prdtCatgs = prdtCatgs;
  }

  public ProductSearchPossibilitiesDTO withPrdtCatgs(String prdtCatgs) {
    this.prdtCatgs = prdtCatgs;
    return this;
  }

  @JsonProperty("prdtBrands")
  public String getPrdtBrands() {
    return prdtBrands;
  }

  @JsonProperty("prdtBrands")
  public void setPrdtBrands(String prdtBrands) {
    this.prdtBrands = prdtBrands;
  }

  public ProductSearchPossibilitiesDTO withPrdtBrands(String prdtBrands) {
    this.prdtBrands = prdtBrands;
    return this;
  }

  @JsonProperty("prdtTags")
  public String getPrdtTags() {
    return prdtTags;
  }

  @JsonProperty("prdtTags")
  public void setPrdtTags(String prdtTags) {
    this.prdtTags = prdtTags;
  }

  public ProductSearchPossibilitiesDTO withPrdtTags(String prdtTags) {
    this.prdtTags = prdtTags;
    return this;
  }

}
