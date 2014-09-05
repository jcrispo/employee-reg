package com.exist.menu.mainMenu;

import java.util.Scanner;
import com.exist.mainFunctions.AddData;
import com.exist.database.DBInsert;

public class Register extends EmployeeRegistrationMenu {

    public void execute() {
        AddData register = new AddData();
        Scanner input = new Scanner(System.in);
        String userInput = new String();
        DBInsert dbIn = new DBInsert();

        boolean exit = false;
        while (!exit) {
            System.out.print("View (1) Add New Employee Data, (2) Add New Position, (3) Add New Department, (4) Populate Database with 10000+ Data:  ");

            userInput = input.nextLine().trim();
            System.out.println();

            exit = true;
            if (userInput.equals("1")) {
                register.addEmployeePersonalData();
            } else if (userInput.equals("2")) {
                register.addNewPosition();
            } else if (userInput.equals("3")){
                register.addNewDepartment();
            } else if (userInput.equals("4")){
                dbIn.addRandomData();
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
