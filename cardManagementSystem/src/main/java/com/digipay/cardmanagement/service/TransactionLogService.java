package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.entity.TransactionLog;
import com.digipay.cardmanagement.repository.TransactionReportRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionLogService {
  private final org.slf4j.Logger logger = LoggerFactory.getLogger(TransactionLogService.class);

  private final TransactionReportRepository transactionRepository;

  public TransactionLogService(TransactionReportRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public void save(TransactionLog transactionLog) {
    logger.info("Ready to save transaction base on {}", transactionLog.toString());
    transactionRepository.save(transactionLog);
  }
}
