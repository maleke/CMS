package com.digipay.cardmanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(
    name = "transaction_log",
    indexes = {
      @Index(columnList = "source", name = "source_idx"),
      @Index(columnList = "transaction_date", name = "transactionDate_idx")
    })
public class TransactionLog implements Serializable {
  @Id
  @Column(name = "id", updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "{null.cardNumber}")
  private String source;

  @NotNull(message = "{null.dest}")
  private String dest;

  @NotNull(message = "{null.expDate}")
  private String expDate;

  @Column(name = "transaction_date")
  private String transactionDate;

  @Column(name = "success_status")
  private Long successStatus;

  @Column(name = "fail_status")
  private Long failStatus;
  // region getter And setter

  public String getSource() {
    return source;
  }

  public TransactionLog setSource(String source) {
    this.source = source;
    return this;
  }

  public String getDest() {
    return dest;
  }

  public TransactionLog setDest(String dest) {
    this.dest = dest;
    return this;
  }

  public String getExpDate() {
    return expDate;
  }

  public TransactionLog setExpDate(String expDate) {
    this.expDate = expDate;
    return this;
  }

  public String getTransactionDate() {
    return transactionDate;
  }

  public TransactionLog setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  public Long getSuccessStatus() {
    return successStatus;
  }

  public TransactionLog setSuccessStatus(Long successStatus) {
    this.successStatus = successStatus;
    return this;
  }

  public Long getFailStatus() {
    return failStatus;
  }

  public TransactionLog setFailStatus(Long failStatus) {
    this.failStatus = failStatus;
    return this;
  }

  // endregion

  @Override
  public String toString() {
    return "TransactionLog{"
        + "source='"
        + source
        + '\''
        + ", dest='"
        + dest
        + '\''
        + ", transactionDate='"
        + transactionDate
        + '\''
        + ", successStatus="
        + successStatus
        + ", failStatus="
        + failStatus
        + '}';
  }
}
