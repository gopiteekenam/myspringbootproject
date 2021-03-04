package com.biz2tech.app.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the ProductRating entity.
 */
public class ProductRatingDTO implements Serializable {

    private Long id;

    private BigDecimal prdtId;

    private Double userRating;

    private BigDecimal colorPosition;

    private BigDecimal colorMoodLevel;

    private String lastUpdatedBy;

    private Instant lastUpdatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrdtId() {
        return prdtId;
    }

    public void setPrdtId(BigDecimal prdtId) {
        this.prdtId = prdtId;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public BigDecimal getColorPosition() {
        return colorPosition;
    }

    public void setColorPosition(BigDecimal colorPosition) {
        this.colorPosition = colorPosition;
    }

    public BigDecimal getColorMoodLevel() {
        return colorMoodLevel;
    }

    public void setColorMoodLevel(BigDecimal colorMoodLevel) {
        this.colorMoodLevel = colorMoodLevel;
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

        ProductRatingDTO productRatingDTO = (ProductRatingDTO) o;
        if (productRatingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productRatingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductRatingDTO{" +
            "id=" + getId() +
            ", prdtId=" + getPrdtId() +
            ", userRating=" + getUserRating() +
            ", colorPosition=" + getColorPosition() +
            ", colorMoodLevel=" + getColorMoodLevel() +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedOn='" + getLastUpdatedOn() + "'" +
            "}";
    }
}
