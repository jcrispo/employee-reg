package com.exist.menu.main_Menu;

import com.exist.main_Functions.DeleteEmployeeData;

public class Delete extends EmployeeRegistrationMenu {
    private static DeleteEmployeeData delete;

    public Delete () {
        delete = new DeleteEmployeeData();
    }

    public void execute () {
        delete.deleteEmployeeInfo();
        //add delete choices to delete from position & department tables
    }

    public boolean showDatabase () {
        return true;
    }

    public boolean exitMenu () {
        return false;
    }

}

