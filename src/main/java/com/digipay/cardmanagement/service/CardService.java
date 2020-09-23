package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.common.Constants;
import com.digipay.cardmanagement.common.utility.TimeUtility;
import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.entity.Card;
import com.digipay.cardmanagement.entity.TransactionLog;
import com.digipay.cardmanagement.entity.User;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.exceptions.error.ErrorCode;
import com.digipay.cardmanagement.exceptions.error.FieldErrorDTO;
import com.digipay.cardmanagement.mapper.CardMapper;
import com.digipay.cardmanagement.repository.CardRepository;
import com.digipay.cardmanagement.service.payment.PaymentService;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CardService {
  private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardService.class);

  private final CardRepository cardRepository;
  private final CardMapper cardMapper;
  private final RabbitTemplate rabbitTemplate;
  private final PaymentService paymentService;
  private final MessagingService messagingServic;

  public CardService(
          CardRepository cardRepository,
          CardMapper cardMapper,
          RabbitTemplate rabbitTemplate,
          PaymentService paymentService, MessagingService messagingServic) {
    this.cardRepository = cardRepository;
    this.cardMapper = cardMapper;
    this.rabbitTemplate = rabbitTemplate;
    this.paymentService = paymentService;
    this.messagingServic = messagingServic;
  }

  public void deleteCard(Long id) {
    logger.info("Ready to delete card with id {}", id);
    cardRepository.deleteById(id);
  }

  public Set<CardDto> findCardsByUserId(Long userId) {
    Set<Card> cards = cardRepository.findByUserId(userId);
    return cardMapper.cardsToCardDtos(cards);
  }

  public void transferMoney(CardTransferRequestDto cardTransferRequestDto) throws ServiceException {
    // call service for transferring money
    Boolean result = paymentService.transferMoney(cardTransferRequestDto);
    insertTransactionToDB(cardTransferRequestDto, result);
    if (result) {
      messagingServic.sendMessage(cardTransferRequestDto.getSource());
    }
  }

  // call DatabaseQueueReceiver
  private void insertTransactionToDB(
      CardTransferRequestDto cardTransferRequestDto, Boolean result) {
    TransactionLog transactionLog =
        new TransactionLog()
            .setSource(cardTransferRequestDto.getSource())
            .setDest(cardTransferRequestDto.getDest())
            .setCvv2(cardTransferRequestDto.getCvv2())
            .setExpDate(cardTransferRequestDto.getExpDate())
            .setPin(cardTransferRequestDto.getPin())
            .setTransactionDate(TimeUtility.getCurrentDate());

    if (result) transactionLog.setSuccessStatus(1L);
    else transactionLog.setFailStatus(1L);

    rabbitTemplate.convertAndSend(
        Constants.EXCHANGE_NAME, Constants.DATABASE_ROUTING_KEY_NAME, transactionLog);
  }


}
