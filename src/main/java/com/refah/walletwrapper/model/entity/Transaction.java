package com.refah.walletwrapper.model.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Audited
public class Transaction extends BaseModel implements Comparable<Transaction> {
    private String amount;
    private String receiptId;
    private String transactionId;
    private boolean finished = false;

    @ManyToOne
    private Wallet wallet;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String balance) {
        this.amount = balance;
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

    public Transaction(String amount, Wallet wallet) {
        this.amount = amount;
        this.wallet = wallet;
    }

    public Transaction(String amount, String receiptId, String transactionId) {
        this.amount = amount;
        this.receiptId = receiptId;
        this.transactionId = transactionId;
    }

    public Transaction(Integer id, Date createdDate, String amount, String receiptId, String transactionId) {
        super(id, createdDate);
        this.amount = amount;
        this.receiptId = receiptId;
        this.transactionId = transactionId;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.getId().compareTo(o.getId());
    }
}
