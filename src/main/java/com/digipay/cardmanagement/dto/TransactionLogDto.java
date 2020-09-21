package com.digipay.cardmanagement.dto;

import com.digipay.cardmanagement.enums.TransactionStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TransactionLogDto implements Serializable {

    private String cardNumber;

    private String transactionDate;
    private TransactionStatus transactionStatus = TransactionStatus.FAILED;
}
