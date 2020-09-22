package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.common.Constants;
import com.digipay.cardmanagement.common.dto.ProviderMessageRequestDTO;
import com.digipay.cardmanagement.entity.User;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.exceptions.error.ErrorCode;
import com.digipay.cardmanagement.exceptions.error.FieldErrorDTO;
import com.digipay.cardmanagement.repository.CardRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {
    private final CardRepository cardRepository;
    private final RabbitTemplate rabbitTemplate;

    public MessagingService(CardRepository cardRepository, RabbitTemplate rabbitTemplate) {
        this.cardRepository = cardRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

   // @HystrixCommand(fallbackMethod = "reliable")
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

    @Bean
    public String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }
}
