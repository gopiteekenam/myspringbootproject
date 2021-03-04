package com.biz2tech.app.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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
 * A SellerDetails.
 */
@Entity
@Table(name = "seller_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SellerDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "sellerDetails")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductDtls> productDtls = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SellerDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public SellerDetails address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<ProductDtls> getProductDtls() {
        return productDtls;
    }

    public SellerDetails productDtls(Set<ProductDtls> productDtls) {
        this.productDtls = productDtls;
        return this;
    }

    public SellerDetails addProductDtls(ProductDtls productDtls) {
        this.productDtls.add(productDtls);
        productDtls.setSellerDetails(this);
        return this;
    }

    public SellerDetails removeProductDtls(ProductDtls productDtls) {
        this.productDtls.remove(productDtls);
        productDtls.setSellerDetails(null);
        return this;
    }

    public void setProductDtls(Set<ProductDtls> productDtls) {
        this.productDtls = productDtls;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SellerDetails sellerDetails = (SellerDetails) o;
        if (sellerDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sellerDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SellerDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
