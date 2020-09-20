package com.digipay.cardmanagement.web.rest;

import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.service.CardService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cards")
public class CardController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmsService(@PathVariable Long id) throws ServiceException {
        logger.debug("Request to delete card with id: {}", id);
        //dont delete service just disable it
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<CardDto>> findCardsByUserId(@PathVariable Long userId)
            throws ServiceException {

        List<CardDto> result = cardService.findCardsByUserId(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}