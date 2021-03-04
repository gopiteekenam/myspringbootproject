package com.biz2tech.app.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"prdtType", "prdtCatg", "prdtBrand", "prdtTag", "prdtTitle", "pageNumber",
    "pageSize", "sortBy", "priceFilter"})
public class ProductSearchDTO {

  @JsonProperty("prdtType")
  private String prdtType;
  @JsonProperty("prdtCatg")
  private String prdtCatg;
  @JsonProperty("prdtBrand")
  private String prdtBrand;
  @JsonProperty("prdtTag")
  private String prdtTag;
  @JsonProperty("prdtTitle")
  private String prdtTitle;
  @JsonProperty("pageNumber")
  private Integer pageNumber;
  @JsonProperty("pageSize")
  private Integer pageSize;
  @JsonProperty("sortBy")
  private SortBy sortBy;
  @JsonProperty("priceFilter")
  private PriceFilter priceFilter;

  @JsonProperty("prdtType")
  public String getPrdtType() {
    return prdtType;
  }

  @JsonProperty("prdtType")
  public void setPrdtType(String prdtType) {
    this.prdtType = prdtType;
  }

  @JsonProperty("prdtCatg")
  public String getPrdtCatg() {
    return prdtCatg;
  }

  @JsonProperty("prdtCatg")
  public void setPrdtCatg(String prdtCatg) {
    this.prdtCatg = prdtCatg;
  }

  @JsonProperty("prdtBrand")
  public String getPrdtBrand() {
    return prdtBrand;
  }

  @JsonProperty("prdtBrand")
  public void setPrdtBrand(String prdtBrand) {
    this.prdtBrand = prdtBrand;
  }

  @JsonProperty("prdtTag")
  public String getPrdtTag() {
    return prdtTag;
  }

  @JsonProperty("prdtTag")
  public void setPrdtTag(String prdtTag) {
    this.prdtTag = prdtTag;
  }

  @JsonProperty("prdtTitle")
  public String getPrdtTitle() {
    return prdtTitle;
  }

  @JsonProperty("prdtTitle")
  public void setPrdtTitle(String prdtTitle) {
    this.prdtTitle = prdtTitle;
  }

  @JsonProperty("pageNumber")
  public Integer getPageNumber() {
    return (pageNumber != null) ? pageNumber : 0;
  }

  @JsonProperty("pageNumber")
  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  @JsonProperty("pageSize")
  public Integer getPageSize() {
    return pageSize;
  }

  @JsonProperty("pageSize")
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  @JsonProperty("sortBy")
  public SortBy getSortBy() {
    return sortBy;
  }

  @JsonProperty("sortBy")
  public void setSortBy(SortBy sortBy) {
    this.sortBy = sortBy;
  }

  @JsonProperty("priceFilter")
  public PriceFilter getPriceFilter() {
    return priceFilter;
  }

  @JsonProperty("priceFilter")
  public void setPriceFilter(PriceFilter priceFilter) {
    this.priceFilter = priceFilter;
  }

}
