package com.biz2tech.app.service.dto;


import java.io.Serializable;
import java.time.Instant;

/**
 * DTO implementation for Color Category
 */
public class ColorCategoryDTO implements Serializable {

    private Long id;

    private String categoryName;

    private String subCategoryName;

    private String colorName;
    
    private String colorCode;
    
    private Double percentageDistribution;

    private String comments;

    private String createdBy;

    private Instant createdOn;

    private String lastUpdatedBy;

    private Instant lastUpdatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Double getPercentageDistribution() {
		return percentageDistribution;
	}

	public void setPercentageDistribution(Double percentageDistribution) {
		this.percentageDistribution = percentageDistribution;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((colorCode == null) ? 0 : colorCode.hashCode());
		result = prime * result + ((colorName == null) ? 0 : colorName.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result + ((lastUpdatedOn == null) ? 0 : lastUpdatedOn.hashCode());
		result = prime * result + ((percentageDistribution == null) ? 0 : percentageDistribution.hashCode());
		result = prime * result + ((subCategoryName == null) ? 0 : subCategoryName.hashCode());
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
		ColorCategoryDTO other = (ColorCategoryDTO) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (colorCode == null) {
			if (other.colorCode != null)
				return false;
		} else if (!colorCode.equals(other.colorCode))
			return false;
		if (colorName == null) {
			if (other.colorName != null)
				return false;
		} else if (!colorName.equals(other.colorName))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
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
		if (percentageDistribution == null) {
			if (other.percentageDistribution != null)
				return false;
		} else if (!percentageDistribution.equals(other.percentageDistribution))
			return false;
		if (subCategoryName == null) {
			if (other.subCategoryName != null)
				return false;
		} else if (!subCategoryName.equals(other.subCategoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ColorCategoryDTO [id=" + id + ", categoryName=" + categoryName + ", subCategoryName=" + subCategoryName
				+ ", colorName=" + colorName + ", colorCode=" + colorCode + ", percentageDistribution="
				+ percentageDistribution + ", comments=" + comments + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedOn=" + lastUpdatedOn + "]";
	}

    
}
