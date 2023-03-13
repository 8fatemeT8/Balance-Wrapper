package com.refah.walletwrapper.model.entity;


import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@Table(name = "wallets")
public class Wallet extends BaseModel {

    private String balance;
    @OneToOne
    private User user;

    @AuditMappedBy(mappedBy = "wallet")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    private List<Transaction> transactions = new ArrayList<>();

    public Wallet() {
    }

    public Wallet(String balance) {
        this.balance = balance;
    }

    public Wallet(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
