
package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.biz2tech.app.domain.enumeration.SizeCapacityUnit;
import com.biz2tech.app.domain.enumeration.SizeMeasurementUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({"id", "prdtTitle", "prdtDesc", "detailedSpec", "imageURL", "brandName",
    "basePrice", "markedPercentage", "sellPrice", "additionalDiscount","discountedPrice","currencyCode", "size", "sizeUnit",
    "sizeVarientCode", "colorRating", "isDropShip", "isClearence", "isDiscontinued", "isSale",
    "isPremium", "prdtType", "prdtCategory", "inventoryDtlsId", "sellerDetailsId", "dropShipDtlsId",
    "prdtTagNames", "prdtQuantity", "hasInventory", "userReview", "prdtGallery"})
public class ProductDtlsDTO implements Serializable {

  @JsonProperty("id")
  private Long id;
  @JsonProperty("prdtTitle")
  private String prdtTitle;
  @JsonProperty("prdtDesc")
  private String prdtDesc;
  @JsonProperty("detailedSpec")
  private String detailedSpec;
  @JsonProperty("imageURL")
  private String imageURL;
  @JsonProperty("brandName")
  private String brandName;
  @JsonProperty("basePrice")
  private Double basePrice;
  @JsonProperty("markedPercentage")
  private Double markedPercentage;
  @JsonProperty("sellPrice")
  private Double sellPrice;
  @JsonProperty("additionalDiscount")
  private Double additionalDiscount;
  @JsonProperty("discountedPrice")
  private Double discountedPrice;

