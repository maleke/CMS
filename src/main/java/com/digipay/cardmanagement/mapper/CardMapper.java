package com.digipay.cardmanagement.mapper;

import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    CardDto cardToCardDto(Card card);
    List<CardDto> cardsToCardDtos(List<Card> cards);

    Card cardDtoToCard(CardDto cardDto);
    List<Card> cardDtosToCards(List<CardDto> cardDtos);
}
