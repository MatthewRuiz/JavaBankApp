package com.revature;

import com.revature.dao.bankaccount.BankAccountDAO;
import com.revature.dao.bankaccount.BankAccountDaoImpl;
import com.revature.dao.banktransaction.BankTransactionDao;
import com.revature.dao.banktransaction.BankTransactionDaoImpl;
import com.revature.dao.bankuser.BankUserDao;
import com.revature.dao.bankuser.BankUserDaoImpl;
import com.revature.exceptions.user.InvalidLoginException;
import com.revature.model.BankAccount;
import com.revature.model.BankUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final BankUserDao dao = new BankUserDaoImpl();
    private static final BankAccountDAO bankAccountDao = new BankAccountDaoImpl();
    private static final BankTransactionDao bankTransactionDao = new BankTransactionDaoImpl();
    private static final Scanner scanner = new Scanner(System.in);
    private static BankUser currentUser = new BankUser();
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void main( String[] args ) {
        menu();
    }

    private static void menu() {
        System.out.println("Welcome to Thunderdome, Bitch.");
        int menuChoice = 0;
        do {
            String menuSelections[] = {"Login", "Quit"};
            menuChoice = getMenuSelection(menuSelections);

            if (menuChoice == 2) {
                if (verifyQuit()) {
                    quitProgram();
                }
            } else {
                startMenu(menuChoice);
            }
        } while (menuChoice != -1);
    }

    private static void startMenu(int selection) {
        Boolean continueWithCurrentMenu = true;
        while (continueWithCurrentMenu) {
            switch (selection) {
                case 1:
                    if (checkIfUserCanLogin(getUserLoginInfo())) {
                        userMenu();
                        return;
                    }
                    break;
                case 2:
                    continueWithCurrentMenu = !verifyQuit();
                default:
                    System.out.println("Pick a valid option, jitbag.\n");
                    break;
            }
        }
    }

    /*
        Menu for when the user has logged in.
     */
    private static void userMenu() {
        int menuChoice = 0;
        do {
            String menuSelections[] = {"View Accounts", "View Account Activity", "Logout", "Quit"};
            menuChoice = getMenuSelection(menuSelections);

            switch (menuChoice) {
                case 1:
                    loadAccounts(currentUser);
                    System.out.println(accounts);
                    break;
                case 2:
                    loadAccounts(currentUser);
                    System.out.println(bankTransactionDao.getAllTransactions(accounts.get(0)));
                    break;
                case 3:
                    if (!verifyLogout()) { menuChoice = 0; }
                    break;
                case 4:
                    if (!verifyQuit()) {
                        menuChoice = 0;
                    } else {
                        quitProgram();
                    }
                default:
                    break;
            }
        }while(menuChoice != 3);
    }

    /*
        Get username and password
     */
    private static BankUser getUserLoginInfo() {
        BankUser user = new BankUser();
        user.readIn();

        return user;
    }

    private static void loadAccounts(BankUser bankUser) {
        if (accounts.size() == 0) {
            accounts = bankAccountDao.getAllAccounts(currentUser);
        }
    }

    private static boolean checkIfUserCanLogin(BankUser bankUser) {
        try {
            currentUser = dao.login(bankUser);
            if (currentUser == null) {
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

    /*
        Given a menu, return the user's selection
     */
    private static int getMenuSelection(String[] menuSelections) {
        int menuSelectionsLength = menuSelections.length;
        int menuChoice = 0;

        // Display menu
        for (int i = 0; i < menuSelectionsLength; i++) {
            System.out.println((i+1) + ") " + menuSelections[i]);
        }

        while (true) {
            System.out.print("What would you like to do? ");
            // Ensure that the user has entered a number
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter a number. ");
                scanner.nextLine();
            }

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            if (menuChoice < 1 || menuChoice > menuSelectionsLength) {
                System.out.println("Please enter a valid selection. ");
                continue;
            }
            break;
        }

        return menuChoice;
    }

    private static boolean verifyQuit() {
        return verifyChoice("Are you sure you want to quit? (Y/N) ");
    }

    private static boolean verifyLogout() {
        return verifyChoice("Are you sure you want to logout? (Y/N) ");
    }

    private static boolean verifyChoice(String verificationMessage) {
        System.out.println(verificationMessage);
        char input = scanner.nextLine().toLowerCase().charAt(0);

        while (input != 'y' && input != 'n') {
            System.out.print("Invalid selection. Please type \'Y\' for Yes and \'N\' for No. ");
            input = scanner.nextLine().toLowerCase().charAt(0);
        }

        return input == 'y';
    }

    private static void quitProgram() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
