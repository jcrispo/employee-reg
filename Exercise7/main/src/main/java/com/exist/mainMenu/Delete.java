package com.exist.mainMenu;

import com.exist.mainFunctions.DeleteEmployeeData;

public class Delete extends EmployeeRegistrationMenu {

    public void execute() {
        DeleteEmployeeData delete = new DeleteEmployeeData();

        delete.deleteEmployeeInfo();
        //TODO: add delete choices to delete from position & department tables
    }

    public boolean exitMenu() {
        return false;
    }

}
