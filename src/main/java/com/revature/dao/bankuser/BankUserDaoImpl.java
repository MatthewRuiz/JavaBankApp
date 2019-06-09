package com.revature.dao.bankuser;

import com.revature.UserStatus;
import com.revature.exceptions.user.InvalidLoginException;
import com.revature.model.BankUser;
import com.revature.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankUserDaoImpl implements BankUserDao {

    @Override
    public List<BankUser> getAllUsers() {
        List<BankUser> users = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection()) {
            // Get a statment to the database
            PreparedStatement stmt = conn.prepareCall("SELECT * FROM bank_user");

            // Execute the statment, and get the result set.
            ResultSet rs = stmt.executeQuery();

            // Iterate over the Result Set and create new Bank Users
            while (rs.next()) {
                users.add (
                        new BankUser(rs.getInt("userid"), rs.getString("username"), rs.getString("password"), rs.getString("email"), UserStatus.valueOf(rs.getString("status")))
                );

                return users;
            }
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    @Override
    public BankUser getUserByUsername() {
        return null;
    }

    @Override
    public BankUser updateUser(BankUser bankUser) {
        return null;
    }

    @Override
    public BankUser deleteUser(String username) {
        return null;
    }

    @Override
    public BankUser createUser(BankUser bankUser) {
        int index = 1;

        try (Connection conn = ConnectionPool.getConnection()) {
            // Prepared our connection with the database
            PreparedStatement stmt = conn.prepareCall("INSERT INTO bank_user (username, password, email) VALUES (?, ?, ?)");

            // Set the appropriate values in the query
            stmt.setString(index++, bankUser.getUsername());
            stmt.setString(index++, bankUser.getPassword());
            stmt.setString(index++, bankUser.getEmail());

            // Execute the query
            // executeUpdate() is used for DML statement, and return an int value for the number of records affected
            if (stmt.executeUpdate() == 1) {
                return bankUser;
            }
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean login(BankUser bankUser) {
        BankUser user;
        int index = 1;
        try (Connection conn = ConnectionPool.getConnection()) {
            // Prepared our connection with the database
            PreparedStatement stmt = conn.prepareCall("SELECT COUNT(username) FROM bank_user WHERE username LIKE ? AND password = ?");

            // Set the appropriate values in the query
            stmt.setString(index++, bankUser.getUsername());
            stmt.setString(index++, bankUser.getPassword());

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                rs.last();
                int bankUserCount = rs.getInt(1);
                if (bankUserCount != 1) { return false; }       // A user with the provided credentials does not exist
            }
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }
}
