package com.exist.main;

import java.util.Scanner;

import com.exist.database.utility.HDBUtility;
import com.exist.mainMenu.EmployeeRegistrationMenu;
import com.exist.mainMenu.MenuOptions;

public class EmployeeRegistration {
    private static String userChoice;
    private static final String BLANK = "b";
    private static final String menuOptions = "\nRegister 'r' | View 'v' | Edit 'e' | Delete 'd' | Exit 'x' : ";

    public EmployeeRegistration() {
        userChoice = new String();
    }

    public static void main(String[] args) {
        EmployeeRegistration mainClass = new EmployeeRegistration();

        mainClass.login();

        System.out.println("\nEmployee Registration Program\n");

        boolean exitMenu = false;
        while (!exitMenu) {
            mainClass.showMenu();

            EmployeeRegistrationMenu menu = MenuOptions.userChoice(userChoice);
            menu.execute();
            exitMenu = menu.exitMenu();
        }
    }

    public void showMenu() {
        Scanner userInput = new Scanner(System.in);

        System.out.print(menuOptions);

        userChoice = (userInput.nextLine()).trim();
        if (userChoice.length() == 0) {
            userChoice = BLANK;
        }
    }

    public void login() {
        HDBUtility hdbUtility = new HDBUtility();

        hdbUtility.login();
    }

}
