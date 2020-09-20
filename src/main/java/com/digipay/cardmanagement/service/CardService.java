package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.exceptions.error.ErrorCode;
import com.digipay.cardmanagement.exceptions.error.FieldErrorDTO;
import com.digipay.cardmanagement.repository.CardRepository;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public void deleteCard(Long id) throws ServiceException {
        logger.info("Ready to delete card with id {}", id);
        cardRepository.deleteById(id);

    }


}
