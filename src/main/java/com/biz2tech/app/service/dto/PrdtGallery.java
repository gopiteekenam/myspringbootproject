
package com.biz2tech.app.service.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"prdtSlides", "prdtThumbs"})
public class PrdtGallery implements Serializable {

  @JsonProperty("prdtSlides")
  private String prdtSlides;
  @JsonProperty("prdtThumbs")
  private String prdtThumbs;
  private final static long serialVersionUID = -7126274234924357981L;

  @JsonProperty("prdtSlides")
  public String getPrdtSlides() {
    return prdtSlides;
  }

  @JsonProperty("prdtSlides")
  public void setPrdtSlides(String prdtSlides) {
    this.prdtSlides = prdtSlides;
  }

  public PrdtGallery withPrdtSlides(String prdtSlides) {
    this.prdtSlides = prdtSlides;
    return this;
  }

  @JsonProperty("prdtThumbs")
  public String getPrdtThumbs() {
    return prdtThumbs;
  }

  @JsonProperty("prdtThumbs")
  public void setPrdtThumbs(String prdtThumbs) {
    this.prdtThumbs = prdtThumbs;
  }

  public PrdtGallery withPrdtThumbs(String prdtThumbs) {
    this.prdtThumbs = prdtThumbs;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("prdtSlides", prdtSlides)
        .append("prdtThumbs", prdtThumbs).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(prdtThumbs).append(prdtSlides).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof PrdtGallery) == false) {
      return false;
    }
    PrdtGallery rhs = ((PrdtGallery) other);
    return new EqualsBuilder().append(prdtThumbs, rhs.prdtThumbs).append(prdtSlides, rhs.prdtSlides)
        .isEquals();
  }

}
