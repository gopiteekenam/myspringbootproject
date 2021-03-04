
package com.biz2tech.app.service.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"rating", "reviewCount"})
public class UserReview implements Serializable {

  @JsonProperty("rating")
  private String rating;
  @JsonProperty("reviewCount")
  private String reviewCount;
  private final static long serialVersionUID = 3919271628392323851L;

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public UserReview withRating(String rating) {
    this.rating = rating;
    return this;
  }

  @JsonProperty("reviewCount")
  public String getReviewCount() {
    return reviewCount;
  }

  @JsonProperty("reviewCount")
  public void setReviewCount(String reviewCount) {
    this.reviewCount = reviewCount;
  }

  public UserReview withReviewCount(String reviewCount) {
    this.reviewCount = reviewCount;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("rating", rating).append("reviewCount", reviewCount)
        .toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(reviewCount).append(rating).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof UserReview) == false) {
      return false;
    }
    UserReview rhs = ((UserReview) other);
    return new EqualsBuilder().append(reviewCount, rhs.reviewCount).append(rating, rhs.rating)
        .isEquals();
  }

}
