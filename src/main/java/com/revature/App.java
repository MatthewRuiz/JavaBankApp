package com.revature;

import com.revature.dao.bankaccount.BankAccountDAO;
import com.revature.dao.bankaccount.BankAccountDaoImpl;
import com.revature.dao.bankuser.BankUserDao;
import com.revature.dao.bankuser.BankUserDaoImpl;
import com.revature.exceptions.user.InvalidLoginException;
import com.revature.model.BankAccount;
import com.revature.model.BankUser;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private static final BankUserDao dao = new BankUserDaoImpl();
    private static final BankAccountDAO bankAccountDao = new BankAccountDaoImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) {
        menu();
    }

    private static void menu() {
        int menuChoice;
        do {
            System.out.println("What would you like to do?");
            System.out.println("Press 1 to login");

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case -1:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    System.out.println("Login");
                    Boolean b = checkIfUserCanLogin(getUserLoginInfo());
                    break;
                default:
                    System.out.println("Pick a valid option, jitbag.\n");
                    break;
            }
        } while (menuChoice != -1);
    }

    private static void userMenu() {
        int menuChoice;
        do {
            System.out.println("Welcome to the thunderdome.");
            menuChoice = -1;
        } while (menuChoice != -1);
    }

    private static BankUser getUserLoginInfo() {
        System.out.print("Please enter your username: ");
        String userName = scanner.nextLine();

        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        BankUser user = new BankUser(userName, password);
        System.out.println(user);
        return user;
    }

    private static boolean checkIfUserCanLogin(BankUser bankUser) {
        try {
            if (dao.login(bankUser)) {
                System.out.println("All good. You can login!");
                userMenu();
            } else {
                System.out.println("You're beat!");
                throw new InvalidLoginException("Invalid username or password.");
            }
        } catch (InvalidLoginException e) {
            System.err.println("InvalidLoginException Message: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Exception Message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }


        return true;
    }

}
