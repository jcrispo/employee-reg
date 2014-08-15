package com.exist.menu.main_Menu;

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

