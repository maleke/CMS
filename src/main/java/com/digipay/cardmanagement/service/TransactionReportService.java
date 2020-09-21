package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.common.search.SearchUtils;
import com.digipay.cardmanagement.common.search.SearchablePage;
import com.digipay.cardmanagement.dto.TransactionDto;
import com.digipay.cardmanagement.dto.TransactionLogDto;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.repository.TransactionReportRepository;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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

  public Page<TransactionLogDto> getTransactionReport(
      TransactionDto transactionDto, SearchablePage searchablePage) throws ServiceException {
    String startDateTime = transactionDto.getStartDateTime();
    String endDateTime = transactionDto.getEndDateTime();
    PageRequest pageRequest = SearchUtils.getPageRequest(searchablePage);
    //    Page<TransactionLogDto> transactionLogDtos =
    //        transactionRepository.findAllByTransactionDate(startDateTime, endDateTime,
    // pageRequest);
    List<TransactionLogDto> transactionLogDtos =
        transactionRepository.findAllByTransactionDate(startDateTime, endDateTime);
    return null;
  }
}
