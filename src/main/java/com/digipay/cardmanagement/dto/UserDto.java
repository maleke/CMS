package com.digipay.cardmanagement.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {
    @NotNull(message = "{null.name}")
    private String name;

    private String phoneNumber;

    private List<CardDto> cards = new ArrayList<>();

    //region getter And setter

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<CardDto> getCards() {
        return cards;
    }

    public UserDto setCards(List<CardDto> cards) {
        this.cards = cards;
        return this;
    }

    //endregion
}
