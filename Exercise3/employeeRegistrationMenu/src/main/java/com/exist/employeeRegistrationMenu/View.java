package com.exist.employeeRegistrationMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.exist.employeeInfoPackage.EmployeeData;
import com.exist.addViewPackage.ViewEmployeeData;
import com.exist.addViewPackage.AddEmployeeData;

public class View extends EmployeeRegMenu {
    private static ViewEmployeeData viewData;
    private static AddEmployeeData addData;
    private static final String EMPTYDATABASE = "\nEmployee Database is Empty!";

    public View () {
        viewData = new ViewEmployeeData();
        addData = new AddEmployeeData();
    }

    public void execute (List <String> sortKeyString, List <Integer> employeeIDStorage, Map <String, Integer> sortKeyID, Map <Integer, EmployeeData> employeeDatabase) {
        if (employeeDatabase.isEmpty()){
            System.out.println(EMPTYDATABASE);
        } else {
            viewData.sortEmpData(sortKeyString, sortKeyID, employeeDatabase);
            addData.updateEmployeeIDList(employeeIDStorage, employeeDatabase);
        } 
    }

    public boolean getShowDatabase () {
        return false;
    }

    public boolean getDontExitMenu () {
        return true;
    }

}
