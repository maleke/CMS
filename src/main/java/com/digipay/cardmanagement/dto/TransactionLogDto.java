package com.digipay.cardmanagement.dto;

import com.digipay.cardmanagement.enums.TransactionStatus;

import java.io.Serializable;

public class TransactionLogDto implements Serializable {

  private String source;
  private TransactionStatus staus;
  private Long count;

  public TransactionLogDto() {}

  public TransactionLogDto(String source) {
    this.source = source;
  }

  public TransactionLogDto(String source, TransactionStatus staus) {
    this.source = source;
    this.staus = staus;
  }

  public TransactionLogDto(String source, TransactionStatus staus, Long count) {
    this.source = source;
    this.staus = staus;
    this.count = count;
  }
  // region getter and setter

  public String getSource() {
    return source;
  }

  public TransactionLogDto setSource(String source) {
    this.source = source;
    return this;
  }

  public TransactionStatus getStaus() {
    return staus;
  }

  public TransactionLogDto setStaus(TransactionStatus staus) {
    this.staus = staus;
    return this;
  }

  public Long getCount() {
    return count;
  }

  public TransactionLogDto setCount(Long count) {
    this.count = count;
    return this;
  }

  // endregion

  @Override
  public String toString() {
    return "TransactionLogDto{"
        + "cardNumber='"
        + source
        + '\''
        + ", staus='"
        + staus
        + '\''
        + ", count="
        + count
        + '}';
  }
}
