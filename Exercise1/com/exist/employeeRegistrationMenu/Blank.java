package com.exist.employeeRegistrationMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.exist.employeeInfoPackage.EmployeeData;

public class Blank extends EmployeeRegMenu {

    public void execute (List <String> sortKeyString, List <Integer> employeeIDStorage, Map <String, Integer> sortKeyID, Map <Integer, EmployeeData> employeeDatabase) {
        System.out.println("\nNo data was given!");
    }

    public boolean getShowDatabase () {
        return false;
    }

    public boolean getDontExitMenu () {
        return true;
    }

}
