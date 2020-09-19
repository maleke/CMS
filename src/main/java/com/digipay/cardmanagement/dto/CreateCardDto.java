package com.digipay.cardmanagement.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreateCardDto implements Serializable {
    @NotNull(message = "{null.userId}")
    private Long userId;

    List<CardDto> cardDtos = new ArrayList<>();

    //region getter and setter
    public Long getUserId() {
        return userId;
    }

    public CreateCardDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }

    public CreateCardDto setCardDtos(List<CardDto> cardDtos) {
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
