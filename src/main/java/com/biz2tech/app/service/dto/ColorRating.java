package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"colorPosition", "colorMood"})
public class ColorRating implements Serializable, Comparable<ColorRating> {
  @JsonProperty("colorPosition")
  private Integer colorPosition;
  @JsonProperty("userRating")
  private Integer userRating;
  @JsonProperty("colorMood")
  private Set<ColorMood> colorMood = new TreeSet<ColorMood>();
  private final static long serialVersionUID = -5865359115886889335L;

  @JsonProperty("colorPosition")
  public Integer getColorPosition() {
    return colorPosition;
  }

  @JsonProperty("colorPosition")
  public void setColorPosition(Integer colorPosition) {
    this.colorPosition = colorPosition;
  }

  public ColorRating withColorPosition(Integer colorPosition) {
    this.colorPosition = colorPosition;
    return this;
  }

  public Integer getUserRating() {
    return userRating;
  }

  @JsonProperty("userRating")
  public void setUserRating(Integer userRating) {
    this.userRating = userRating;
  }

  public ColorRating withUserRating(Integer userRating) {
    this.userRating = userRating;
    return this;
  }


  @JsonProperty("colorMood")
  public Set<ColorMood> getColorMood() {
    return colorMood;
  }

  @JsonProperty("colorMood")
  public void setColorMood(Set<ColorMood> colorMood) {
    this.colorMood = colorMood;
  }

  public ColorRating withColorMood(Set<ColorMood> colorMood) {
    this.colorMood = colorMood;
    return this;
  }

  public ColorRating withColorMood(ColorMood colorMood) {
    this.colorMood.add(colorMood);
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("colorPosition", colorPosition)
        .append("colorMood", colorMood).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(colorMood).append(colorPosition).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof ColorRating) == false) {
      return false;
    }
    ColorRating rhs = ((ColorRating) other);
    return new EqualsBuilder().append(colorMood, rhs.colorMood)
        .append(colorPosition, rhs.colorPosition).isEquals();
  }

  @Override
  public int compareTo(ColorRating o) {
    return this.getColorPosition().compareTo(o.getColorPosition());
  }
}
