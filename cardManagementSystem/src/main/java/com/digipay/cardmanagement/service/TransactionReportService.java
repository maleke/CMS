package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.common.search.SearchUtils;
import com.digipay.cardmanagement.common.search.SearchablePage;
import com.digipay.cardmanagement.dto.TransactionDto;
import com.digipay.cardmanagement.dto.TransactionLogDto;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.repository.TransactionReportRepository;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionReportService {
  private final org.slf4j.Logger logger = LoggerFactory.getLogger(TransactionReportService.class);

  private final TransactionReportRepository transactionRepository;

  public TransactionReportService(TransactionReportRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<TransactionLogDto> getTransactionReport(
      TransactionDto transactionDto, SearchablePage searchablePage) throws ServiceException {
    String startDateTime = transactionDto.getStartDateTime();
    String endDateTime = transactionDto.getEndDateTime();
    PageRequest pageRequest = SearchUtils.getPageRequest(searchablePage);
    logger.info("Ready to get transaction report from page " + searchablePage.getPage() +
            "with each page conatins " + searchablePage.getTotal() + "element");
    List<TransactionLogDto> transactionReport = transactionRepository.getTransactionReport(startDateTime, endDateTime, pageRequest);
    return transactionReport;
  }
}
