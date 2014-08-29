package com.exist.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import com.exist.menu.mainMenu.EmployeeRegistrationMenu;
import com.exist.menu.mainMenu.MenuOptions;
import com.exist.database.DBDataSource;

public class EmployeeRegistration {
    private static String userChoice;
    private static final String BLANK = "b";
    private static final String menuOptions = "\nRegister 'r' | View 'v' | Edit 'e' | Delete 'd' | Exit 'x' : ";

    public EmployeeRegistration () {
        userChoice = new String();
    }

    public static void main (String[] args) {
        EmployeeRegistration mainClass = new EmployeeRegistration();

        System.out.println("Employee Registration Program\n");

        mainClass.loginDatabase();

        boolean exitMenu = false;
        while (!exitMenu) {
            mainClass.showMenu();

            EmployeeRegistrationMenu menu = MenuOptions.userChoice(userChoice);
            menu.execute();
            exitMenu = menu.exitMenu();
        }
    }

    public void showMenu () {
        System.out.print(menuOptions);

        Scanner userInput = new Scanner(System.in);
        userChoice = (userInput.nextLine()).trim();
        if (userChoice.length() == 0) {
            userChoice = BLANK;
        }
    }

    public void loginDatabase () {
        Connection dbConnection = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();

            System.out.println("Login to Database Successful\n");
        } catch (SQLException e) {
            System.out.println("Wrong Username / Password! ");

            e.printStackTrace();
            System.exit(0);
        } finally {
            try {
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
