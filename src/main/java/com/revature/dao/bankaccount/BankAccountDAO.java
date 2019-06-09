package com.revature.dao.bankaccount;

import com.revature.model.BankAccount;

import java.util.List;

public interface BankAccountDAO {
    List<BankAccount> getAllAccounts();
    BankAccount getAccountByTitle();
    BankAccount updateAccount(BankAccount bankAccount);
    BankAccount deleteAccount(String username);
    BankAccount createAccount(BankAccount bankAccount);
}
