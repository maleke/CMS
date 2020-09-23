package com.digipay.cardmanagement.web.rest;

import com.digipay.cardmanagement.dto.TransactionDto;
import com.digipay.cardmanagement.utility.JsonUtility;
import org.junit.jupiter.api.Test;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean TransactionService transactionService;

  @Test
  void transactionReportTest() throws Exception {
    TransactionDto transactionDto =
        new TransactionDto().setStartDateTime("20200922081940").setEndDateTime("20200922082346");

    // execute
    String transaction = JsonUtility.mapToJson(transactionDto);
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/transactions?page=1&total=2")
            .accept(MediaType.APPLICATION_JSON)
            .content(transaction)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8");

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    // verify
    int status = result.getResponse().getStatus();
    assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

    // verify that service method was called once

  }
}
