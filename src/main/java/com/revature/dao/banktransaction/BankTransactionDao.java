package com.revature.dao.banktransaction;

import com.revature.model.BankAccount;
import com.revature.model.BankTransaction;
import com.revature.model.BankUser;

import java.util.List;

public interface BankTransactionDao {
    List<BankTransaction> getAllTransactions(BankAccount bankAccount);
    BankTransaction createTransaction(BankTransaction bankTransaction);
    BankTransaction deleteTransaction(BankTransaction bankTransaction);
    BankTransaction updateTransaction(BankTransaction bankTransaction);
}
