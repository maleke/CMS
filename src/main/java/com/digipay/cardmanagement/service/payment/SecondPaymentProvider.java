package com.digipay.cardmanagement.service.payment;

import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.interfaces.PaymentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class SecondPaymentProvider implements PaymentProvider {

  private static final Logger logger = LoggerFactory.getLogger(SecondPaymentProvider.class);

  @Value("${info.url.secondPaymentProviderUrl}")
  private String secondPaymentProviderUrl;

  @Override
  public Future<Boolean> transferMoney(CardTransferRequestDto cardTransferRequestDto) {
    // call web service
    Boolean result =true;
    // Boolean result = restTemplate.postForObject(firstPaymentProviderUrl, cardTransferRequestDto, Boolean.class);
    logger.info("result is:" + result);
    return new AsyncResult<>(result);
  }

  @Override
  public String getName() {
    return "paymentProvider2";
  }
}
