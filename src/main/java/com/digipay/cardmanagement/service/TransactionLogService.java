package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.entity.TransactionLog;
import com.digipay.cardmanagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionLogService {
    private final TransactionRepository transactionRepository;

    public TransactionLogService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void save(TransactionLog transactionLog){
        transactionRepository.save(transactionLog);
    }
}
