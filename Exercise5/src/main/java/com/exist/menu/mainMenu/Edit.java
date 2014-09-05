package com.exist.menu.mainMenu;

import com.exist.mainFunctions.EditEmployeeData;

public class Edit extends EmployeeRegistrationMenu {

    public void execute() {
        EditEmployeeData edit = new EditEmployeeData();

        edit.editData();
        //TODO: add choices for editing: a.employeedata b.positiondata c.departmentdata
    }

    public boolean showDatabase() {
        return true;
    }

    public boolean exitMenu() {
        return false;
    }

}
