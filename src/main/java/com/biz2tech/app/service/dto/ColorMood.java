package com.biz2tech.app.service.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"colorMoodLevel", "userRating", "userReviewCount"})
public class ColorMood implements Serializable, Comparable<ColorMood> {

  @JsonProperty("colorMoodLevel")
  private Integer colorMoodLevel;
  @JsonProperty("userRating")
  private Integer userRating;
  @JsonProperty("userReviewCount")
  private Integer userReviewCount;
  private final static long serialVersionUID = -8783895942279249170L;

  @JsonProperty("colorMoodLevel")
  public Integer getColorMoodLevel() {
    return colorMoodLevel;
  }

  @JsonProperty("colorMoodLevel")
  public void setColorMoodLevel(Integer colorMoodLevel) {
    this.colorMoodLevel = colorMoodLevel;
  }

  public ColorMood withColorMoodLevel(Integer colorMoodLevel) {
    this.colorMoodLevel = colorMoodLevel;
    return this;
  }

  @JsonProperty("userRating")
  public Integer getUserRating() {
    return userRating;
  }

  @JsonProperty("userRating")
  public void setUserRating(Integer userRating) {
    this.userRating = userRating;
  }

  public ColorMood withUserRating(Integer userRating) {
    this.userRating = userRating;
    return this;
  }

  @JsonProperty("userReviewCount")
  public Integer getUserReviewCount() {
    return userReviewCount;
  }

  @JsonProperty("userReviewCount")
  public void setUserReviewCount(Integer userReviewCount) {
    this.userReviewCount = userReviewCount;
  }

  public ColorMood withUserReviewCount(Integer userReviewCount) {
    this.userReviewCount = userReviewCount;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("colorMoodLevel", colorMoodLevel)
        .append("userRating", userRating).append("userReviewCount", userReviewCount).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(colorMoodLevel).append(userRating).append(userReviewCount)
        .toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof ColorMood) == false) {
      return false;
    }
    ColorMood rhs = ((ColorMood) other);
    return new EqualsBuilder().append(colorMoodLevel, rhs.colorMoodLevel)
        .append(userRating, rhs.userRating).append(userReviewCount, rhs.userReviewCount).isEquals();
  }

  @Override
  public int compareTo(ColorMood o) {
    return o.getUserReviewCount().compareTo(getUserReviewCount());
  }

}
