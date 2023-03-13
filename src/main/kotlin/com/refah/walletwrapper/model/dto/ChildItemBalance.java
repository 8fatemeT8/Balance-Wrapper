package com.refah.walletwrapper.model.dto;

public class ChildItemBalance {
    private Long TenantID;
    private Integer AccountKind = 1;
    private String MobileNumber;
    private String AccountNumberCode;

    public ChildItemBalance() {
    }

    public ChildItemBalance(Long tenantID, String mobileNumber, String accountNumberCode) {
        TenantID = tenantID;
        MobileNumber = mobileNumber;
        AccountNumberCode = accountNumberCode;
    }

    public Long getTenantID() {
        return TenantID;
    }

    public void setTenantID(Long tenantID) {
        TenantID = tenantID;
    }

    public Integer getAccountKind() {
        return AccountKind;
    }

    public void setAccountKind(Integer accountKind) {
        AccountKind = accountKind;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getAccountNumberCode() {
        return AccountNumberCode;
    }

    public void setAccountNumberCode(String accountNumberCode) {
        AccountNumberCode = accountNumberCode;
    }
}
