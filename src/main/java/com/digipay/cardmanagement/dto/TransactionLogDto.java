package com.digipay.cardmanagement.dto;

import java.io.Serializable;

public class TransactionLogDto implements Serializable {

  private String source;
  private Long successStausCount;
  private Long failStausCount;



  public TransactionLogDto() {}

  public TransactionLogDto(String source) {
    this.source = source;
  }

  public TransactionLogDto(String source, Long successStausCount, Long failStausCount) {
    this.source = source;
    this.successStausCount = successStausCount;
    this.failStausCount = failStausCount;
  }

  // region getter and setter

  public String getSource() {
    return source;
  }

  public TransactionLogDto setSource(String source) {
    this.source = source;
    return this;
  }

  public Long getSuccessStausCount() {
    return successStausCount;
  }

  public TransactionLogDto setSuccessStausCount(Long successStausCount) {
    this.successStausCount = successStausCount;
    return this;
  }

  public Long getFailStausCount() {
    return failStausCount;
  }

  public TransactionLogDto setFailStausCount(Long failStausCount) {
    this.failStausCount = failStausCount;
    return this;
  }


  // endregion


  @Override
  public String toString() {
    return "TransactionLogDto{" +
            "source='" + source + '\'' +
            ", successStausCount=" + successStausCount +
            ", failStausCount=" + failStausCount +
            '}';
  }
}
