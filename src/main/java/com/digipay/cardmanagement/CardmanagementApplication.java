package com.digipay.cardmanagement;

import com.digipay.cardmanagement.interfaces.PaymentProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableCircuitBreaker
public class CardmanagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(CardmanagementApplication.class, args);
  }

  @Bean
  Map<String, PaymentProvider> paymentProviderStrategy(List<PaymentProvider> paymentProviders) {
    Map<String, PaymentProvider> paymentProviderStrategy = new HashMap<>();
    paymentProviders.forEach(
        paymentProvider -> paymentProviderStrategy.put(paymentProvider.getName(), paymentProvider));
    return paymentProviderStrategy;
  }
}
