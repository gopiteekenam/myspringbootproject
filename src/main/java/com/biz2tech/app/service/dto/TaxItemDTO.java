package com.biz2tech.app.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the TaxItem entity.
 */
public class TaxItemDTO implements Serializable {

    private Long id;

    private BigDecimal pincode;

    private String name;

    private String description;

    private Double percentage;

    private Instant applicableFrom;

    private Instant applicableTill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPincode() {
        return pincode;
    }

    public void setPincode(BigDecimal pincode) {
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Instant getApplicableFrom() {
        return applicableFrom;
    }

    public void setApplicableFrom(Instant applicableFrom) {
        this.applicableFrom = applicableFrom;
    }

    public Instant getApplicableTill() {
        return applicableTill;
    }

    public void setApplicableTill(Instant applicableTill) {
        this.applicableTill = applicableTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaxItemDTO taxItemDTO = (TaxItemDTO) o;
        if(taxItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taxItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaxItemDTO{" +
            "id=" + getId() +
            ", pincode=" + getPincode() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", percentage=" + getPercentage() +
            ", applicableFrom='" + getApplicableFrom() + "'" +
            ", applicableTill='" + getApplicableTill() + "'" +
            "}";
    }
}
