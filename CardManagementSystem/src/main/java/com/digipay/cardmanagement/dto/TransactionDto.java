package com.digipay.cardmanagement.dto;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class TransactionDto implements Serializable {
  @Size(min = 14, max = 14, message = "{size.startDateTime}")
  private String startDateTime;

  @Size(min = 14, max = 14, message = "{size.endDateTime}")
  private String endDateTime;

  public TransactionDto() {}

  public String getStartDateTime() {
    return startDateTime;
  }

  public TransactionDto setStartDateTime(String startDateTime) {
    this.startDateTime = startDateTime;
    return this;
  }

  public String getEndDateTime() {
    return endDateTime;
  }

  public TransactionDto setEndDateTime(String endDateTime) {
    this.endDateTime = endDateTime;
    return this;
  }
}
