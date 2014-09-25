package com.exist.mainMenu;

public class InvalidChoice extends EmployeeRegistrationMenu {

    public void execute() {
        System.out.println("Invalid Input!");
    }

    public boolean exitMenu() {
        return false;
    }

}
