package com.refah.walletwrapper.utils.report.dtos;

import com.refah.walletwrapper.model.entity.Transaction;
import com.refah.walletwrapper.model.entity.Wallet;

import java.util.Collections;
import java.util.List;

public class UserReportDto {
    private String firstName;

    private String lastName;

    private String nationalCode;

    private String mobileNumber;

    private String registered = "رجیستر نشده";

    private String companyName;

    private String walletBalance;

    private String transaction1;
    private String transaction1Status;

    private String transaction1Date;

    private String transaction2;
    private String transaction2Status;

    private String transaction2Date;

    private String transaction3;
    private String transaction3Status;

    private String transaction3Date;


    public UserReportDto(String firstName, String lastName, String nationalCode, String mobileNumber,
                         String registered, String companyName, String walletBalance, Wallet wallet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.mobileNumber = mobileNumber;
        this.registered = registered;
        this.companyName = companyName;
        this.walletBalance = walletBalance;
        List<Transaction> sorted = wallet.getTransactions();
        sorted.sort(Collections.reverseOrder());

        if (sorted.size() < 3) {
            switch (sorted.size()) {
                case 2:
                    this.transaction2 = sorted.get(1).getAmount();
                    this.transaction2Status = sorted.get(1).isFinished() ? "موفق" : "ناموفق";
                    this.transaction2Date = sorted.get(1).getCreatedDate().toString();
                case 1:
                    this.transaction1 = sorted.get(0).getAmount();
                    this.transaction1Status = sorted.get(0).isFinished() ? "موفق" : "ناموفق";
                    this.transaction1Date = sorted.get(0).getCreatedDate().toString();
                default:
                    break;
            }
        } else {
            this.transaction1 = sorted.get(0).getAmount();
            this.transaction1Status = sorted.get(0).isFinished() ? "موفق" : "ناموفق";
            this.transaction1Date = sorted.get(0).getCreatedDate().toString();
            this.transaction2 = sorted.get(1).getAmount();
            this.transaction2Status = sorted.get(1).isFinished() ? "موفق" : "ناموفق";
            this.transaction2Date = sorted.get(1).getCreatedDate().toString();
            this.transaction3 = sorted.get(2).getAmount();
            this.transaction3Status = sorted.get(2).isFinished() ? "موفق" : "ناموفق";
            this.transaction3Date = sorted.get(2).getCreatedDate().toString();
        }
    }

    public UserReportDto() {
    }

    public String getTransaction2Date() {
        return transaction2Date;
    }

    public void setTransaction2Date(String transaction2Date) {
        this.transaction2Date = transaction2Date;
    }

    public String getTransaction3Date() {
        return transaction3Date;
    }

    public void setTransaction3Date(String transaction3Date) {
        this.transaction3Date = transaction3Date;
    }

    public String getTransaction1Date() {
        return transaction1Date;
    }

    public void setTransaction1Date(String transaction1Date) {
        this.transaction1Date = transaction1Date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(String walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getTransaction1() {
        return transaction1;
    }

    public void setTransaction1(String transaction1) {
        this.transaction1 = transaction1;
    }

    public String getTransaction1Status() {
        return transaction1Status;
    }

    public void setTransaction1Status(String transaction1Status) {
        this.transaction1Status = transaction1Status;
    }

    public String getTransaction2() {
        return transaction2;
    }

    public void setTransaction2(String transaction2) {
        this.transaction2 = transaction2;
    }

    public String getTransaction2Status() {
        return transaction2Status;
    }

    public void setTransaction2Status(String transaction2Status) {
        this.transaction2Status = transaction2Status;
    }

    public String getTransaction3() {
        return transaction3;
    }

    public void setTransaction3(String transaction3) {
        this.transaction3 = transaction3;
    }

    public String getTransaction3Status() {
        return transaction3Status;
    }

    public void setTransaction3Status(String transaction3Status) {
        this.transaction3Status = transaction3Status;
    }
}
