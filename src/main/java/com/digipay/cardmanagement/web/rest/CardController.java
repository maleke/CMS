package com.digipay.cardmanagement.web.rest;

import com.digipay.cardmanagement.service.CardService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cards")
public class CardController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }



}
