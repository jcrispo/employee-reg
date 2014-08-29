package com.exist.menu.mainMenu;

import java.util.Scanner;
import com.exist.mainFunctions.ViewEmployeeData;

public class View extends EmployeeRegistrationMenu {

    public void execute() {
        ViewEmployeeData view = new ViewEmployeeData();
        Scanner input = new Scanner(System.in);
        String userInput = new String();

        boolean exit = false;
        while (!exit) {
            System.out.print("View (1) Employee Data, (2) List of Positions, (3) List of Departments:  ");

            userInput = input.nextLine().trim();

            exit = true;
            if (userInput.equals("1")) {
                view.viewEmployeeInformation();
            } else if (userInput.equals("2")) {
                view.viewSortedPosition();
            } else if (userInput.equals("3")){
                view.viewSortedDepartments();
            } else {
                System.out.println("Invalid input!");

                exit = false;
            }
        }

    }

    public boolean showDatabase() {
        return true;
    }

    public boolean exitMenu() {
        return false;
    }

}
