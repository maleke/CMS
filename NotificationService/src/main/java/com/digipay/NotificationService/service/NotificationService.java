package com.digipay.NotificationService.service;

import com.digipay.NotificationService.common.Constants;
import com.digipay.NotificationService.common.ProviderMessageRequestDTO;
import com.digipay.NotificationService.common.ProviderMessageResponseDto;
import com.rabbitmq.client.Channel;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class NotificationService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final SmsProviderMock smsProviderMock;

    public NotificationService(SmsProviderMock smsProviderMock) {
        this.smsProviderMock = smsProviderMock;
    }

    @RabbitListener(queues = Constants.INCOMING_QUEUE_NAME)
    public void sendMessage(ProviderMessageRequestDTO providerMessageRequestDTO, Channel channel,
                            @Header(AmqpHeaders.DELIVERY_TAG) long tag,
                            @Header(name = "x-death", required = false) Map<?, ?> death) throws IOException {

        logger.info("Message " + providerMessageRequestDTO.toString() +
                " Received");
        try {
            smsProviderMock.sendMessage(providerMessageRequestDTO);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            logger.error("An error has been occurred while sending ack to provider");
            if (hasExceededRetryCount(death)) {
                logger.info("Retries exeeded. send nack to notification service");
                channel.basicNack(tag, false, true);
            } else channel.basicReject(tag, false);
        }
    }

    private boolean hasExceededRetryCount(Map<?, ?> xDeathHeader) {
        if (xDeathHeader != null && xDeathHeader.get("count").equals(3L)) {
            throw new ImmediateAcknowledgeAmqpException("Failed after 4 attempts");
        }
        throw new AmqpRejectAndDontRequeueException("failed");
    }

}
