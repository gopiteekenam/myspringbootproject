package com.biz2tech.app.service.dto;


import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the DropShipDtls entity.
 */
public class DropShipDtlsDTO implements Serializable {

    private Long id;

    private String vendorName;

    private String vendorUrl;

    private Double totalChrgToCust;

    private String currencyCode;

    private Double margin;

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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorUrl() {
        return vendorUrl;
    }

    public void setVendorUrl(String vendorUrl) {
        this.vendorUrl = vendorUrl;
    }

    public Double getTotalChrgToCust() {
        return totalChrgToCust;
    }

    public void setTotalChrgToCust(Double totalChrgToCust) {
        this.totalChrgToCust = totalChrgToCust;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
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

        DropShipDtlsDTO dropShipDtlsDTO = (DropShipDtlsDTO) o;
        if(dropShipDtlsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dropShipDtlsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DropShipDtlsDTO{" +
            "id=" + getId() +
            ", vendorName='" + getVendorName() + "'" +
            ", vendorUrl='" + getVendorUrl() + "'" +
            ", totalChrgToCust=" + getTotalChrgToCust() +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", margin=" + getMargin() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedOn='" + getLastUpdatedOn() + "'" +
            "}";
    }
}
