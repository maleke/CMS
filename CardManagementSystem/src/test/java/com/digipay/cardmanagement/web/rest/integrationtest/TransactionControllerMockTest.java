package com.digipay.cardmanagement.web.rest.integrationtest;

import com.digipay.cardmanagement.common.search.SearchablePage;
import com.digipay.cardmanagement.dto.TransactionDto;
import com.digipay.cardmanagement.dto.TransactionLogDto;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.repository.TransactionReportRepository;
import com.digipay.cardmanagement.service.TransactionReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionControllerMockTest {

    @MockBean
    TransactionReportRepository transactionRepository;

    @Autowired
    TransactionReportService transactionReportService;
    @Test
    void testFindCardsByUserId() throws ServiceException {
        TransactionDto transactionDto = new TransactionDto()
                .setStartDateTime("20200922081940")
                .setEndDateTime("20200922082346");
        SearchablePage searchablePage = new SearchablePage()
                .setPage(0)
                .setTotal(10);
        TransactionLogDto transactionLogDto1 = new TransactionLogDto()
                .setSource("6104234556784986")
                .setSuccessStausCount(1L);
        TransactionLogDto transactionLogDto2 = new TransactionLogDto()
                .setSource("6104234556784986")
                .setFailStausCount(1L);
        List<TransactionLogDto> transactionLogDtos = new ArrayList<>();
        transactionLogDtos.add(transactionLogDto1);
        transactionLogDtos.add(transactionLogDto2);
        when(transactionReportService.getTransactionReport(transactionDto, searchablePage)).thenReturn(transactionLogDtos);
        assertEquals(transactionReportService.getTransactionReport(transactionDto, searchablePage), transactionLogDtos,"Unexpected result");
    }
}
