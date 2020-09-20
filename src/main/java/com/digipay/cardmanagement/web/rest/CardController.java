package com.digipay.cardmanagement.web.rest;

import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.service.CardService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "")
public class CardController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @ApiOperation(value = "(delete card) User can delete card by card id")
    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        logger.debug("Request to delete card with id: {}", id);
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "(find cards) User can get card list")
    @GetMapping("/cards/{userId}")
    public ResponseEntity<List<CardDto>> findCardsByUserId(@PathVariable Long userId){

        List<CardDto> result = cardService.findCardsByUserId(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/payments/transfer")
    public ResponseEntity<Void> cnpCardTransfer(@Valid @RequestBody CardTransferRequestDto cardTransferRequestDto) throws Exception {
        cardService.cardTransfer(cardTransferRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
