package com.digipay.cardmanagement.service.payment;

import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.interfaces.PaymentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class FirstPaymentProvider implements PaymentProvider {
  private static final Logger logger = LoggerFactory.getLogger(FirstPaymentProvider.class);
  private final RestTemplate restTemplate;

  @Value("${info.url.firstPaymentProvider}")
  private String firstPaymentProviderUrl;

  public FirstPaymentProvider(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Future<Boolean> transferMoney(CardTransferRequestDto cardTransferRequestDto) {
    // call webService
    Boolean result =true;
   // Boolean result = restTemplate.postForObject(firstPaymentProviderUrl, cardTransferRequestDto, Boolean.class);
    logger.info("result is:" + result.toString());
    return new AsyncResult<>(result);
  }

  @Override
  public String getName() {
    return "paymentProvider1";
  }
}
