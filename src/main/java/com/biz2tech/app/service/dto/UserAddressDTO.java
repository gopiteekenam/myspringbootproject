package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the UserAddress entity.
 */
public class UserAddressDTO implements Serializable {

  private static final long serialVersionUID = 4148237877602710566L;

  private Long id;

  private String addressName;

  private String userName;

  private String userAddressEmail;

  private String userAddressPhone;

  private String addressLine1;

  private String addressLine2;

  private String city;

  private String state;

  private String country;

  private String postalCode;

  private String createdBy;

  private Instant createdOn;

  private String lastUpdatedBy;

  private Instant lastUpdatedOn;

  private Long userId;
  private boolean isDefault;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddressName() {
    return addressName;
  }

  public void setAddressName(String addressName) {
    this.addressName = addressName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserAddressEmail() {
    return userAddressEmail;
  }

  public void setUserAddressEmail(String userAddressEmail) {
    this.userAddressEmail = userAddressEmail;
  }

  public String getUserAddressPhone() {
    return userAddressPhone;
  }

  public void setUserAddressPhone(String userAddressPhone) {
    this.userAddressPhone = userAddressPhone;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public Instant getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  public void setLastUpdatedOn(Instant lastUpdatedOn) {
    this.lastUpdatedOn = lastUpdatedOn;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean isDefault) {
    this.isDefault = isDefault;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserAddressDTO userAddressDTO = (UserAddressDTO) o;
    if (userAddressDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), userAddressDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "UserAddressDTO{" + "id=" + getId() + ", addressName='" + getAddressName() + "'"
        + ", userName='" + getUserName() + "'" + ", userAddressEmail='" + getUserAddressEmail()
        + "'" + ", userAddressPhone='" + getUserAddressPhone() + "'" + ", addressLine1='"
        + getAddressLine1() + "'" + ", addressLine2='" + getAddressLine2() + "'" + ", city='"
        + getCity() + "'" + ", state='" + getState() + "'" + ", country='" + getCountry() + "'"
        + ", postalCode='" + getPostalCode() + "'" + ", createdBy='" + getCreatedBy() + "'"
        + ", createdOn='" + getCreatedOn() + "'" + ", lastUpdatedBy='" + getLastUpdatedBy() + "'"
        + ", lastUpdatedOn='" + getLastUpdatedOn() + "'" + ", isDefault='" + isDefault() + "'"
        + ", userId=" + getUserId() + "}";
  }
}
