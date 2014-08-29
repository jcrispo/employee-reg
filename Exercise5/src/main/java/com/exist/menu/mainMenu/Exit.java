package com.exist.menu.mainMenu;

public class Exit extends EmployeeRegistrationMenu {

    public void execute() {
        System.out.println("Exit");
    }

    public boolean showDatabase() {
        return false;
    }

    public boolean exitMenu() {
        return true;
    }

}
