package com.digipay.cardmanagement.service.payment;

import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.interfaces.PaymentProvider;
import org.springframework.stereotype.Service;

@Service
public class PaymentProvider2 implements PaymentProvider {

  @Override
  public Boolean transferMoney(CardTransferRequestDto cardTransferRequestDto) {
    return true;
  }

  @Override
  public String getName() {
    return "paymentProvider2";
  }
}
