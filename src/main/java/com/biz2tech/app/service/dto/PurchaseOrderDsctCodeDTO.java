package com.biz2tech.app.service.dto;


import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;

/**
 * A DTO for the PurchaseOrderDsctCode entity.
 */
public class PurchaseOrderDsctCodeDTO implements Serializable {

    private Long id;

    private String code;

    private Double discountPercentage;

    private Instant validFrom;

    private Instant validTo;

    private String createdBy;

    private Instant createdOn;

    private String lastUpdatedBy;

    private Instant lastUpdatedOn;
    
    private String couponType;
    
    private Long couponCount;
    
    public String getEligibilityCriteria() {
		return eligibilityCriteria;
	}

	public void setEligibilityCriteria(String eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	private String eligibilityCriteria;
    
    private String comments;

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

	private String elegibilityCriteria;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Instant getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    public Instant getValidTo() {
        return validTo;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((couponCount == null) ? 0 : couponCount.hashCode());
		result = prime * result + ((couponType == null) ? 0 : couponType.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((discountPercentage == null) ? 0 : discountPercentage.hashCode());
		result = prime * result + ((elegibilityCriteria == null) ? 0 : elegibilityCriteria.hashCode());
		result = prime * result + ((eligibilityCriteria == null) ? 0 : eligibilityCriteria.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result + ((lastUpdatedOn == null) ? 0 : lastUpdatedOn.hashCode());
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
		PurchaseOrderDsctCodeDTO other = (PurchaseOrderDsctCodeDTO) obj;
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
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
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
		if (eligibilityCriteria == null) {
			if (other.eligibilityCriteria != null)
				return false;
		} else if (!eligibilityCriteria.equals(other.eligibilityCriteria))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdatedBy == null) {
			if (other.lastUpdatedBy != null)
				return false;
		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
			return false;
		if (lastUpdatedOn == null) {
			if (other.lastUpdatedOn != null)
				return false;
		} else if (!lastUpdatedOn.equals(other.lastUpdatedOn))
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
		return "PurchaseOrderDsctCodeDTO [id=" + id + ", code=" + code + ", discountPercentage=" + discountPercentage
				+ ", validFrom=" + validFrom + ", validTo=" + validTo + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedOn=" + lastUpdatedOn + ", couponType="
				+ couponType + ", couponCount=" + couponCount + ", eligibilityCriteria=" + eligibilityCriteria
				+ ", comments=" + comments + ", elegibilityCriteria=" + elegibilityCriteria + "]";
	}
}
