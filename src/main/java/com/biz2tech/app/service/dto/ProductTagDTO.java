package com.biz2tech.app.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the ProductTag entity.
 */
public class ProductTagDTO implements Serializable {

    private Long id;

    private String tagName;

    private String tagDescription;

    private BigDecimal productTagParentId;

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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public BigDecimal getProductTagParentId() {
        return productTagParentId;
    }

    public void setProductTagParentId(BigDecimal productTagParentId) {
        this.productTagParentId = productTagParentId;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductTagDTO productTagDTO = (ProductTagDTO) o;
        if(productTagDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productTagDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductTagDTO{" +
            "id=" + getId() +
            ", tagName='" + getTagName() + "'" +
            ", tagDescription='" + getTagDescription() + "'" +
            ", productTagParentId=" + getProductTagParentId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedOn='" + getLastUpdatedOn() + "'" +
            "}";
    }
}
