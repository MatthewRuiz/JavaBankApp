package com.revature.dao.bankuser;

import com.revature.model.BankUser;

import java.util.List;

public interface BankUserDao {

    List<BankUser> getAllUsers();
    BankUser getUserByUsername();
    BankUser updateUser(BankUser bankUser);
    BankUser deleteUser(String username);
    BankUser createUser(BankUser bankUser);
    BankUser login(BankUser bankUser);
}
