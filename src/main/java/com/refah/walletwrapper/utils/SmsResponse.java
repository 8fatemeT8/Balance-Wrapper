package com.refah.walletwrapper.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmsResponse {

    @JsonProperty("ERROR_CODE")
    public int errorCode;
    @JsonProperty("ERROR_MSG")
    public String errorMessage;


    public SmsResponse() {
    }

    public SmsResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
