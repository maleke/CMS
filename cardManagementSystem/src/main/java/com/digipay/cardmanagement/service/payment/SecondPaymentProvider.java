package com.digipay.cardmanagement.service.payment;

import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.dto.CardTransferResponseDto;
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

  private final PaymentServiceMock paymentServiceMock;

  public SecondPaymentProvider(PaymentServiceMock paymentServiceMock) {
    this.paymentServiceMock = paymentServiceMock;
  }

  @Override
  public Future<Boolean> transferMoney(CardTransferRequestDto cardTransferRequestDto) {
    // call webService
    try {
      CardTransferResponseDto cardTransferResponseDto =
          paymentServiceMock.transferMoneyMock(cardTransferRequestDto, secondPaymentProviderUrl);
      logger.info("result form secondPaymentProvider is:" + cardTransferResponseDto.getResult());
      return new AsyncResult<>(cardTransferResponseDto.getResult());
    } catch (Exception ex) {
      logger.error("An error has been occurred while calling webservice" + ex.getMessage());
      return new AsyncResult<>(false);
    }
  }

  @Override
  public String getName() {
    return "paymentProvider2";
  }
}