  @JsonProperty("currencyCode")
  private String currencyCode;
  @JsonProperty("size")
  private SizeCapacityUnit size;
  @JsonProperty("sizeUnit")
  private SizeMeasurementUnit sizeUnit;
  @JsonProperty("sizeVarientCode")
  private String sizeVarientCode;
  @JsonProperty("colorRating")
  private Set<ColorRating> colorRatings = new TreeSet<ColorRating>();
  @JsonProperty("isDropShip")
  private Boolean isDropShip;
  @JsonProperty("isClearence")
  private Boolean isClearence;
  @JsonProperty("isDiscontinued")
  private Boolean isDiscontinued;
  @JsonProperty("isSale")
  private Boolean isSale;
  @JsonProperty("isPremium")
  private Boolean isPremium;
  @JsonProperty("prdtType")
  private String prdtType;
  @JsonProperty("prdtCategory")
  private String prdtCategory;
  @JsonProperty("inventoryDtlsId")
  private Integer inventoryDtlsId;
  @JsonProperty("inventoryIdentifier")
  private String inventoryIdentifier;
  @JsonProperty("sellerDetailsId")
  private Integer sellerDetailsId;
  @JsonProperty("dropShipDtlsId")
  private Integer dropShipDtlsId;
  @JsonProperty("prdtTagNames")
  private String prdtTagNames;
  @JsonProperty("prdtQuantity")
  private Integer prdtQuantity;
  @JsonProperty("hasInventory")
  private boolean hasInventory = true;
  @JsonProperty("userReview")
  private UserReview userReview;
  @JsonProperty("prdtGallery")
  private PrdtGallery prdtGallery;
  private final static long serialVersionUID = -7064556853967507398L;

  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Long id) {
    this.id = id;
  }

  public ProductDtlsDTO withId(Long id) {
    this.id = id;
    return this;
  }

  @JsonProperty("prdtTitle")
  public String getPrdtTitle() {
    return prdtTitle;
  }

  @JsonProperty("prdtTitle")
  public void setPrdtTitle(String prdtTitle) {
    this.prdtTitle = prdtTitle;
  }

  public ProductDtlsDTO withPrdtTitle(String prdtTitle) {
    this.prdtTitle = prdtTitle;
    return this;
  }

  @JsonProperty("prdtDesc")
  public String getPrdtDesc() {
    return prdtDesc;
  }

  @JsonProperty("prdtDesc")
  public void setPrdtDesc(String prdtDesc) {
    this.prdtDesc = prdtDesc;
  }

  public ProductDtlsDTO withPrdtDesc(String prdtDesc) {
    this.prdtDesc = prdtDesc;
    return this;
  }

  @JsonProperty("detailedSpec")
  public String getDetailedSpec() {
    return detailedSpec;
  }

  @JsonProperty("detailedSpec")
  public void setDetailedSpec(String detailedSpec) {
    this.detailedSpec = detailedSpec;
  }

  public ProductDtlsDTO withDetailedSpec(String detailedSpec) {
    this.detailedSpec = detailedSpec;
    return this;
  }

  @JsonProperty("imageURL")
  public String getImageURL() {
    return imageURL;
  }

  @JsonProperty("imageURL")
  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public ProductDtlsDTO withImageURL(String imageURL) {
    this.imageURL = imageURL;
    return this;
  }

  @JsonProperty("brandName")
  public String getBrandName() {
    return brandName;
  }

  @JsonProperty("brandName")
  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public ProductDtlsDTO withBrandName(String brandName) {
    this.brandName = brandName;
    return this;
  }

  @JsonProperty("basePrice")
  public Double getBasePrice() {
    return basePrice;
  }

  @JsonProperty("basePrice")
  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  public ProductDtlsDTO withBasePrice(Double basePrice) {
    this.basePrice = basePrice;
    return this;
  }

  @JsonProperty("markedPercentage")
  public Double getMarkedPercentage() {
    return markedPercentage;
  }

  @JsonProperty("markedPercentage")
  public void setMarkedPercentage(Double markedPercentage) {
    this.markedPercentage = markedPercentage;
  }

  public ProductDtlsDTO withMarkedPercentage(Double markedPercentage) {
    this.markedPercentage = markedPercentage;
    return this;
  }

  @JsonProperty("sellPrice")
  public Double getSellPrice() {
    return sellPrice;
  }

  @JsonProperty("sellPrice")
  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public ProductDtlsDTO withSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
    return this;
  }
  
  @JsonProperty("additionalDiscount")
  public Double getAdditionalDiscount() {
    return additionalDiscount;
  }

  @JsonProperty("additionalDiscount")
  public void setAdditionalDiscount(Double additionalDiscount) {
    this.additionalDiscount = additionalDiscount;
  }

  public ProductDtlsDTO withAdditionalDiscount(Double additionalDiscount) {
    this.additionalDiscount = additionalDiscount;
    return this;
  }

  
  @JsonProperty("discountedPrice")
  public Double getDiscountedPrice() {
    return discountedPrice;
  }

  @JsonProperty("discountedPrice")
  public void setDiscountedPrice(Double discountedPrice) {
    this.discountedPrice = discountedPrice;
  }

  public ProductDtlsDTO withDiscountedPrice(Double discountedPrice) {
    this.discountedPrice = discountedPrice;
    return this;
  }


  @JsonProperty("currencyCode")
  public String getCurrencyCode() {
    return currencyCode;
  }

  @JsonProperty("currencyCode")
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public ProductDtlsDTO withCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  @JsonProperty("size")
  public SizeCapacityUnit getSize() {
    return size;
  }

  @JsonProperty("size")
  public void setSize(SizeCapacityUnit size) {
    this.size = size;
  }

  public ProductDtlsDTO withSize(SizeCapacityUnit size) {
    this.size = size;
    return this;
  }

  @JsonProperty("sizeUnit")
  public SizeMeasurementUnit getSizeUnit() {
    return sizeUnit;
  }

  @JsonProperty("sizeUnit")
  public void setSizeUnit(SizeMeasurementUnit sizeUnit) {
    this.sizeUnit = sizeUnit;
  }

  public ProductDtlsDTO withSizeUnit(SizeMeasurementUnit sizeUnit) {
    this.sizeUnit = sizeUnit;
    return this;
  }

  @JsonProperty("sizeVarientCode")
  public String getSizeVarientCode() {
    return sizeVarientCode;
  }

  @JsonProperty("sizeVarientCode")
  public void setSizeVarientCode(String sizeVarientCode) {
    this.sizeVarientCode = sizeVarientCode;
  }

  public ProductDtlsDTO withSizeVarientCode(String sizeVarientCode) {
    this.sizeVarientCode = sizeVarientCode;
    return this;
  }

  public Set<ColorRating> getColorRatings() {
    return colorRatings;
  }

  public void setColorRatings(Set<ColorRating> colorRatings) {
    this.colorRatings = colorRatings;
  }

  public ProductDtlsDTO withColorRatings(Set<ColorRating> colorRatings) {
    this.colorRatings = colorRatings;
    return this;
  }

  public ProductDtlsDTO withColorRating(ColorRating colorRating) {
    this.colorRatings.add(colorRating);
    return this;
  }


  @JsonProperty("isDropShip")
  public Boolean getIsDropShip() {
    return isDropShip;
  }

  @JsonProperty("isDropShip")
  public void setIsDropShip(Boolean isDropShip) {
    this.isDropShip = isDropShip;
  }

  public ProductDtlsDTO withIsDropShip(Boolean isDropShip) {
    this.isDropShip = isDropShip;
    return this;
  }

  @JsonProperty("isClearence")
  public Boolean getIsClearence() {
    return isClearence;
  }

  @JsonProperty("isClearence")
  public void setIsClearence(Boolean isClearence) {
    this.isClearence = isClearence;
  }

  public ProductDtlsDTO withIsClearence(Boolean isClearence) {
    this.isClearence = isClearence;
    return this;
  }

  @JsonProperty("isDiscontinued")
  public Boolean getIsDiscontinued() {
    return isDiscontinued;
  }

  @JsonProperty("isDiscontinued")
  public void setIsDiscontinued(Boolean isDiscontinued) {
    this.isDiscontinued = isDiscontinued;
  }

  public ProductDtlsDTO withIsDiscontinued(Boolean isDiscontinued) {
    this.isDiscontinued = isDiscontinued;
    return this;
  }

  @JsonProperty("isSale")
  public Boolean getIsSale() {
    return isSale;
  }

  @JsonProperty("isSale")
  public void setIsSale(Boolean isSale) {
    this.isSale = isSale;
  }

  public ProductDtlsDTO withIsSale(Boolean isSale) {
    this.isSale = isSale;
    return this;
  }

  @JsonProperty("isPremium")
  public Boolean getIsPremium() {
    return isPremium;
  }

  @JsonProperty("isPremium")
  public void setIsPremium(Boolean isPremium) {
    this.isPremium = isPremium;
  }

  public ProductDtlsDTO withIsPremium(Boolean isPremium) {
    this.isPremium = isPremium;
    return this;
  }

  @JsonProperty("prdtType")
  public String getPrdtType() {
    return prdtType;
  }

  @JsonProperty("prdtType")
  public void setPrdtType(String prdtType) {
    this.prdtType = prdtType;
  }

  public ProductDtlsDTO withPrdtType(String prdtType) {
    this.prdtType = prdtType;
    return this;
  }

  @JsonProperty("prdtCategory")
  public String getPrdtCategory() {
    return prdtCategory;
  }

  @JsonProperty("prdtCategory")
  public void setPrdtCategory(String prdtCategory) {
    this.prdtCategory = prdtCategory;
  }

  public ProductDtlsDTO withPrdtCategory(String prdtCategory) {
    this.prdtCategory = prdtCategory;
    return this;
  }

  @JsonProperty("inventoryDtlsId")
  public Integer getInventoryDtlsId() {
    return inventoryDtlsId;
  }

  @JsonProperty("inventoryDtlsId")
  public void setInventoryDtlsId(Integer inventoryDtlsId) {
    this.inventoryDtlsId = inventoryDtlsId;
  }

  public ProductDtlsDTO withInventoryDtlsId(Integer inventoryDtlsId) {
    this.inventoryDtlsId = inventoryDtlsId;
    return this;
  }

  public ProductDtlsDTO withInventoryIdentifier(String inventoryIdentifier) {
    this.inventoryIdentifier = inventoryIdentifier;
    return this;
  }

  @JsonProperty("inventoryIdentifier")
  public String getInventoryIdentifier() {
    return inventoryIdentifier;
  }

  @JsonProperty("inventoryIdentifier")
  public void setInventoryIdentifier(String inventoryIdentifier) {
    this.inventoryIdentifier = inventoryIdentifier;
  }

  @JsonProperty("sellerDetailsId")
  public Integer getSellerDetailsId() {
    return sellerDetailsId;
  }

  @JsonProperty("sellerDetailsId")
  public void setSellerDetailsId(Integer sellerDetailsId) {
    this.sellerDetailsId = sellerDetailsId;
  }

  public ProductDtlsDTO withSellerDetailsId(Integer sellerDetailsId) {
    this.sellerDetailsId = sellerDetailsId;
    return this;
  }

  @JsonProperty("dropShipDtlsId")
  public Integer getDropShipDtlsId() {
    return dropShipDtlsId;
  }

  @JsonProperty("dropShipDtlsId")
  public void setDropShipDtlsId(Integer dropShipDtlsId) {
    this.dropShipDtlsId = dropShipDtlsId;
  }

  public ProductDtlsDTO withDropShipDtlsId(Integer dropShipDtlsId) {
    this.dropShipDtlsId = dropShipDtlsId;
    return this;
  }

  @JsonProperty("prdtTagNames")
  public String getPrdtTagNames() {
    return prdtTagNames;
  }

  @JsonProperty("prdtTagNames")
  public void setPrdtTagNames(String prdtTagNames) {
    this.prdtTagNames = prdtTagNames;
  }

  public ProductDtlsDTO withPrdtTagNames(String prdtTagNames) {
    this.prdtTagNames = prdtTagNames;
    return this;
  }

  @JsonProperty("hasInventory")
  public boolean isHasInventory() {
    return hasInventory;
  }

  @JsonProperty("hasInventory")
  public void setHasInventory(boolean hasInventory) {
    this.hasInventory = hasInventory;
  }

  public Integer getPrdtQuantity() {
    return prdtQuantity;
  }

  @JsonProperty("prdtQuantity")
  public void setPrdtQuantity(Integer prdtQuantity) {
    this.prdtQuantity = prdtQuantity;
  }

  public ProductDtlsDTO withPrdtQuantity(Integer prdtQuantity) {
    this.prdtQuantity = prdtQuantity;
    return this;
  }

  @JsonProperty("userReview")
  public UserReview getUserReview() {
    return userReview;
  }

  @JsonProperty("userReview")
  public void setUserReview(UserReview userReview) {
    this.userReview = userReview;
  }

  public ProductDtlsDTO withUserReview(UserReview userReview) {
    this.userReview = userReview;
    return this;
  }

  @JsonProperty("prdtGallery")
  public PrdtGallery getPrdtGallery() {
    return prdtGallery;
  }

  @JsonProperty("prdtGallery")
  public void setPrdtGallery(PrdtGallery prdtGallery) {
    this.prdtGallery = prdtGallery;
  }

  public ProductDtlsDTO withPrdtGallery(PrdtGallery prdtGallery) {
    this.prdtGallery = prdtGallery;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("prdtTitle", prdtTitle)
        .append("prdtDesc", prdtDesc).append("detailedSpec", detailedSpec)
        .append("imageURL", imageURL).append("brandName", brandName).append("basePrice", basePrice)
        .append("markedPercentage", markedPercentage).append("sellPrice", sellPrice)
        .append("additionalDiscount", additionalDiscount).append("discountedPrice", discountedPrice)
        .append("currencyCode", currencyCode).append("size", size).append("sizeUnit", sizeUnit)
        .append("sizeVarientCode", sizeVarientCode).append("colorRating", colorRatings)
        .append("isDropShip", isDropShip).append("isClearence", isClearence)
        .append("isDiscontinued", isDiscontinued).append("isSale", isSale)
        .append("isPremium", isPremium).append("prdtType", prdtType)
        .append("prdtCategory", prdtCategory).append("inventoryDtlsId", inventoryDtlsId)
        .append("sellerDetailsId", sellerDetailsId).append("dropShipDtlsId", dropShipDtlsId)
        .append("prdtTagNames", prdtTagNames).append("prdtQuantity", prdtQuantity)
        .append("userReview", userReview).append("prdtGallery", prdtGallery).toString();
  }

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sellPrice).append(additionalDiscount).append(discountedPrice)
				.append(prdtGallery).append(isSale).append(prdtCategory).append(sellerDetailsId).append(prdtQuantity)
				.append(isDiscontinued).append(brandName).append(id).append(inventoryDtlsId).append(isPremium)
				.append(sizeUnit).append(markedPercentage).append(prdtTagNames).append(userReview)
				.append(dropShipDtlsId).append(prdtType).append(isDropShip).append(currencyCode).append(prdtDesc)
				.append(prdtTitle).append(colorRatings).append(detailedSpec).append(isClearence).append(size)
				.append(sizeVarientCode).append(basePrice).append(imageURL).toHashCode();
	}

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof ProductDtlsDTO) == false) {
      return false;
    }
    ProductDtlsDTO rhs = ((ProductDtlsDTO) other);
    return new EqualsBuilder().append(sellPrice, rhs.sellPrice).append(prdtGallery, rhs.prdtGallery)
        .append(isSale, rhs.isSale).append(prdtCategory, rhs.prdtCategory)
        .append(sellerDetailsId, rhs.sellerDetailsId).append(prdtQuantity, rhs.prdtQuantity)
        .append(isDiscontinued, rhs.isDiscontinued).append(brandName, rhs.brandName)
        .append(id, rhs.id).append(inventoryDtlsId, rhs.inventoryDtlsId)
        .append(isPremium, rhs.isPremium).append(sizeUnit, rhs.sizeUnit)
        .append(markedPercentage, rhs.markedPercentage).append(prdtTagNames, rhs.prdtTagNames)
        .append(userReview, rhs.userReview).append(dropShipDtlsId, rhs.dropShipDtlsId)
        .append(prdtType, rhs.prdtType).append(isDropShip, rhs.isDropShip)
        .append(currencyCode, rhs.currencyCode).append(prdtDesc, rhs.prdtDesc)
        .append(prdtTitle, rhs.prdtTitle).append(colorRatings, rhs.colorRatings)
        .append(detailedSpec, rhs.detailedSpec).append(isClearence, rhs.isClearence)
        .append(size, rhs.size).append(sizeVarientCode, rhs.sizeVarientCode)
        .append(basePrice, rhs.basePrice).append(imageURL, rhs.imageURL).isEquals();
  }


}
