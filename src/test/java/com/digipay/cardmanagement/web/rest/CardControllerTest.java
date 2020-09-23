package com.digipay.cardmanagement.web.rest;

import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.entity.Card;
import com.digipay.cardmanagement.entity.User;
import com.digipay.cardmanagement.mapper.CardMapper;
import com.digipay.cardmanagement.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CardService cardService;
    @MockBean
    CardMapper cardMapper;

    private static ObjectMapper mapper = new ObjectMapper();


    @Test
    void transferMoneyTest() throws Exception {
        CardTransferRequestDto cardTransferRequestDto = new CardTransferRequestDto()
                .setSource("6104234556784986").setDest("60375896245685248")
                .setCvv2("1234").setPin("12345")
                .setExpDate("0002").setAmount(1000L);

        // execute
        String cardTransferRequest = mapper.writeValueAsString(cardTransferRequestDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/payments/transfer")
                .accept(MediaType.APPLICATION_JSON)
                .content(cardTransferRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        // verify that service method was called once
        verify(cardService).transferMoney(any(CardTransferRequestDto.class));

    }

    @Test
    List<CardDto> findCardsByUserIdTest() throws Exception{
        Set<Card> cards = new HashSet<>();
        Card card = new Card().setCardNumber("6104234556784986")
                .setCvv2("1234")
                .setPin("12345")
                .setExpDate("0002");
        cards.add(card);
        User user = new User().setId(1000L).setName("testUser")
                .setPhoneNumber("09999999999")
                .setCards(cards);
        Set<CardDto> cardDtos = cardMapper.cardsToCardDtos(cards);
        Mockito.when(cardService.findCardsByUserId(ArgumentMatchers.anyLong())).thenReturn(cardDtos);
        return null;
    }
}

