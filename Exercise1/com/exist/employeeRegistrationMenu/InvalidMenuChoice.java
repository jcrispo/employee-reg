package com.exist.employeeRegistrationMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.exist.employeeInfoPackage.EmployeeData;

public class InvalidMenuChoice extends EmployeeRegMenu {

    public void execute (List <String> sortKeyString, List <Integer> employeeIDStorage, Map <String, Integer> sortKeyID, Map <Integer, EmployeeData> employeeDatabase) {
        System.out.println("\nInput invalid! Please choose only from the given choices.");
    }

    public boolean getShowDatabase () {
        return false;
    }

    public boolean getDontExitMenu () {
        return true;
    }

}
