package com.refah.walletwrapper.model.dto;

public class UserRegisterDto {

    private String MobileNumber;
    private String EmailAddress;
    private String NationalCode;
    private String NameFrst;
    private String NameLast;
    private String PIN = "1234";
    private Integer WalletAmount = 0;
    private Integer PointAmount = 0;
    private Integer CustomerKind = 1;
    private Integer SettlementType = 0;
    private Integer UserGroupId = 1;
    private Integer ContractType = 1;
    private Integer SIMType = 1;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String mobileNumber, String emailAddress, String nationalCode,
                           String nameFrst, String nameLast) {
        MobileNumber = mobileNumber;
        EmailAddress = emailAddress;
        NationalCode = nationalCode;
        NameFrst = nameFrst;
        NameLast = nameLast;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getNationalCode() {
        return NationalCode;
    }

    public void setNationalCode(String nationalCode) {
        NationalCode = nationalCode;
    }

    public String getNameFrst() {
        return NameFrst;
    }

    public void setNameFrst(String nameFrst) {
        NameFrst = nameFrst;
    }

    public String getNameLast() {
        return NameLast;
    }

    public void setNameLast(String nameLast) {
        NameLast = nameLast;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public Integer getWalletAmount() {
        return WalletAmount;
    }

    public void setWalletAmount(Integer walletAmount) {
        WalletAmount = walletAmount;
    }

    public Integer getPointAmount() {
        return PointAmount;
    }

    public void setPointAmount(Integer pointAmount) {
        PointAmount = pointAmount;
    }

    public Integer getCustomerKind() {
        return CustomerKind;
    }

    public void setCustomerKind(Integer customerKind) {
        CustomerKind = customerKind;
    }

    public Integer getSettlementType() {
        return SettlementType;
    }

    public void setSettlementType(Integer settlementType) {
        SettlementType = settlementType;
    }

    public Integer getUserGroupId() {
        return UserGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        UserGroupId = userGroupId;
    }

    public Integer getContractType() {
        return ContractType;
    }

    public void setContractType(Integer contractType) {
        ContractType = contractType;
    }

    public Integer getSIMType() {
        return SIMType;
    }

    public void setSIMType(Integer SIMType) {
        this.SIMType = SIMType;
    }
}
