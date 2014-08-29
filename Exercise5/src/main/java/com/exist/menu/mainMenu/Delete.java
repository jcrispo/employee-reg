package com.exist.menu.mainMenu;

import com.exist.mainFunctions.DeleteEmployeeData;

public class Delete extends EmployeeRegistrationMenu {

    public void execute() {
        DeleteEmployeeData delete = new DeleteEmployeeData();

        delete.deleteEmployeeInfo();
        //add delete choices to delete from position & department tables
    }

    public boolean showDatabase() {
        return true;
    }

    public boolean exitMenu() {
        return false;
    }

}
