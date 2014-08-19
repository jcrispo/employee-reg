package com.exist.main;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import com.exist.main_Functions.Database;
import com.exist.main_Functions.Insert;
import com.exist.menu.main_Menu.EmployeeRegistrationMenu;
import com.exist.menu.main_Menu.MenuOptions;

public class EmployeeRegistration {
    private static String userChoice;
    private static Scanner userInput;
    private static Database companyDatabase;
    private static Insert insertToDatabase;
    private static EmployeeRegistrationMenu menu;
    private static boolean exitMenu;
    private static final String BLANK = "b";
    private static final String user = "root";
    private static final String pass = "ex1stgl0bal";
    private static final String dbUrl = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "Employee_Registration";
    private static final String menuOptions = "\nRegister 'r' | View 'v' | Edit 'e' | Delete 'd' | Exit 'x' : ";

    public EmployeeRegistration () {
        userInput = new Scanner(System.in);
        companyDatabase = new Database();
        insertToDatabase = new Insert();
        exitMenu = false;
    }

    public static void main (String[] args) {
        EmployeeRegistration mainClass = new EmployeeRegistration();
        System.out.println("Employee Registration Program\n");

        mainClass.loginDatabase();
        while (!exitMenu) {
            mainClass.showMenu();
            menu = MenuOptions.userChoice(userChoice);
            menu.execute();
            exitMenu = menu.exitMenu();
        }
    }

    public void showMenu () {
        System.out.print(menuOptions);
        userChoice = (userInput.nextLine()).trim();
        if (userChoice.length() == 0) {
            userChoice = BLANK;
        }
    }

    public void loginDatabase () {
            boolean exitLogin = false;
            companyDatabase.setCredentials(dbUrl + dbName, user, pass);
            insertToDatabase.setCredentials(dbUrl + dbName, user, pass);
            exitLogin = companyDatabase.loginDatabase();
            if (exitLogin) {
                System.out.println("Login to Database Successful\n");
            } else {
                System.out.println("Wrong Username / Password! ");
                System.exit(0);
            }
            companyDatabase.closeDatabase();
    }
}
