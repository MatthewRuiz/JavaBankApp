package com.revature.model;

import java.util.Objects;

public class BankTransaction {
    private int transactionId;
    private String title;
    private float amount;
    private int accountId;

    public BankTransaction(int transactionId, String title, float amount, int accountId) {
        this.transactionId = transactionId;
        this.title = title;
        this.amount = amount;
        this.accountId = accountId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return transactionId == that.transactionId &&
                Float.compare(that.amount, amount) == 0 &&
                accountId == that.accountId &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, title, amount, accountId);
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "transactionId=" + transactionId +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", accountId=" + accountId +
                '}';
    }
}
