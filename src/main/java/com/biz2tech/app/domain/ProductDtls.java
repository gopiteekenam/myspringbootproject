package com.biz2tech.app.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.biz2tech.app.domain.enumeration.SizeCapacityUnit;
import com.biz2tech.app.domain.enumeration.SizeMeasurementUnit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A ProductDtls.
 */
@Entity
@Table(name = "product_dtls")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductDtls extends AbstractAuditingEntity {

  private static final long serialVersionUID = -6105196878766188550L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "prdt_title")
  private String prdtTitle;

  @Column(name = "prdt_desc")
  private String prdtDesc;

  @Column(name = "detailed_spec")
  private String detailedSpec;
  
  @Column(name = "reason_choose")
  private String chooseReason;
  

  @Column(name = "image_url")
  private String imageURL;

  @Column(name = "brand_name")
  private String brandName;

  @Column(name = "base_price")
  private Double basePrice;

  @Column(name = "marked_percentage")
  private Double markedPercentage;

  @Column(name = "sell_price")
  private Double sellPrice;

  @Column(name = "additional_discount")
  private Double additionalDiscount;
   
  @Column(name = "discounted_price")
  private Double discountedPrice;
  
  @Column(name = "currency_code")
  private String currencyCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "jhi_size")
  private SizeCapacityUnit size;

  @Enumerated(EnumType.STRING)
  @Column(name = "size_unit")
  private SizeMeasurementUnit sizeUnit;

  @Column(name = "size_varient_code")
  private String sizeVarientCode;

  @Column(name = "color")
  private String colorRating;

  @Column(name = "is_drop_ship")
  private Boolean isDropShip;

  @Column(name = "is_clearence")
  private Boolean isClearence;

  @Column(name = "is_discontinued")
  private Boolean isDiscontinued;

  @Column(name = "is_sale")
  private Boolean isSale;

  @Column(name = "is_premium")
  private Boolean isPremium;

  @Column(name = "prdt_type")
  private String prdtType;

  @Column(name = "prdt_category")
  private String prdtCategory;


  @OneToOne(fetch = FetchType.LAZY)
  @Cascade(CascadeType.PERSIST)
  @JoinColumn(unique = true)
  private InventoryDtls inventoryDtls;


  @ManyToOne
  @JsonIgnoreProperties("productDtls")
  private SellerDetails sellerDetails;

  @ManyToOne
  @JsonIgnoreProperties("productDtls")
  private DropShipDtls dropShipDtls;

  @ManyToMany(fetch = FetchType.LAZY)
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @Cascade(CascadeType.MERGE)
  @JoinTable(name = "product_dtls_product_tag",
      joinColumns = @JoinColumn(name = "product_dtls_id", table = "product_dtls",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "product_tags_id", table = "product_tag",
          referencedColumnName = "id"))
  private Set<ProductTag> productTags = new HashSet<ProductTag>();

  @ManyToMany(fetch = FetchType.LAZY)
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @Cascade(CascadeType.MERGE)
  @JoinTable(name = "product_dtls_color_category",
      joinColumns = @JoinColumn(name = "product_dtls_id", table = "product_dtls",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "color_category_id", table = "color_Category",
          referencedColumnName = "id"))
  private Set<ColorCategory> colorCategory = new HashSet<ColorCategory>();


  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPrdtTitle() {
    return prdtTitle;
  }

  public ProductDtls prdtTitle(String prdtTitle) {
    this.prdtTitle = prdtTitle;
    return this;
  }

  public void setPrdtTitle(String prdtTitle) {
    this.prdtTitle = prdtTitle;
  }

  public String getPrdtDesc() {
    return prdtDesc;
  }

  public ProductDtls prdtDesc(String prdtDesc) {
    this.prdtDesc = prdtDesc;
    return this;
  }

  public void setPrdtDesc(String prdtDesc) {
    this.prdtDesc = prdtDesc;
  }

  public String getDetailedSpec() {
    return detailedSpec;
  }

  public ProductDtls detailedSpec(String detailedSpec) {
    this.detailedSpec = detailedSpec;
    return this;
  }

  public void setDetailedSpec(String detailedSpec) {
    this.detailedSpec = detailedSpec;
  }
  
  public String getChooseReason() {
	return chooseReason;
  }

  public void setChooseReason(String chooseReason) {
	this.chooseReason = chooseReason;
  }

  public String getImageURL() {
    return imageURL;
  }

  public ProductDtls imageURL(String imageURL) {
    this.imageURL = imageURL;
    return this;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getBrandName() {
    return brandName;
  }

  public ProductDtls brandName(String brandName) {
    this.brandName = brandName;
    return this;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }


  public Double getBasePrice() {
    return basePrice;
  }

  public ProductDtls basePrice(Double basePrice) {
    this.basePrice = basePrice;
    return this;
  }

  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  public Double getMarkedPercentage() {
    return markedPercentage;
  }

  public ProductDtls markedPercentage(Double markedPercentage) {
    this.markedPercentage = markedPercentage;
    return this;
  }

  public void setMarkedPercentage(Double markedPercentage) {
    this.markedPercentage = markedPercentage;
  }

  public Double getSellPrice() {
    return sellPrice;
  }

  public ProductDtls sellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
    return this;
  }

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Double getAdditionalDiscount() {
		return additionalDiscount;
	}

	public void setAdditionalDiscount(Double additionalDiscount) {
		this.additionalDiscount = additionalDiscount;
	}

	public Double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

  public String getCurrencyCode() {
    return currencyCode;
  }

  public ProductDtls currencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public SizeCapacityUnit getSize() {
    return size;
  }

  public ProductDtls size(SizeCapacityUnit size) {
    this.size = size;
    return this;
  }

  public void setSize(SizeCapacityUnit size) {
    this.size = size;
  }

  public SizeMeasurementUnit getSizeUnit() {
    return sizeUnit;
  }

  public ProductDtls sizeUnit(SizeMeasurementUnit sizeUnit) {
    this.sizeUnit = sizeUnit;
    return this;
  }

  public void setSizeUnit(SizeMeasurementUnit sizeUnit) {
    this.sizeUnit = sizeUnit;
  }

  public String getSizeVarientCode() {
    return sizeVarientCode;
  }

  public ProductDtls sizeVarientCode(String sizeVarientCode) {
    this.sizeVarientCode = sizeVarientCode;
    return this;
  }

  public void setSizeVarientCode(String sizeVarientCode) {
    this.sizeVarientCode = sizeVarientCode;
  }

  public String getColorRating() {
    return colorRating;
  }

  public ProductDtls colorRating(String colorRating) {
    this.colorRating = colorRating;
    return this;
  }

  public void setColorRating(String colorRating) {
    this.colorRating = colorRating;
  }

  public Boolean isIsDropShip() {
    return isDropShip;
  }

  public ProductDtls isDropShip(Boolean isDropShip) {
    this.isDropShip = isDropShip;
    return this;
  }

  public void setIsDropShip(Boolean isDropShip) {
    this.isDropShip = isDropShip;
  }

  public Boolean isIsClearence() {
    return isClearence;
  }

  public ProductDtls isClearence(Boolean isClearence) {
    this.isClearence = isClearence;
    return this;
  }

  public void setIsClearence(Boolean isClearence) {
    this.isClearence = isClearence;
  }

  public Boolean isIsDiscontinued() {
    return isDiscontinued;
  }

  public ProductDtls isDiscontinued(Boolean isDiscontinued) {
    this.isDiscontinued = isDiscontinued;
    return this;
  }

  public void setIsDiscontinued(Boolean isDiscontinued) {
    this.isDiscontinued = isDiscontinued;
  }

  public Boolean isIsSale() {
    return isSale;
  }

  public ProductDtls isSale(Boolean isSale) {
    this.isSale = isSale;
    return this;
  }

  public void setIsSale(Boolean isSale) {
    this.isSale = isSale;
  }

  public Boolean isIsPremium() {
    return isPremium;
  }

  public ProductDtls isPremium(Boolean isPremium) {
    this.isPremium = isPremium;
    return this;
  }

  public void setIsPremium(Boolean isPremium) {
    this.isPremium = isPremium;
  }

  public String getPrdtType() {
    return prdtType;
  }

  public ProductDtls prdtType(String prdtType) {
    this.prdtType = prdtType;
    return this;
  }

  public void setPrdtType(String prdtType) {
    this.prdtType = prdtType;
  }

  public String getPrdtCategory() {
    return prdtCategory;
  }

  public ProductDtls prdtCategory(String prdtCategory) {
    this.prdtCategory = prdtCategory;
    return this;
  }

  public void setPrdtCategory(String prdtCategory) {
    this.prdtCategory = prdtCategory;
  }

  public InventoryDtls getInventoryDtls() {
    return inventoryDtls;
  }

  public ProductDtls inventoryDtls(InventoryDtls inventoryDtls) {
    this.inventoryDtls = inventoryDtls;
    return this;
  }

  public void setInventoryDtls(InventoryDtls inventoryDtls) {
    this.inventoryDtls = inventoryDtls;
  }



  public SellerDetails getSellerDetails() {
    return sellerDetails;
  }

  public ProductDtls sellerDetails(SellerDetails sellerDetails) {
    this.sellerDetails = sellerDetails;
    return this;
  }

  public void setSellerDetails(SellerDetails sellerDetails) {
    this.sellerDetails = sellerDetails;
  }

  public DropShipDtls getDropShipDtls() {
    return dropShipDtls;
  }

  public ProductDtls dropShipDtls(DropShipDtls dropShipDtls) {
    this.dropShipDtls = dropShipDtls;
    return this;
  }

  public void setDropShipDtls(DropShipDtls dropShipDtls) {
    this.dropShipDtls = dropShipDtls;
  }

  public Set<ProductTag> getProductTags() {
    return productTags;
  }

  public ProductDtls productTags(Set<ProductTag> productTags) {
    this.productTags = productTags;
    return this;
  }

  public ProductDtls addProductTag(ProductTag productTag) {
    this.productTags.add(productTag);
    return this;
  }


  public void setProductTags(Set<ProductTag> productTags) {
    this.productTags = productTags;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not
  // remove

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDtls productDtls = (ProductDtls) o;
    if (productDtls.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), productDtls.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

	@Override
	public String toString() {
		return "ProductDtls [id=" + id + ", prdtTitle=" + prdtTitle + ", prdtDesc=" + prdtDesc + ", detailedSpec="
				+ detailedSpec + ",chooseReason=" +chooseReason+", imageURL=" + imageURL + ", brandName=" + brandName + ", basePrice=" + basePrice
				+ ", markedPercentage=" + markedPercentage + ", sellPrice=" + sellPrice + ", additionalDiscount="
				+ additionalDiscount + ", discountedPrice=" + discountedPrice + ", currencyCode=" + currencyCode
				+ ", size=" + size + ", sizeUnit=" + sizeUnit + ", sizeVarientCode=" + sizeVarientCode
				+ ", colorRating=" + colorRating + ", isDropShip=" + isDropShip + ", isClearence=" + isClearence
				+ ", isDiscontinued=" + isDiscontinued + ", isSale=" + isSale + ", isPremium=" + isPremium
				+ ", prdtType=" + prdtType + ", prdtCategory=" + prdtCategory + ", inventoryDtls=" + inventoryDtls
				+ ", sellerDetails=" + sellerDetails + ", dropShipDtls=" + dropShipDtls + ", productTags=" + productTags
				+ "]";
	}

  public void merge(ProductDtls provider) {
    setPrdtDesc(provider.getPrdtDesc());
    setDetailedSpec(provider.getDetailedSpec());
    setPrdtTitle(provider.getPrdtTitle());
    setBasePrice(provider.getSellPrice());
    setSellPrice(provider.getSellPrice());
    setDiscountedPrice(provider.getDiscountedPrice());
  }

}
