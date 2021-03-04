package com.biz2tech.app.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A UserAddress.
 */
@Entity
@Table(name = "user_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserAddress extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "address_name")
  private String addressName;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "user_address_email")
  private String userAddressEmail;

  @Column(name = "user_address_phone")
  private String userAddressPhone;

  @Column(name = "address_line_1")
  private String addressLine1;

  @Column(name = "address_line_2")
  private String addressLine2;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "country")
  private String country;

  @Column(name = "postal_code")
  private String postalCode;


  @Column(name = "default_flag")
  private boolean isDefault;

  @ManyToOne
  @JsonIgnoreProperties("userAddresses")
  private User user;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddressName() {
    return addressName;
  }

  public UserAddress addressName(String addressName) {
    this.addressName = addressName;
    return this;
  }

  public void setAddressName(String addressName) {
    this.addressName = addressName;
  }

  public String getUserName() {
    return userName;
  }

  public UserAddress userName(String userName) {
    this.userName = userName;
    return this;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserAddressEmail() {
    return userAddressEmail;
  }

  public UserAddress userAddressEmail(String userAddressEmail) {
    this.userAddressEmail = userAddressEmail;
    return this;
  }

  public void setUserAddressEmail(String userAddressEmail) {
    this.userAddressEmail = userAddressEmail;
  }

  public String getUserAddressPhone() {
    return userAddressPhone;
  }

  public UserAddress userAddressPhone(String userAddressPhone) {
    this.userAddressPhone = userAddressPhone;
    return this;
  }

  public void setUserAddressPhone(String userAddressPhone) {
    this.userAddressPhone = userAddressPhone;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public UserAddress addressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
    return this;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public UserAddress addressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
    return this;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getCity() {
    return city;
  }

  public UserAddress city(String city) {
    this.city = city;
    return this;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public UserAddress state(String state) {
    this.state = state;
    return this;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public UserAddress country(String country) {
    this.country = country;
    return this;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public UserAddress postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }



  public User getUser() {
    return user;
  }

  public UserAddress user(User user) {
    this.user = user;
    return this;
  }

  public void setUser(User user) {
    this.user = user;
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
    UserAddress userAddress = (UserAddress) o;
    if (userAddress.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), userAddress.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "UserAddress [id=" + id + ", addressName=" + addressName + ", userName=" + userName
        + ", userAddressEmail=" + userAddressEmail + ", userAddressPhone=" + userAddressPhone
        + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
        + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode
        + ", isDefault=" + isDefault + ", user=" + user + "]";
  }


}
