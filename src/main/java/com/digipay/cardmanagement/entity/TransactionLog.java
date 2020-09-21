package com.digipay.cardmanagement.entity;

import com.digipay.cardmanagement.enums.TransactionStatus;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  //  @SequenceGenerator(
  //      name = "transaction_log_id_seq",
  //      sequenceName = "transaction_log_id_seq",
  //      allocationSize = 1)
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_log_id_seq")
  private Long id;

  @NotNull(message = "{null.cardNumber}")
  private String source;

  @NotNull(message = "{null.dest}")
  private String dest;

  @NotNull(message = "{null.cvv2}")
  private String cvv2;

  @NotNull(message = "{null.expDate}")
  private String expDate;
  // todo:: encrypt pin for save in database
  @NotNull(message = "{null.pin}")
  private String pin;

  @Column(name = "transaction_date")
  private String transactionDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_status")
  private TransactionStatus transactionStatus = TransactionStatus.FAILED;

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

  public String getCvv2() {
    return cvv2;
  }

  public TransactionLog setCvv2(String cvv2) {
    this.cvv2 = cvv2;
    return this;
  }

  public String getExpDate() {
    return expDate;
  }

  public TransactionLog setExpDate(String expDate) {
    this.expDate = expDate;
    return this;
  }

  public String getPin() {
    return pin;
  }

  public TransactionLog setPin(String pin) {
    this.pin = pin;
    return this;
  }

  public String getTransactionDate() {
    return transactionDate;
  }

  public TransactionLog setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  public TransactionStatus getTransactionStatus() {
    return transactionStatus;
  }

  public TransactionLog setTransactionStatus(TransactionStatus transactionStatus) {
    this.transactionStatus = transactionStatus;
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
        + ", transactionStatus="
        + transactionStatus
        + '}';
  }
}
