package com.refah.walletwrapper.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponseDto {

    @JsonProperty("StatusCode")
    private int statusCode;
    @JsonProperty("IsSuccess")
    private boolean isSuccess;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("MessageEn")
    private String messageEn;
    @JsonProperty("ValidationErrors")
    private String validationErrors;
    @JsonProperty("ErrorCode")
    private String errorCode;
    @JsonProperty("Data")
    private RegisterDataDto data;

    public RegisterResponseDto() {
    }

    public RegisterResponseDto(int statusCode, boolean isSuccess, String message, String messageEn,
                               String validationErrors, String errorCode, RegisterDataDto data) {
        this.statusCode = statusCode;
        this.isSuccess = isSuccess;
        this.message = message;
        this.messageEn = messageEn;
        this.validationErrors = validationErrors;
        this.errorCode = errorCode;
        this.data = data;
    }

    public RegisterDataDto getData() {
        return data;
    }

    public void setData(RegisterDataDto data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public void setMessageEn(String messageEn) {
        this.messageEn = messageEn;
    }

    public String getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(String validationErrors) {
        this.validationErrors = validationErrors;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public static class RegisterDataDto {
        @JsonProperty("ID")
        private Long id;
        @JsonProperty("Number")
        private String number;
        @JsonProperty("MobileNumber")
        private String mobileNumber;
        @JsonProperty("AccountKind")
        private long accountKind;
        @JsonProperty("TenantID")
        private Long tenantId;
        @JsonProperty("Balance")
        private Long balance;
        @JsonProperty("ReturnValue")
        private Long returnValue;


        public RegisterDataDto() {
        }

        public RegisterDataDto(Long id, String number, String mobileNumber,
                               long accountKind, Long tenantId, Long balance, Long returnValue) {
            this.id = id;
            this.number = number;
            this.mobileNumber = mobileNumber;
            this.accountKind = accountKind;
            this.tenantId = tenantId;
            this.balance = balance;
            this.returnValue = returnValue;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public long getAccountKind() {
            return accountKind;
        }

        public void setAccountKind(long accountKind) {
            this.accountKind = accountKind;
        }

        public Long getTenantId() {
            return tenantId;
        }

        public void setTenantId(Long tenantId) {
            this.tenantId = tenantId;
        }

        public Long getBalance() {
            return balance;
        }

        public void setBalance(Long balance) {
            this.balance = balance;
        }

        public Long getReturnValue() {
            return returnValue;
        }

        public void setReturnValue(Long returnValue) {
            this.returnValue = returnValue;
        }
    }
}
