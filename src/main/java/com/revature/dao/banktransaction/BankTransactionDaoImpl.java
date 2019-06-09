package com.revature.dao.banktransaction;

import com.revature.model.BankAccount;
import com.revature.model.BankTransaction;
import com.revature.model.BankUser;
import com.revature.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionDaoImpl implements BankTransactionDao {

    @Override
    public List<BankTransaction> getAllTransactions(BankAccount bankAccount) {
        List<BankTransaction> transactions = new ArrayList<>();
        int index = 1;
        try (Connection conn = ConnectionPool.getConnection()) {
            // Get a statment to the database
            PreparedStatement stmt = conn.prepareCall("SELECT * FROM bank_transaction WHERE account_id = ?");
            stmt.setInt(index++, bankAccount.getAccountId());

            // Execute the statement, and get the result set.
            ResultSet rs = stmt.executeQuery();

            // Iterate over the Result Set and create new Bank Users
            while (rs.next()) {
                transactions.add(
                        new BankTransaction(rs.getInt("transaction_id"), rs.getString("title"), rs.getFloat("amount"), rs.getInt("account_id"))
                );
            }
            return transactions;
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public BankTransaction createTransaction(BankTransaction bankTransaction) {
        return null;
    }

    @Override
    public BankTransaction deleteTransaction(BankTransaction bankTransaction) {
        return null;
    }

    @Override
    public BankTransaction updateTransaction(BankTransaction bankTransaction) {
        return null;
    }
}
