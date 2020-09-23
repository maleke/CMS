package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.common.Constants;
import com.digipay.cardmanagement.common.dto.ProviderMessageRequestDTO;
import com.digipay.cardmanagement.entity.User;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.exceptions.error.ErrorCode;
import com.digipay.cardmanagement.exceptions.error.FieldErrorDTO;
import com.digipay.cardmanagement.repository.CardRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {
  private final CardRepository cardRepository;
  private final RabbitTemplate rabbitTemplate;

  public MessagingService(CardRepository cardRepository, RabbitTemplate rabbitTemplate) {
    this.cardRepository = cardRepository;
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(String cardNumber) throws ServiceException {
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

  public User findUserBySource(String source) throws ServiceException {
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
