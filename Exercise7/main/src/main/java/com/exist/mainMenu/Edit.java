package com.exist.mainMenu;

import com.exist.mainFunctions.EditEmployeeData;

public class Edit extends EmployeeRegistrationMenu {

    public void execute() {
        EditEmployeeData edit = new EditEmployeeData();

        edit.editData();
        //TODO: add choices for editing: a.employeedata b.positiondata c.departmentdata
    }

    public boolean exitMenu() {
        return false;
    }

}
