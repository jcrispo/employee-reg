package com.exist.menu.mainMenu;

public class Blank extends EmployeeRegistrationMenu {

    public void execute () {
        System.out.println("Blank Input!");
    }

    public boolean showDatabase () {
        return false;
    }

    public boolean exitMenu () {
        return false;
    }

}

