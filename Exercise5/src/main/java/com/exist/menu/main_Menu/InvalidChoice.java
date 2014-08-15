package com.exist.menu.main_Menu;

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

