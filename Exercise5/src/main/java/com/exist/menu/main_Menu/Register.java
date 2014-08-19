package com.exist.menu.main_Menu;

import java.util.Scanner;
import com.exist.main_Functions.AddData;

public class Register extends EmployeeRegistrationMenu {
    private AddData register;
    private static Scanner input;

    public Register () {
        register = new AddData();
        input = new Scanner(System.in);
    }

    public void execute () {
        boolean exit = false;
        String userInput;
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

    public boolean showDatabase () {
        return false;
    }

    public boolean exitMenu () {
        return false;
    }

}
