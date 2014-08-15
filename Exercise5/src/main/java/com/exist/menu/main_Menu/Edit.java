package com.exist.menu.main_Menu;

import com.exist.main_Functions.EditEmployeeData;

public class Edit extends EmployeeRegistrationMenu {
    private EditEmployeeData edit;

    public Edit () {
        edit = new EditEmployeeData();
    }

    public void execute () {
        edit.editData();
        //add choices for editing: a.employeedata b.positiondata c.departmentdata
    }

    public boolean showDatabase () {
        return true;
    }

    public boolean exitMenu () {
        return false;
    }

}

