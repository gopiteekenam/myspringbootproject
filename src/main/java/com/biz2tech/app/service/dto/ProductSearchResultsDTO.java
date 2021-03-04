package com.biz2tech.app.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"pageNumber", "totalPages", "productDtls"})
public class ProductSearchResultsDTO {

  @JsonProperty("pageNumber")
  private Long pageNumber;
  @JsonProperty("totalPages")
  private Long totalPages;
  @JsonProperty("productDtls")
  private List<ProductDtlsDTO> productDtls = null;

  public ProductSearchResultsDTO(Long pageNumber, Long totalPages,
      List<ProductDtlsDTO> productDtls) {
    this.pageNumber = pageNumber;
    this.totalPages = totalPages;
    this.productDtls = productDtls;
  }

  @JsonProperty("pageNumber")
  public Long getPageNumber() {
    return pageNumber;
  }

  @JsonProperty("pageNumber")
  public void setPageNumber(Long pageNumber) {
    this.pageNumber = pageNumber;
  }

  @JsonProperty("totalPages")
  public Long getTotalPages() {
    return totalPages;
  }

  @JsonProperty("totalPages")
  public void setTotalPages(Long totalPages) {
    this.totalPages = totalPages;
  }

  @JsonProperty("productDtls")
  public List<ProductDtlsDTO> getProductDtls() {
    return productDtls;
  }

  @JsonProperty("productDtls")
  public void setProductDtls(List<ProductDtlsDTO> productDtls) {
    this.productDtls = productDtls;
  }

}
