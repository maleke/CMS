package com.digipay.cardmanagement.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProviderMessageRequestDTO implements Serializable {

  private String mobileNo;
  private String message;

  public ProviderMessageRequestDTO(
      @JsonProperty("mobileNo") String mobileNo, @JsonProperty("message") String message) {
    this.mobileNo = mobileNo;
    this.message = message;
  }
  // region getter and setter

  public String getMobileNo() {
    return mobileNo;
  }

  public ProviderMessageRequestDTO setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public ProviderMessageRequestDTO setMessage(String message) {
    this.message = message;
    return this;
  }
  // endregion

  // region toString

  @Override
  public String toString() {
    return "ProviderMessageRequestDTO{"
        + "mobileNo='"
        + mobileNo
        + '\''
        + ", message='"
        + message
        + '\''
        + '}';
  }

  // endregion
}
