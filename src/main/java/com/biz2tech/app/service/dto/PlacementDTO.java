package com.biz2tech.app.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Placement entity.
 */
public class PlacementDTO implements Serializable {

    private Long id;

    private String transporterName;

    @NotNull
    private String trackingDetails;

    private Instant placementDate;

    private Instant estimateDeliveryDate;

    private Instant actualDeliveryDate;

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

    public String getTransporterName() {
        return transporterName;
    }

    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }

    public String getTrackingDetails() {
        return trackingDetails;
    }

    public void setTrackingDetails(String trackingDetails) {
        this.trackingDetails = trackingDetails;
    }

    public Instant getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Instant placementDate) {
        this.placementDate = placementDate;
    }

    public Instant getEstimateDeliveryDate() {
        return estimateDeliveryDate;
    }

    public void setEstimateDeliveryDate(Instant estimateDeliveryDate) {
        this.estimateDeliveryDate = estimateDeliveryDate;
    }

    public Instant getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Instant actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
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

        PlacementDTO placementDTO = (PlacementDTO) o;
        if (placementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), placementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlacementDTO{" +
            "id=" + getId() +
            ", transporterName='" + getTransporterName() + "'" +
            ", trackingDetails='" + getTrackingDetails() + "'" +
            ", placementDate='" + getPlacementDate() + "'" +
            ", estimateDeliveryDate='" + getEstimateDeliveryDate() + "'" +
            ", actualDeliveryDate='" + getActualDeliveryDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedOn='" + getLastUpdatedOn() + "'" +
            "}";
    }
}
