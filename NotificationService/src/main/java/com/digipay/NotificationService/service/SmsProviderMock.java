package com.digipay.NotificationService.service;

import com.digipay.NotificationService.common.ProviderMessageRequestDTO;
import com.digipay.NotificationService.common.ProviderMessageResponseDto;
import com.digipay.NotificationService.common.SerializeUtility;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@Service
public class SmsProviderMock {
  private static final Logger logger = LoggerFactory.getLogger(SmsProviderMock.class);

  @Value("${info.url.smsProvider}")
  private String smsProviderUrl;

  ProviderMessageResponseDto sendMessage(ProviderMessageRequestDTO providerMessageRequestDTO) throws IOException {
    ProviderMessageResponseDto providerMessageResponseDto = new
            ProviderMessageResponseDto().setResult(true);
    WireMockServer wireMockServer = new WireMockServer(40020);
    StubMapping stubMapping =
        wireMockServer.stubFor(
            post(urlPathMatching("/messages/send-sms"))
                .withRequestBody(
                    containing(
                        "\" "
                            + providerMessageRequestDTO.getMobileNo()
                            + "\":"
                            + "\" "
                            + providerMessageRequestDTO.getMessage()
                            + "\":"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(SerializeUtility.mapToJson(providerMessageResponseDto))));
    ProviderMessageResponseDto result =
        SerializeUtility.mapFromJson(
            stubMapping.getResponse().getBody(), ProviderMessageResponseDto.class);
    return providerMessageResponseDto;
  }
}
