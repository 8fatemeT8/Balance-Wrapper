package com.refah.walletwrapper.model.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Audited
public class Transaction extends BaseModel {
    private String balance;
    private String receiptId;
    private String transactionId;
    private boolean finished = false;

    @ManyToOne
    private Wallet wallet;


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Transaction() {
    }

    public Transaction(String balance,Wallet wallet) {
        this.balance = balance;
        this.wallet = wallet;
    }

    public Transaction(String balance, String receiptId, String transactionId) {
        this.balance = balance;
        this.receiptId = receiptId;
        this.transactionId = transactionId;
    }

    public Transaction(Integer id, Date createdDate, String balance, String receiptId, String transactionId) {
        super(id, createdDate);
        this.balance = balance;
        this.receiptId = receiptId;
        this.transactionId = transactionId;
    }
}
