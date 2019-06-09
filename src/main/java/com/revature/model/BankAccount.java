package com.revature.model;

import java.util.Objects;

public class BankAccount {
    private int accountId;
    private String title;
    private float balance;
    private int userid;

    public BankAccount(int accountId, String title, float balance, int userid) {
        this.accountId = accountId;
        this.title = title;
        this.balance = balance;
        this.userid = userid;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountId == that.accountId &&
                Float.compare(that.balance, balance) == 0 &&
                userid == that.userid &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, title, balance, userid);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId=" + accountId +
                ", title='" + title + '\'' +
                ", balance=" + balance +
                ", userid=" + userid +
                '}';
    }
}
