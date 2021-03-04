package com.biz2tech.app.service.dto;

import java.io.Serializable;

import com.biz2tech.app.domain.enumeration.SizeCapacityUnit;
import com.biz2tech.app.domain.enumeration.SizeMeasurementUnit;

public class ImportInventoryDetailDto implements Serializable {
  private static final long serialVersionUID = -2922043784425321467L;
  private String itemId;
  private String prdtTitle;
  private String prdtDesc;
  private String detailedSpec;
  private String sellPrice;
  private String imageURL;
  private String brandName;
  private String prdtTagId;
  private String basePrice;
  private String markedPercentage;
  private String currencyCode;
  private SizeCapacityUnit productSize;
  private SizeMeasurementUnit sizeMeasurementUnit;
  private String productMeasurement;
  private String color;
  private String isDropShipBoolean;
  private String clearence;
  private String discontinued;
  private String sale;
  private String premium;
  private String type;
  private String category;
  private String desc;
  private String totalCnt;
  private String avblCnt;
  private String sellCnt;

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getPrdtTitle() {
    return prdtTitle;
  }

  public void setPrdtTitle(String prdtTitle) {
    this.prdtTitle = prdtTitle;
  }

  public String getPrdtDesc() {
    return prdtDesc;
  }

  public void setPrdtDesc(String prdtDesc) {
    this.prdtDesc = prdtDesc;
  }

  public String getDetailedSpec() {
    return detailedSpec;
  }

  public void setDetailedSpec(String detailedSpec) {
    this.detailedSpec = detailedSpec;
  }

  public String getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(String sellPrice) {
    this.sellPrice = sellPrice;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getPrdtTagId() {
    return prdtTagId;
  }

  public void setPrdtTagId(String prdtTagId) {
    this.prdtTagId = prdtTagId;
  }

  public String getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(String basePrice) {
    this.basePrice = basePrice;
  }

  public String getMarkedPercentage() {
    return markedPercentage;
  }

  public void setMarkedPercentage(String markedPercentage) {
    this.markedPercentage = markedPercentage;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }



  public SizeCapacityUnit getProductSize() {
    return productSize;
  }

  public void setProductSize(SizeCapacityUnit productSize) {
    this.productSize = productSize;
  }

  public SizeMeasurementUnit getSizeMeasurementUnit() {
    return sizeMeasurementUnit;
  }

  public void setSizeMeasurementUnit(SizeMeasurementUnit sizeMeasurementUnit) {
    this.sizeMeasurementUnit = sizeMeasurementUnit;
  }

  public String getProductMeasurement() {
    return productMeasurement;
  }

  public void setProductMeasurement(String productMeasurement) {
    this.productMeasurement = productMeasurement;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }



  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getTotalCnt() {
    return totalCnt;
  }

  public void setTotalCnt(String totalCnt) {
    this.totalCnt = totalCnt;
  }

  public String getAvblCnt() {
    return avblCnt;
  }

  public void setAvblCnt(String avblCnt) {
    this.avblCnt = avblCnt;
  }

  public String getSellCnt() {
    return sellCnt;
  }

  public void setSellCnt(String sellCnt) {
    this.sellCnt = sellCnt;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getIsDropShipBoolean() {
    return isDropShipBoolean;
  }

  public void setIsDropShipBoolean(String isDropShipBoolean) {
    this.isDropShipBoolean = isDropShipBoolean;
  }

  public String getClearence() {
    return clearence;
  }

  public void setClearence(String clearence) {
    this.clearence = clearence;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  public String getSale() {
    return sale;
  }

  public void setSale(String sale) {
    this.sale = sale;
  }

  public String getPremium() {
    return premium;
  }

  public void setPremium(String premium) {
    this.premium = premium;
  }


}
