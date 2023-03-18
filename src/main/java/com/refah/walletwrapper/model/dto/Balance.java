package com.refah.walletwrapper.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Balance {
    @JsonProperty("Mobile")
    public String mobile;
    @JsonProperty("AccountNumber")
    public String accountNumber;
    @JsonProperty("Balance")
    public Long balance;
    @JsonProperty("AvailableBalance")
    public Long availableBalance;
    @JsonProperty("AccountKind")
    public Long accountKind;
    @JsonProperty("AccountKindTitle")
    public String accountKindTitle;
    @JsonProperty("TenantName")
    public String tenantName;
}
