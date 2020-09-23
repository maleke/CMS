package com.digipay.cardmanagement.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CardDto implements Serializable {
    @NotNull(message = "{null.cardNumber}")
    private String cardNumber;
    @NotNull(message = "{null.cvv2}")
    private String cvv2;
    @NotNull(message = "{null.expDate}")
    private String expDate;
    //todo:: encrypt pin for save in database
    @NotNull(message = "{null.pin}")
    private String pin;

    //region getter and setter

    public String getCardNumber() {
        return cardNumber;
    }

    public CardDto setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCvv2() {
        return cvv2;
    }

    public CardDto setCvv2(String cvv2) {
        this.cvv2 = cvv2;
        return this;
    }

    public String getExpDate() {
        return expDate;
    }

    public CardDto setExpDate(String expDate) {
        this.expDate = expDate;
        return this;
    }

    public String getPin() {
        return pin;
    }

    public CardDto setPin(String pin) {
        this.pin = pin;
        return this;
    }


    //endregion
}
