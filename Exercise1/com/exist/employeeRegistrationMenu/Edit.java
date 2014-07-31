package com.exist.employeeRegistrationMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.exist.employeeInfoPackage.EmployeeData;
import com.exist.editDeletePackage.EditEmployeeData;

public class Edit extends EmployeeRegMenu {
    private static EditEmployeeData editData;
    private static final String EMPTYDATABASE = "\nEmployee Database is Empty!";

    public Edit () {
        editData = new EditEmployeeData();
    }

    public void execute (List <String> sortKeyString, List <Integer> employeeIDStorage, Map <String, Integer> sortKeyID, Map <Integer, EmployeeData> employeeDatabase) {
        if (employeeDatabase.isEmpty()){
            System.out.println(EMPTYDATABASE);
        } else { 
            editData.editEmpData(employeeIDStorage, employeeDatabase);
        }
    }

    public boolean getShowDatabase () {
        return true;
    }

    public boolean getDontExitMenu () {
        return true;
    }

}
