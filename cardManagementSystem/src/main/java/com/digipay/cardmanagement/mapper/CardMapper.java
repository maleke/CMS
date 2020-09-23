package com.digipay.cardmanagement.mapper;

import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    CardDto cardToCardDto(Card card);
    Set<CardDto> cardsToCardDtos(Set<Card> cards);

    Card cardDtoToCard(CardDto cardDto);
    Set<Card> cardDtosToCards(Set<CardDto> cardDtos);
}
