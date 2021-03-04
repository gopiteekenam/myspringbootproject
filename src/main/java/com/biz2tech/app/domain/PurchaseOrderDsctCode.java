package com.biz2tech.app.domain;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A PurchaseOrderDsctCode.
 */
@Entity
@Table(name = "purchase_order_dsct_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PurchaseOrderDsctCode extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "code")
  private String code;

  @Column(name = "discount_percentage")
  private Double discountPercentage;

  @Column(name = "valid_from")
  private Instant validFrom;

  @Column(name = "valid_to")
  private Instant validTo;
  
  @Column(name = "coupon_type")
  private String couponType;
  
  @Column(name = "coupon_count")
  private Long couponCount;

  @Column(name = "eligibility_criteria")
  private String elegibilityCriteria;
  
  @Column(name = "comments")
  private String comments;



  public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public String getCouponType() {
	return couponType;
}

public void setCouponType(String couponType) {
	this.couponType = couponType;
}

public Long getCouponCount() {
	return couponCount;
}

public void setCouponCount(Long couponCount) {
	this.couponCount = couponCount;
}

public String getElegibilityCriteria() {
	return elegibilityCriteria;
}

public void setElegibilityCriteria(String elegibilityCriteria) {
	this.elegibilityCriteria = elegibilityCriteria;
}

@OneToMany(mappedBy = "purchaseOrderDsctCode")
  @JsonIgnore
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<PurchaseOrder> purchaseOrders = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public PurchaseOrderDsctCode code(String code) {
    this.code = code;
    return this;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Double getDiscountPercentage() {
    return discountPercentage;
  }

  public PurchaseOrderDsctCode discountPercentage(Double discountPercentage) {
    this.discountPercentage = discountPercentage;
    return this;
  }

  public void setDiscountPercentage(Double discountPercentage) {
    this.discountPercentage = discountPercentage;
  }

  public Instant getValidFrom() {
    return validFrom;
  }

  public PurchaseOrderDsctCode validFrom(Instant validFrom) {
    this.validFrom = validFrom;
    return this;
  }

  public void setValidFrom(Instant validFrom) {
    this.validFrom = validFrom;
  }

  public Instant getValidTo() {
    return validTo;
  }

  public PurchaseOrderDsctCode validTo(Instant validTo) {
    this.validTo = validTo;
    return this;
  }

  public void setValidTo(Instant validTo) {
    this.validTo = validTo;
  }

  public Set<PurchaseOrder> getPurchaseOrders() {
    return purchaseOrders;
  }

  public PurchaseOrderDsctCode purchaseOrders(Set<PurchaseOrder> purchaseOrders) {
    this.purchaseOrders = purchaseOrders;
    return this;
  }

  public PurchaseOrderDsctCode addPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrders.add(purchaseOrder);
    purchaseOrder.setPurchaseOrderDsctCode(this);
    return this;
  }

  public PurchaseOrderDsctCode removePurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrders.remove(purchaseOrder);
    purchaseOrder.setPurchaseOrderDsctCode(null);
    return this;
  }

  public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
    this.purchaseOrders = purchaseOrders;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not
  // remove

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((code == null) ? 0 : code.hashCode());
	result = prime * result + ((comments == null) ? 0 : comments.hashCode());
	result = prime * result + ((couponCount == null) ? 0 : couponCount.hashCode());
	result = prime * result + ((couponType == null) ? 0 : couponType.hashCode());
	result = prime * result + ((discountPercentage == null) ? 0 : discountPercentage.hashCode());
	result = prime * result + ((elegibilityCriteria == null) ? 0 : elegibilityCriteria.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((purchaseOrders == null) ? 0 : purchaseOrders.hashCode());
	result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());
	result = prime * result + ((validTo == null) ? 0 : validTo.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	PurchaseOrderDsctCode other = (PurchaseOrderDsctCode) obj;
	if (code == null) {
		if (other.code != null)
			return false;
	} else if (!code.equals(other.code))
		return false;
	if (comments == null) {
		if (other.comments != null)
			return false;
	} else if (!comments.equals(other.comments))
		return false;
	if (couponCount == null) {
		if (other.couponCount != null)
			return false;
	} else if (!couponCount.equals(other.couponCount))
		return false;
	if (couponType == null) {
		if (other.couponType != null)
			return false;
	} else if (!couponType.equals(other.couponType))
		return false;
	if (discountPercentage == null) {
		if (other.discountPercentage != null)
			return false;
	} else if (!discountPercentage.equals(other.discountPercentage))
		return false;
	if (elegibilityCriteria == null) {
		if (other.elegibilityCriteria != null)
			return false;
	} else if (!elegibilityCriteria.equals(other.elegibilityCriteria))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (purchaseOrders == null) {
		if (other.purchaseOrders != null)
			return false;
	} else if (!purchaseOrders.equals(other.purchaseOrders))
		return false;
	if (validFrom == null) {
		if (other.validFrom != null)
			return false;
	} else if (!validFrom.equals(other.validFrom))
		return false;
	if (validTo == null) {
		if (other.validTo != null)
			return false;
	} else if (!validTo.equals(other.validTo))
		return false;
		return true;
	}

	@Override
	public String toString() {
		return "PurchaseOrderDsctCode [id=" + id + ", code=" + code + ", discountPercentage=" + discountPercentage
				+ ", validFrom=" + validFrom + ", validTo=" + validTo + ", couponType=" + couponType + ", couponCount="
				+ couponCount + ", elegibilityCriteria=" + elegibilityCriteria + ", comments=" + comments
				+ ", purchaseOrders=" + purchaseOrders + "]";
	}
}
