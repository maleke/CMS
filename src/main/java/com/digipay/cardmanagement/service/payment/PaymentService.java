package com.digipay.cardmanagement.service.payment;

import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.interfaces.PaymentProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {
    private final Map<String, PaymentProvider> paymentProviderStrategy;

    public PaymentService(Map<String, PaymentProvider> paymentProviderStrategy) {
        this.paymentProviderStrategy = paymentProviderStrategy;
    }

    public Boolean transferMoney(CardTransferRequestDto cardTransferRequestDto) {
        String paymentProviderName =
                cardTransferRequestDto.getSource().startsWith("6037")
                        ? "paymentProvider1"
                        : "paymentProvider2";
        PaymentProvider paymentProvider = findPaymentProvider(paymentProviderName);
        return paymentProvider.transferMoney(cardTransferRequestDto);
    }

    private PaymentProvider findPaymentProvider(String paymentProviderName) {
        return paymentProviderStrategy.get(paymentProviderName);
    }
}
