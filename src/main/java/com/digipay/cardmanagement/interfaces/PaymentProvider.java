package com.digipay.cardmanagement.interfaces;

import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentProvider {
  Boolean transferMoney(CardTransferRequestDto cardTransferRequestDto);

  String getName();
}
