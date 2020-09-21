package com.digipay.cardmanagement.entity;

import com.digipay.cardmanagement.enums.TransactionStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "transaction_log")
public class TransactionLog implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @SequenceGenerator(name = "transaction_log_id_seq", sequenceName = "transaction_log_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_log_id_seq")
    private Long id;

    @NotNull(message = "{null.cardNumber}")
    private String cardNumber;
    @NotNull(message = "{null.cvv2}")
    private String cvv2;
    @NotNull(message = "{null.expDate}")
    private String expDate;
    //todo:: encrypt pin for save in database
    @NotNull(message = "{null.pin}")
    private String pin;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus = TransactionStatus.FAILED;

    //region getter And setter

    public String getCardNumber() {
        return cardNumber;
    }

    public TransactionLog setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    //endregion


    @Override
    public String toString() {
        return "TransactionLog{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionStatus=" + transactionStatus +
                '}';
    }
}
