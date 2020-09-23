package com.digipay.cardmanagement.web.rest.integrationtest;


import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.entity.Card;
import com.digipay.cardmanagement.entity.User;
import com.digipay.cardmanagement.mapper.CardMapper;
import com.digipay.cardmanagement.repository.CardRepository;
import com.digipay.cardmanagement.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardControllerMockTest {

    @Autowired
    private CardService cardService;
    @MockBean
    private CardMapper cardMapper;
    @MockBean
    private CardRepository cardRepository;

    @Test
    void testFindCardsByUserId() {
        Set<Card> cards = new HashSet<>();
        Card card = new Card().setCardNumber("6104234556784986")
                .setCvv2("1234")
                .setPin("12345")
                .setExpDate("0002");
        cards.add(card);
        User user = new User().setId(1000L).setName("testUser")
                .setPhoneNumber("09999999999")
                .setCards(cards);
        Set<CardDto> cardDtos = new HashSet<>();
        CardDto cardDto = new CardDto().setCardNumber(card.getCardNumber())
                .setCvv2(card.getCvv2())
                .setExpDate(card.getExpDate())
                .setPin(card.getPin());
        cardDtos.add(cardDto);
        when(cardRepository.findByUserId(1000L)).thenReturn(cards);
        when(cardMapper.cardsToCardDtos(cards)).thenReturn(cardDtos);
        assertEquals(cardService.findCardsByUserId(1000L), cardDtos,"Unexpected result");
    }

}
