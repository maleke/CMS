package com.digipay.cardmanagement.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CardTransferRequestDto implements Serializable {

  @NotNull(message = "{null.source}")
  @Size(min = 16, max = 19, message = "{size.source}")
  private String source;

  @NotNull(message = "{null.amount}")
  @Digits(integer = 12, fraction = 0, message = "{digit.amount}")
  private Long amount;

  @NotNull(message = "{null.dest}")
  @Size(min = 16, max = 19, message = "{size.dest}")
  private String dest;

  @NotNull(message = "{null.cvv2}")
  @Size(min = 3, message = "{size.cvv2}")
  private String cvv2;

  @NotNull(message = "{null.expDate}")
  @Size(max = 4, message = "{size.expDate}")
  private String expDate;

  @NotNull(message = "{null.pin}")
  @Size(min = 5, message = "{size.pin}")
  private String pin;

  public CardTransferRequestDto() {}

  // region getterAndSetter

  public String getSource() {
    return source;
  }

  public CardTransferRequestDto setSource(String source) {
    this.source = source;
    return this;
  }

  public Long getAmount() {
    return amount;
  }

  public CardTransferRequestDto setAmount(Long amount) {
    this.amount = amount;
    return this;
  }

  public String getDest() {
    return dest;
  }

  public CardTransferRequestDto setDest(String dest) {
    this.dest = dest;
    return this;
  }

  public String getCvv2() {
    return cvv2;
  }

  public CardTransferRequestDto setCvv2(String cvv2) {
    this.cvv2 = cvv2;
    return this;
  }

  public String getExpDate() {
    return expDate;
  }

  public CardTransferRequestDto setExpDate(String expDate) {
    this.expDate = expDate;
    return this;
  }

  public String getPin() {
    return pin;
  }

  public CardTransferRequestDto setPin(String pin) {
    this.pin = pin;
    return this;
  }

  // endregion

  @Override
  public String toString() {
    return "CardTransferRequestDto{"
        + "source='"
        + source
        + '\''
        + ", amount="
        + amount
        + ", dest='"
        + dest
        + '\''
        + ", cvv2='"
        + cvv2
        + '\''
        + ", expDate='"
        + expDate
        + '\''
        + ", pin='"
        + pin
        + '\''
        + '}';
  }
}
