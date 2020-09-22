package com.digipay.cardmanagement.interfaces;

import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public interface PaymentProvider {
  Future<Boolean> transferMoney(CardTransferRequestDto cardTransferRequestDto);

  String getName();
}
