package com.exist.employeeRegistrationMenu;

import java.util.Map;
import java.util.List;
import com.exist.employeeInfoPackage.EmployeeData;
import com.exist.addViewPackage.AddEmployeeData;

public class Register extends EmployeeRegMenu {
    private static AddEmployeeData addData;

    public Register () {
        addData = new AddEmployeeData();
    }

    public void execute (List <String> sortKeyString, List <Integer> employeeIDStorage, Map <String, Integer> sortKeyID, Map <Integer, EmployeeData> employeeDatabase) {
        EmployeeData employee = new EmployeeData();
        addData.addEmpData(employee);
        employeeIDStorage.add(employee.getEmployeeID());
        employeeDatabase.put(employee.getEmployeeID(), employee);
    }

    public boolean getShowDatabase () {
        return true;
    }

    public boolean getDontExitMenu () {
        return true;
    }

}
