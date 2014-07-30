package com.exist.employeeRegistrationMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.exist.employeeInfoPackage.EmployeeData;
import com.exist.editDeletePackage.DeleteEmployeeData;
import com.exist.addViewPackage.AddEmployeeData;

public class Delete extends EmployeeRegMenu {
    private static AddEmployeeData addData;
    private static DeleteEmployeeData deleteData;
    private static final String EMPTYDATABASE = "\nEmployee Database is Empty!";

    public Delete () {
        deleteData = new DeleteEmployeeData();
        addData = new AddEmployeeData();
    }

    public void execute (List <String> sortKeyString, List <Integer> employeeIDStorage, Map <String, Integer> sortKeyID, Map <Integer, EmployeeData> employeeDatabase) {
        if (employeeDatabase.isEmpty()){
            System.out.println(EMPTYDATABASE);
        } else {
            deleteData.deleteEmpData(employeeDatabase);
            addData.updateEmployeeIDList(employeeIDStorage, employeeDatabase);
        }
    }

    public boolean getShowDatabase () {
        return true;
    }

    public boolean getDontExitMenu () {
        return true;
    }

}
