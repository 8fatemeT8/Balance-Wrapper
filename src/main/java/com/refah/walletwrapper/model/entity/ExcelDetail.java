package com.refah.walletwrapper.model.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Audited
@Table(name = "excel_details")
public class ExcelDetail extends BaseModel {

    private String excelName;

    private Long tenantId;

    private String accountNumberCode;

    private String companyName;

    @Column(nullable = false)
    private String baseUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String authKey;

    public ExcelDetail() {
    }

    public ExcelDetail(String excelName, Long tenantId, String accountNumberCode,
                       String companyName, String baseUrl, Date createdDate, String authKey) {
        this.excelName = excelName;
        this.tenantId = tenantId;
        this.accountNumberCode = accountNumberCode;
        this.companyName = companyName;
        this.baseUrl = baseUrl;
        this.authKey = authKey;
        super.setCreatedDate(createdDate);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getAccountNumberCode() {
        return accountNumberCode;
    }

    public void setAccountNumberCode(String accountNumberCode) {
        this.accountNumberCode = accountNumberCode;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String url) {
        this.baseUrl = url;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authLey) {
        this.authKey = authLey;
    }

    @Override
    public String toString() {
        return "\n - excelName : " + excelName +
                "\n - tenantId : " + tenantId +
                "\n - accountNumberCode : " + accountNumberCode +
                "\n - baseUrl : " + baseUrl +
                "\n - authKey : " + authKey;
    }
}
