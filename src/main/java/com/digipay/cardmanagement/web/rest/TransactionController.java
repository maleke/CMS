package com.digipay.cardmanagement.web.rest;

import com.digipay.cardmanagement.common.search.SearchablePage;
import com.digipay.cardmanagement.dto.TransactionDto;
import com.digipay.cardmanagement.dto.TransactionLogDto;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.service.TransactionReportService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class TransactionController {
  private final TransactionReportService transactionService;

  public TransactionController(TransactionReportService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping("/transactions")
  ResponseEntity<Page<TransactionLogDto>> getTransactionReport(
      @RequestBody TransactionDto transactionDto,
      @RequestParam int page,
      @RequestParam int total,
      @RequestParam(required = false) String order,
      @RequestParam(required = false) String direction)
      throws ServiceException {
    Map<String, String> query = new HashMap<>();
    SearchablePage searchablePage =
        new SearchablePage()
            .setPage(page)
            .setOrder(order)
            .setTotal(total)
            .setDirection(direction)
            .setFilter(query);
    Page<TransactionLogDto> result =
        transactionService.getTransactionReport(transactionDto, searchablePage);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
