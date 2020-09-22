package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.common.Constants;
import com.digipay.cardmanagement.common.dto.ProviderMessageRequestDTO;
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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
  private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardService.class);

  private final CardRepository cardRepository;
  private final CardMapper cardMapper;
  private final RabbitTemplate rabbitTemplate;
  private final UserService userService;
  private final PaymentService paymentService;

  public CardService(
      CardRepository cardRepository,
      CardMapper cardMapper,
      RabbitTemplate rabbitTemplate,
      UserService userService,
      PaymentService paymentService) {
    this.cardRepository = cardRepository;
    this.cardMapper = cardMapper;
    this.rabbitTemplate = rabbitTemplate;
    this.userService = userService;
    this.paymentService = paymentService;
  }

  public void deleteCard(Long id) {
    logger.info("Ready to delete card with id {}", id);
    cardRepository.deleteById(id);
  }

  public List<CardDto> findCardsByUserId(Long userId) {
    List<Card> cards = cardRepository.findByUserId(userId);
    return cardMapper.cardsToCardDtos(cards);
  }

  public void cardTransfer(CardTransferRequestDto cardTransferRequestDto) throws ServiceException {
    // call service for transferring money
    Boolean result = paymentService.transferMoney(cardTransferRequestDto);
    insertTransactionToDB(cardTransferRequestDto, result);
    if (result) {
      sendMessage(cardTransferRequestDto.getSource());
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

  @HystrixCommand(fallbackMethod = "reliable")
  private void sendMessage(String cardNumber) throws ServiceException {
    User user = findUserBySource(cardNumber);
    ProviderMessageRequestDTO providerMessageRequestDTO =
        new ProviderMessageRequestDTO()
            .setMobileNo(user.getPhoneNumber())
            .setMessage(Constants.SUCCESSFUL_TRANSFER_MONEY);

    // using queue to notification service for increasing performance
    rabbitTemplate.convertAndSend(
        Constants.EXCHANGE_NAME,
        Constants.NOTIFICATION_ROUTING_KEY_NAME,
        providerMessageRequestDTO);
  }

  public String reliable() {
    return "Cloud Native Java (O'Reilly)";
  }

  private User findUserBySource(String source) throws ServiceException {
    return cardRepository
        .findUserByCardNumber(source)
        .orElseThrow(
            () ->
                new ServiceException(
                    new FieldErrorDTO()
                        .setErrorDescription(ErrorCode.USER_NOT_EXIST.getMessage())
                        .setErrorCode(String.valueOf(ErrorCode.USER_NOT_EXIST.getCode()))));
  }
}
