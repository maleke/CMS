package com.digipay.cardmanagement.config;

import com.digipay.cardmanagement.common.Constants;
import com.digipay.cardmanagement.service.TransactionReportService;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.scanner.Constant;

@Configuration
public class AmqpConfig {
    private final ConnectionFactory connectionFactory;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(TransactionReportService.class);

    public AmqpConfig(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(Constants.EXCHANGE_NAME);
    }

    @Bean
    Queue notificationFetchQueue() {
        return QueueBuilder.durable(Constants.INCOMING_QUEUE_NAME)
                .deadLetterExchange("")
                .deadLetterRoutingKey(Constants.DEAD_LETTER_QUEUE_NAME)
                .build();
    }

    @Bean
    Queue waitQueue() {
        return QueueBuilder.durable(Constants.WAIT_QUEUE)
                .deadLetterExchange("")
                .deadLetterRoutingKey(Constants.NOTIFICATION_ROUTING_KEY_NAME)
                .ttl(10000)
                .build();
    }
    @Bean
    Binding waitBinding(){
        return BindingBuilder.bind(waitQueue()).to(exchange()).with(Constants.WAIT_QUEUE);
    }
    @Bean
    Binding binding() {
        return BindingBuilder.bind(notificationFetchQueue())
                .to(exchange()).with(Constants.NOTIFICATION_ROUTING_KEY_NAME);
    }

    @Bean
    public Queue databaseFetchQueue() {
        return QueueBuilder.durable(Constants.DATABASE_QUEUE_NAME)
                .withArgument("database-dead-letter-exchange", "")
                .withArgument("database-dead-letter-routing-key", Constants.DATABASE_DEAD_LETTER_QUEUE_NAME)
                .build();
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(databaseFetchQueue())
                .to(exchange())
                .with(Constants.DATABASE_ROUTING_KEY_NAME);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(Constants.DEAD_LETTER_QUEUE_NAME).build();
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }



    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // Message sending failure returned to the queue, publisher returns needs to be configured for the profile: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        // Message return, the configuration file needs to configure publisher returns: true
        // The ReturnCallback interface is used to implement the callback when a message is sent to the RabbitMQ switch, but there is no corresponding queue bound to the switch.
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            logger.error("Message sending failed:No corresponding queue bound to switch");
        });

        // Message confirmation, the configuration file needs to configure publisher confirms: true
        // The ConfirmCallback interface is used to receive ack callbacks after messages are sent to the RabbitMQ exchanger.
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                logger.info("Message sent successfully:Message sent to RabbitMQ exchanger");
            } else {
                logger.error("Message sending failed:Message not sent to RabbitMQ exchanger");
            }
        });

        return rabbitTemplate;
    }
}
