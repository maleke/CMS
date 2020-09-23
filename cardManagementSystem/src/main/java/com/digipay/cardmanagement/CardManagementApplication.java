package com.digipay.cardmanagement;

import com.digipay.cardmanagement.interfaces.PaymentProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableCircuitBreaker
public class CardManagementApplication {

  @Value("${info.timeout.cardManagementToPaymentProvider}")
  private String cardManagementToPaymentProvider;

  public static void main(String[] args) {
    SpringApplication.run(CardManagementApplication.class, args);
  }

  @Bean
  Map<String, PaymentProvider> paymentProviderStrategy(List<PaymentProvider> paymentProviders) {
    Map<String, PaymentProvider> paymentProviderStrategy = new HashMap<>();
    paymentProviders.forEach(
        paymentProvider -> paymentProviderStrategy.put(paymentProvider.getName(), paymentProvider));
    return paymentProviderStrategy;
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
        .setConnectTimeout(Duration.ofSeconds(Long.parseLong(cardManagementToPaymentProvider)))
        .setReadTimeout(Duration.ofSeconds(Long.parseLong(cardManagementToPaymentProvider)))
        .build();
  }
}
