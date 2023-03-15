package com.refah.walletwrapper.model.entity;


import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Audited
@Entity
@Table(name = "users")
public class User extends BaseModel {

    private String firstName;

    private String lastName;

    private String nationalCode;

    @Column(nullable = false)
    private String mobileNumber;

    private String email;

    private String registerTransactionId;

    private String registerErrorCode;

    @Column(columnDefinition = "TEXT")
    private String errorResponseValidation;

    @Column(columnDefinition = "TEXT")
    private String registerResponse;

    private String registerReturnValue;

    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "excel_detail_id")
    private ExcelDetail excelDetail;


    public User() {
    }

    public User(String firstName, String lastName, String nationalCode,
                String mobileNumber, String email, Wallet wallet, ExcelDetail excelDetail, Date createdDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        setNationalCode(nationalCode);
        setMobileNumber(mobileNumber);
        setEmail(email);
        this.wallet = wallet;
        this.excelDetail = excelDetail;
        this.setCreatedDate(createdDate);
    }

    public User(Integer id, Date createdDate, String firstName, String lastName,
                String nationalCode, String mobileNumber, String email, Wallet wallet, ExcelDetail excelDetail) {
        super(id, createdDate);
        this.firstName = firstName;
        this.lastName = lastName;
        setNationalCode(nationalCode);
        setMobileNumber(mobileNumber);
        setEmail(email);
        this.wallet = wallet;
        this.excelDetail = excelDetail;
    }


    public String getRegisterReturnValue() {
        return registerReturnValue;
    }

    public String getErrorResponseValidation() {
        return errorResponseValidation;
    }

    public void setErrorResponseValidation(String registerResponseValidation) {
        this.errorResponseValidation = registerResponseValidation;
    }

    public String getRegisterErrorCode() {
        return registerErrorCode;
    }

    public void setRegisterErrorCode(String registerErrorCode) {
        this.registerErrorCode = registerErrorCode;
    }

    public String getRegisterResponse() {
        return registerResponse;
    }

    public void setRegisterResponse(String registerResponse) {
        this.registerResponse = registerResponse;
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
        if (nationalCode.length() < 10)
            while (11 - (nationalCode.length()) != 0) nationalCode = "0" + nationalCode;
        this.nationalCode = nationalCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        if (mobileNumber.length() < 11)
            while (11 - (mobileNumber.length()) != 0) mobileNumber = "0" + mobileNumber;
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty() || email.equals("\n") || email.equals(""))
            email = "example@gmail.com";
        this.email = email;
    }

    public String getRegisterTransactionId() {
        return registerTransactionId;
    }

    public void setRegisterTransactionId(String registerTransactionId) {
        this.registerTransactionId = registerTransactionId;
    }

    public void setRegisterReturnValue(String registerTransactionResponseCode) {
        this.registerReturnValue = registerTransactionResponseCode;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public ExcelDetail getExcelDetail() {
        return excelDetail;
    }

    public void setExcelDetail(ExcelDetail excelDetail) {
        this.excelDetail = excelDetail;
    }
}
