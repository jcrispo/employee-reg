package com.exist.menu.main_Menu;

public class Exit extends EmployeeRegistrationMenu {

    public void execute () {
        System.out.println("Exit");
    }

    public boolean showDatabase () {
        return false;
    }

    public boolean exitMenu () {
        return true;
    }

}

