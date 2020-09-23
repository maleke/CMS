package com.digipay.cardmanagement.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateCardDto implements Serializable {
    @NotNull(message = "{null.userId}")
    private Long userId;

    Set<CardDto> cardDtos = new HashSet<>();

    //region getter and setter
    public Long getUserId() {
        return userId;
    }

    public CreateCardDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Set<CardDto> getCardDtos() {
        return cardDtos;
    }

    public CreateCardDto setCardDtos(Set<CardDto> cardDtos) {
        this.cardDtos = cardDtos;
        return this;
    }

    //endregion


    @Override
    public String toString() {
        return "CreateCardDto{" +
                "userId=" + userId +
                ", cardDtos=" + cardDtos +
                '}';
    }
}
