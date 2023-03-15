package com.refah.walletwrapper.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Data {
    @JsonProperty("ReceiptID")
    public String receiptID;
    @JsonProperty("Description")
    public Object description;
    @JsonProperty("Balances")
    public ArrayList<Balance> balances;
}
