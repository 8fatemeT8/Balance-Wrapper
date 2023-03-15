package com.refah.walletwrapper.model.dto;

import java.util.List;

public class BalanceDto {
    private List<ItemBalance> Items;
    private String Description = "transfer data";
    private String Currency = "IRR";
    private Integer TransactionType = 204;
    private Integer CashoutType = 0;
    private Integer ThirdPartyTransactionID = 0;

    public BalanceDto() {
    }

    public BalanceDto(List<ItemBalance> items) {
        Items = items;
    }

    public List<ItemBalance> getItems() {
        return Items;
    }

    public void setItems(List<ItemBalance> items) {
        Items = items;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public Integer getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(Integer transactionType) {
        TransactionType = transactionType;
    }

    public Integer getCashoutType() {
        return CashoutType;
    }

    public void setCashoutType(Integer cashoutType) {
        CashoutType = cashoutType;
    }

    public Integer getThirdPartyTransactionID() {
        return ThirdPartyTransactionID;
    }

    public void setThirdPartyTransactionID(Integer thirdPartyTransactionID) {
        ThirdPartyTransactionID = thirdPartyTransactionID;
    }
}
