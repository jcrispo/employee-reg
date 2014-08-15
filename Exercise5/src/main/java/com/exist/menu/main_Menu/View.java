package com.exist.menu.main_Menu;

import java.util.Scanner;
import com.exist.main_Functions.ViewEmployeeData;

public class View extends EmployeeRegistrationMenu {
    private static ViewEmployeeData view;
    private static Scanner input;

    public View () {
        view = new ViewEmployeeData();
        input = new Scanner(System.in);
    }

    public void execute () {
        boolean exit = false;
        String userInput;
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

    public boolean showDatabase () {
        return true;
    }

    public boolean exitMenu () {
        return false;
    }

}

