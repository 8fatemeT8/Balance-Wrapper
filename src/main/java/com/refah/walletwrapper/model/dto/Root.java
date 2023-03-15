package com.refah.walletwrapper.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
    @JsonProperty("StatusCode")
    public int statusCode;
    @JsonProperty("IsSuccess")
    public boolean isSuccess;
    @JsonProperty("Message")
    public String message;
    @JsonProperty("MessageEn")
    public String messageEn;
    @JsonProperty("Data")
    public Data data;
    @JsonProperty("ValidationErrors")
    public Object validationErrors;
    @JsonProperty("ErrorCode")
    public int errorCode;
}
