package com.exist.main;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.exist.main_Functions.Database;
import com.exist.database.DB_Insert;
import com.exist.main_Functions.ViewEmployeeData;
import com.exist.main_Functions.DatabaseRetrieve;
import com.exist.menu.main_Menu.EmployeeRegistrationMenu;
import com.exist.menu.main_Menu.MenuOptions;

public class EmployeeRegistration {
    private static Connection dbConnection;
    private static String userChoice;
    private static Scanner userInput;
    private static DB_Insert insertToDatabase;
    private static ViewEmployeeData view;
    private static DatabaseRetrieve retrieveFromDatabase;
    private static EmployeeRegistrationMenu menu;
    private static boolean exitMenu;
    private static final String BLANK = "b";
    private static final String menuOptions = "\nRegister 'r' | View 'v' | Edit 'e' | Delete 'd' | Exit 'x' : ";

    public EmployeeRegistration () {
        dbConnection = null;
        userInput = new Scanner(System.in);
        insertToDatabase = new DB_Insert();
        view = new ViewEmployeeData();
        retrieveFromDatabase = new DatabaseRetrieve();
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
        Properties dbProperty = new Properties();
        try {
            InputStream is = new FileInputStream("db.properties");
            dbProperty.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dbConnection = DriverManager.getConnection(dbProperty.getProperty("db.url"), dbProperty.getProperty("db.user"), dbProperty.getProperty("db.password"));
            System.out.println("Login to Database Successful\n");
        } catch (SQLException e) {
            System.out.println("Wrong Username / Password! ");
            System.exit(0);
        }
        insertToDatabase.setCredentials(dbProperty.getProperty("db.url"), dbProperty.getProperty("db.user"), dbProperty.getProperty("db.password"));
        retrieveFromDatabase.setCredentials(dbProperty.getProperty("db.url"), dbProperty.getProperty("db.user"), dbProperty.getProperty("db.password"));
        view.setCredentials(dbProperty.getProperty("db.url"), dbProperty.getProperty("db.user"), dbProperty.getProperty("db.password"));
    }
}
