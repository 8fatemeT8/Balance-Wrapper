package com.refah.walletwrapper.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Balance {
    @JsonProperty("Mobile")
    public String mobile;
    @JsonProperty("AccountNumber")
    public String accountNumber;
    @JsonProperty("Balance")
    public int balance;
    @JsonProperty("AvailableBalance")
    public int availableBalance;
    @JsonProperty("AccountKind")
    public int accountKind;
    @JsonProperty("AccountKindTitle")
    public String accountKindTitle;
    @JsonProperty("TenantName")
    public String tenantName;
}
