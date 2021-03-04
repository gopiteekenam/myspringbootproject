package com.biz2tech.app.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biz2tech.app.domain.enumeration.PaymentState;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Payment extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "bank_name")
  private String bankName;

  @NotNull
  @Column(name = "transaction_reference", nullable = false)
  private String transactionReference;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_code")
  private PaymentState statusCode;

  @Column(name = "payment_notes")
  private String paymentNotes;

  @OneToOne(mappedBy = "payment")
  @JsonIgnore
  private PurchaseOrder purchaseOrder;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBankName() {
    return bankName;
  }

  public Payment bankName(String bankName) {
    this.bankName = bankName;
    return this;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getTransactionReference() {
    return transactionReference;
  }

  public Payment transactionReference(String transactionReference) {
    this.transactionReference = transactionReference;
    return this;
  }

  public void setTransactionReference(String transactionReference) {
    this.transactionReference = transactionReference;
  }

  public PaymentState getStatusCode() {
    return statusCode;
  }

  public Payment statusCode(PaymentState statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public void setStatusCode(PaymentState statusCode) {
    this.statusCode = statusCode;
  }

  public String getPaymentNotes() {
    return paymentNotes;
  }

  public Payment paymentNotes(String paymentNotes) {
    this.paymentNotes = paymentNotes;
    return this;
  }

  public void setPaymentNotes(String paymentNotes) {
    this.paymentNotes = paymentNotes;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public Payment purchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
    return this;
  }

  public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
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
    Payment payment = (Payment) o;
    if (payment.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), payment.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Payment [id=" + id + ", bankName=" + bankName + ", transactionReference="
        + transactionReference + ", statusCode=" + statusCode + ", paymentNotes=" + paymentNotes
        + ", purchaseOrder=" + purchaseOrder + "]";
  }

}
