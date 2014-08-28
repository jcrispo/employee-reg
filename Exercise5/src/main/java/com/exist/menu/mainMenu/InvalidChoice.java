package com.exist.menu.mainMenu;

public class InvalidChoice extends EmployeeRegistrationMenu {

    public void execute () {
        System.out.println("Invalid Input!");
    }

    public boolean showDatabase () {
        return false;
    }

    public boolean exitMenu () {
        return false;
    }

}

