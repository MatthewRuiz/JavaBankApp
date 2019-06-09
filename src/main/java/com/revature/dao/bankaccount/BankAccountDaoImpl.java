package com.revature.dao.bankaccount;

import com.revature.model.BankAccount;
import com.revature.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDaoImpl implements BankAccountDAO {
    @Override
    public List<BankAccount> getAllAccounts() {
        List<BankAccount> accounts = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection()) {
            // Get a statment to the database
            PreparedStatement stmt = conn.prepareCall("SELECT * FROM bank_account");

            // Execute the statment, and get the result set.
            ResultSet rs = stmt.executeQuery();

            // Iterate over the Result Set and create new Bank Users
            while (rs.next()) {
                accounts.add(
                        new BankAccount(rs.getString("title"), rs.getFloat("balance"), rs.getInt("userid"))
                );
            }
            return accounts;
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public BankAccount getAccountByTitle() {
        return null;
    }

    @Override
    public BankAccount updateAccount(BankAccount bankAccount) {
        return null;
    }

    @Override
    public BankAccount deleteAccount(String username) {
        return null;
    }

    @Override
    public BankAccount createAccount(BankAccount bankAccount) {
        return null;
    }
}
