package com.digipay.cardmanagement.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TransferMoneyDto implements Serializable {
    @NotNull(message = "{null.source}")
    private String source;
    @NotNull(message = "{null.dest}")
    private String dest;
    @NotNull(message = "{null.cvv2}")
    private String cvv2;
    @NotNull(message = "{null.expDate}")
    private String expDate;
    @NotNull(message = "{null.pin}")
    private String pin;

    //region getter and setter

    public String getSource() {
        return source;
    }

    public TransferMoneyDto setSource(String source) {
        this.source = source;
        return this;
    }

    public String getDest() {
        return dest;
    }

    public TransferMoneyDto setDest(String dest) {
        this.dest = dest;
        return this;
    }

    public String getCvv2() {
        return cvv2;
    }

    public TransferMoneyDto setCvv2(String cvv2) {
        this.cvv2 = cvv2;
        return this;
    }

    public String getExpDate() {
        return expDate;
    }

    public TransferMoneyDto setExpDate(String expDate) {
        this.expDate = expDate;
        return this;
    }

    public String getPin() {
        return pin;
    }

    public TransferMoneyDto setPin(String pin) {
        this.pin = pin;
        return this;
    }


    //endregion
}
