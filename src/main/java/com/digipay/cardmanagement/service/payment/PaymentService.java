package com.digipay.cardmanagement.service.payment;

import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.interfaces.PaymentProvider;
import com.digipay.cardmanagement.service.CardService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PaymentService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CardService.class);
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
        try {
            return paymentProvider.transferMoney(cardTransferRequestDto).get();
        } catch (ResourceAccessException e) {
            if (e.getRootCause() instanceof SocketTimeoutException) {
                logger.error("SocketTimeoutException in transfer money for: " + cardTransferRequestDto.toString()
                        , Arrays.toString(e.getStackTrace()));
                return (false);
            } else {
                logger.error("Unable to get the Provider URL for registerOtp service for " + cardTransferRequestDto.toString(), Arrays.toString(e.getStackTrace()));
                return false;
            }
        } catch (Exception e) {
            logger.error("An exception has been occurred while calling url");
        }
        return false;
    }

    private PaymentProvider findPaymentProvider(String paymentProviderName) {
        return paymentProviderStrategy.get(paymentProviderName);
    }
}
