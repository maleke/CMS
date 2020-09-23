package com.digipay.NotificationService.common;

import java.io.Serializable;

public class ProviderMessageResponseDto implements Serializable {
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public ProviderMessageResponseDto setResult(Boolean result) {
        this.result = result;
        return this;
    }
}
