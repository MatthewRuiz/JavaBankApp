package com.revature.model;

import com.revature.UserStatus;

import java.util.Objects;
import java.util.Scanner;

public class BankUser {
    private int userid;
    private String username;
    private String password;
    private String email;
    private UserStatus status;

    public BankUser(int userid, String username, String password, String email, UserStatus status) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    public BankUser(int userid, String username, String password, String email) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public BankUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public BankUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public BankUser() {}

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankUser bankUser = (BankUser) o;
        return userid == bankUser.userid &&
                Objects.equals(username, bankUser.username) &&
                Objects.equals(password, bankUser.password) &&
                Objects.equals(email, bankUser.email) &&
                status == bankUser.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, username, password, email, status);
    }

    @Override
    public String toString() {
        return "BankUser{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }

    public void readIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your username: ");
        this.username = scanner.nextLine();

        System.out.print("Please enter your password: ");
        this.password = scanner.nextLine();
    }
}
