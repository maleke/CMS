package com.digipay.cardmanagement.config;

import com.digipay.cardmanagement.common.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
  private final ConnectionFactory connectionFactory;

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
        .withArgument("x-dead-letter-exchange", "")
        .withArgument("x-dead-letter-routing-key", Constants.DEAD_LETTER_QUEUE_NAME)
        .build();
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
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
    return rabbitTemplate;
  }
}
