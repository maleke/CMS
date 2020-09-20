package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.entity.Card;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.mapper.CardMapper;
import com.digipay.cardmanagement.repository.CardRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    public void deleteCard(Long id) throws ServiceException {
        logger.info("Ready to delete card with id {}", id);
        cardRepository.deleteById(id);

    }

    public List<CardDto> findCardsByUserId(Long userId) {
        List<Card> cards = cardRepository.findByUserId(userId);
        return cardMapper.cardsToCardDtos(cards);
    }
}
