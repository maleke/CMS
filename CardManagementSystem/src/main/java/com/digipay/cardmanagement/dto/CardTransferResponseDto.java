package com.digipay.cardmanagement.dto;

import java.io.Serializable;

public class CardTransferResponseDto implements Serializable {
  private Boolean result;

  public Boolean getResult() {
    return result;
  }

  public CardTransferResponseDto setResult(Boolean result) {
    this.result = result;
    return this;
  }
}
