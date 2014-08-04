package com.exist.employeeRegistrationMenu;

import java.util.Map;
import java.util.List;
import com.exist.employeeInfoPackage.EmployeeData;

public abstract class EmployeeRegMenu {
    public abstract void execute (List <String> sortKeyString, List <Integer> employeeIDStorage, Map <String, Integer> sortKeyID, Map <Integer, EmployeeData> employeeDatabase);
    public abstract boolean getShowDatabase ();
    public abstract boolean getDontExitMenu ();

}
