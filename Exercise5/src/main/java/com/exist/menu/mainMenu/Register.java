package com.exist.menu.mainMenu;

import java.util.Scanner;
import com.exist.mainFunctions.AddData;

public class Register extends EmployeeRegistrationMenu {

    public void execute() {
        AddData register = new AddData();
        Scanner input = new Scanner(System.in);
        String userInput = new String();

        boolean exit = false;
        while (!exit) {
            System.out.print("View (1) Add New Employee Data, (2) Add New Position, (3) Add New Department:  ");

            userInput = input.nextLine().trim();

            exit = true;
            if (userInput.equals("1")) {
                register.addEmployeePersonalData();
            } else if (userInput.equals("2")) {
                register.addNewPosition();
            } else if (userInput.equals("3")){
                register.addNewDepartment();
            } else {
                System.out.println("Invalid input!");

                exit = false;
            }
        }

    }

    public boolean showDatabase() {
        return false;
    }

    public boolean exitMenu() {
        return false;
    }

}
