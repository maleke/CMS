package com.digipay.cardmanagement.service.database;

import com.digipay.cardmanagement.common.Constants;
import com.digipay.cardmanagement.common.utility.SerializeUtility;
import com.digipay.cardmanagement.entity.TransactionLog;
import com.digipay.cardmanagement.service.TransactionLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DatabaseQueueReceiver {
    private final Logger logger = LoggerFactory.getLogger(DatabaseQueueReceiver.class);

    public final RabbitTemplate rabbitTemplate;
    public final TransactionLogService transactionLogService;

    public DatabaseQueueReceiver(RabbitTemplate rabbitTemplate, TransactionLogService transactionLogService) {
        this.rabbitTemplate = rabbitTemplate;
        this.transactionLogService = transactionLogService;
    }

    @RabbitListener(queues = Constants.DATABASE_QUEUE_NAME)//listen to binding2 topic(*.database.*)
    public void receive1(TransactionLog transactionLog) throws IOException, ClassNotFoundException {
      //  logger.info("message has been received" + transactionLogByte);
        logger.info("ready to save transaction base on {}" + transactionLog.toString());
        transactionLogService.save(transactionLog);

    }
}

