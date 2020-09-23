package com.digipay.cardmanagement.web.rest.integrationtest;

import com.digipay.cardmanagement.common.utility.SerializeUtility;
import com.digipay.cardmanagement.dto.CardTransferRequestDto;
import com.digipay.cardmanagement.dto.CardTransferResponseDto;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
// @ActiveProfiles({"dev", "docker"})
public class FirstPaymentServiceTest {
  private static final Logger logger = LoggerFactory.getLogger(FirstPaymentServiceTest.class);

  @Value("${info.url.firstPaymentProvider}")
  private String firstPaymentProviderUrl;

  @Test
  void transferMoneyTest() throws IOException {
    WireMockServer wireMockServer = new WireMockServer(40020);
    CardTransferRequestDto cardTransferRequestDto = new CardTransferRequestDto();
    cardTransferRequestDto
        .setSource("5022291087739221")
        .setDest("6037291087739221")
        .setCvv2("224")
        .setPin("765876")
        .setAmount(5000L);
    CardTransferResponseDto cardTransferResponseDto = new CardTransferResponseDto().setResult(true);

    StubMapping stubMapping =
        wireMockServer.stubFor(
            post(urlPathMatching("/payments/transfer"))
                .withRequestBody(
                    containing(
                        "\" "
                            + cardTransferRequestDto.getSource()
                            + "\":"
                            + "\" "
                            + cardTransferRequestDto.getDest()
                            + "\":"
                            + "\" "
                            + cardTransferRequestDto.getPin()
                            + "\":"
                            + "\" "
                            + cardTransferRequestDto.getCvv2()
                            + "\":"
                            + "\" "
                            + cardTransferRequestDto.getExpDate()
                            + "\":"
                            + "\" "
                            + cardTransferRequestDto.getAmount()
                            + "\":"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(SerializeUtility.mapToJson(cardTransferResponseDto))));
    CardTransferResponseDto result =
        SerializeUtility.mapFromJson(
            stubMapping.getResponse().getBody(), CardTransferResponseDto.class);
    assertEquals(result.getResult(), true, "Unexpected result");
  }
}
