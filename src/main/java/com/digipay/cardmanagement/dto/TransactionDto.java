package com.digipay.cardmanagement.dto;

import java.io.Serializable;

public class TransactionDto implements Serializable {
    private String startDate;
    private String endTime;

    public TransactionDto() {
    }

    public String getStartDate() {
        return startDate;
    }

    public TransactionDto setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public TransactionDto setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }
}
