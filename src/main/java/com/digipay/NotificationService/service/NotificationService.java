package com.digipay.NotificationService.service;

import com.digipay.NotificationService.common.Constants;
import com.digipay.NotificationService.common.ProviderMessageRequestDTO;
import com.digipay.NotificationService.utility.SerializeUtility;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotificationService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @RabbitListener(queues = Constants.INCOMING_QUEUE_NAME)
    public void sendMessage(ProviderMessageRequestDTO providerMessageRequestDTO) throws IOException, ClassNotFoundException {

        logger.info("String instance "  + providerMessageRequestDTO.toString() +
                " [x] Received");

    }
}
