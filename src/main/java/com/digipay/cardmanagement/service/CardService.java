package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.common.Constants;
import com.digipay.cardmanagement.common.dto.ProviderMessageRequestDTO;
import com.digipay.cardmanagement.dto.CardDto;
import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.entity.Card;
import com.digipay.cardmanagement.entity.User;
import com.digipay.cardmanagement.interfaces.PaymentProvider;
import com.digipay.cardmanagement.mapper.CardMapper;
import com.digipay.cardmanagement.repository.CardRepository;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class CardService {
  private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardService.class);

  private final CardRepository cardRepository;
  private final CardMapper cardMapper;
  private final Map<String, PaymentProvider> paymentProviderStrategy;
  private final RabbitTemplate rabbitTemplate;
  private final UserService userService;

  public CardService(
      CardRepository cardRepository,
      CardMapper cardMapper,
      Map<String, PaymentProvider> paymentProviderStrategy,
      RabbitTemplate rabbitTemplate,
      UserService userService) {
    this.cardRepository = cardRepository;
    this.cardMapper = cardMapper;
    this.paymentProviderStrategy = paymentProviderStrategy;
    this.rabbitTemplate = rabbitTemplate;
    this.userService = userService;
  }

  @PostConstruct
  public void transfer() {
    CardTransferRequestDto cardTransferRequestDto =
        new CardTransferRequestDto()
            .setSource("5892232")
            .setAmount(1000L)
            .setDest("6037")
            .setExpDate("0300")
            .setPin("1234");
    cardTransfer(cardTransferRequestDto);
  }

  public void deleteCard(Long id) {
    logger.info("Ready to delete card with id {}", id);
    cardRepository.deleteById(id);
  }

  public List<CardDto> findCardsByUserId(Long userId) {
    List<Card> cards = cardRepository.findByUserId(userId);
    return cardMapper.cardsToCardDtos(cards);
  }

  public void cardTransfer(CardTransferRequestDto cardTransferRequestDto) {
    // call service for transferring money
    String paymentProviderName =
        cardTransferRequestDto.getSource().startsWith("6037")
            ? "paymentProvider1"
            : "paymentProvider2";
    PaymentProvider paymentProvider = findPaymentProvider(paymentProviderName);
    Boolean result = paymentProvider.transferMoney(cardTransferRequestDto);
    if (result) {
      sendMessage(cardTransferRequestDto.getSource());
    }
  }

  private void sendMessage(String cardNumber) {
    // User user = findUserBySource(cardNumber);
    ProviderMessageRequestDTO providerMessageRequestDTO =
        new ProviderMessageRequestDTO("user.getPhoneNumber()", Constants.SUCCESSFUL_TRANSFER_MONEY);
    //    ProviderMessageRequestDTO providerMessageRequestDTO =
    //        new ProviderMessageRequestDTO()
    //            .setMobileNo("user.getPhoneNumber()")
    //            .setMessage(Constants.SUCCESSFUL_TRANSFER_MONEY);
    // using queue to message increase performance

    rabbitTemplate.convertAndSend(
        Constants.EXCHANGE_NAME, Constants.ROUTING_KEY_NAME, providerMessageRequestDTO);
  }

  private User findUserBySource(String source) {
    Long userId = cardRepository.findBycardNumber(source);
    return userService.getById(userId).get();
  }

  private PaymentProvider findPaymentProvider(String cardNumber) {
    return paymentProviderStrategy.get(cardNumber);
  }
}
