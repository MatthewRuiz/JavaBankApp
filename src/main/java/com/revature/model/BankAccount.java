package com.revature.model;

import java.util.Objects;

public class BankAccount {
    private String title;
    private float balance;
    private int userid;

    public BankAccount(String title, float balance, int userid) {
        this.title = title;
        this.balance = balance;
        this.userid = userid;
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
        return Float.compare(that.balance, balance) == 0 &&
                userid == that.userid &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, balance, userid);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "title='" + title + '\'' +
                ", balance=" + balance +
                ", userid=" + userid +
                '}';
    }
}
