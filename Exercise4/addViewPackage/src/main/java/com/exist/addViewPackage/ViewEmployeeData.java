package com.exist.addViewPackage;

import com.exist.employeeInfoPackage.EmployeeDataValidation;
import com.exist.employeeInfoPackage.EmployeeData;
import java.util.Scanner;
import java.util.Formatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ViewEmployeeData{
    private static ViewEmployeeData viewDataClass = new ViewEmployeeData();
    private static EmployeeDataValidation validate;
    private static DateFormat dateFormat;
    private static DateFormat sortDateFormat;
    private static Scanner input;
    private static String  tempID;
    private static String  tempFName;
    private static String  tempMName;
    private static String  tempLName;
    private static String  tempGender;
    private static String  tempBirthDate;
    private static String  tempPosition;
    private static int sortChoice = 0;
    private static int DisplayPadding = 0;
    private static final int FIRSTNAME = 1;
    private static final int LASTNAME = 2;
    private static final int AGE = 3;
    private static final int EMPLOYEEID = 5;
    private static final int POSITION = 4;
    private static final int ASCENDING = 1;
    private static final int DESCENDING = 2;
    private static final String EMPLOYEE_POSITION = "Position";
    private static final String EMPLOYEE_BIRTHDATE = "Birth Date";
    private static final String EMPLOYEE_GENDER = "Gender";
    private static final String EMPLOYEE_NAME = "Employee Name";
    private static final String EMPLOYEE_FNAME = "First Name";
    private static final String EMPLOYEE_MNAME = "Middle Name";
    private static final String EMPLOYEE_LNAME = "Last Name";
    private static final String EMPLOYEE_ID = "Employee ID";
    private static final String NXTLINE = "\n";
    private static String DISPLAYFORMAT = "%-20s%-25s%-25s%-25s%-11s%-23s%-20s\n";

    public ViewEmployeeData () {
        dateFormat = new SimpleDateFormat("dd/MM/YYYY - HH:mm");
        sortDateFormat = new SimpleDateFormat("YYYY/MM/dd - HH:mm");
        input = new Scanner(System.in);
        validate = new EmployeeDataValidation(); 
    }

    public void viewEmpData (List<Integer> tempKeySortStorage, Map<Integer, EmployeeData> employeeDatabase) {
        Collections.sort(tempKeySortStorage);
        System.out.format(NXTLINE + DISPLAYFORMAT + NXTLINE, EMPLOYEE_ID, EMPLOYEE_FNAME, EMPLOYEE_MNAME, EMPLOYEE_LNAME, EMPLOYEE_GENDER, EMPLOYEE_BIRTHDATE, EMPLOYEE_POSITION);
        for (int i=0; i < tempKeySortStorage.size(); i++) {
            tempID = (employeeDatabase.get(tempKeySortStorage.get(i))).getEmployeeID().toString();
            tempFName = (employeeDatabase.get(tempKeySortStorage.get(i))).getFirstName();
            tempMName = (employeeDatabase.get(tempKeySortStorage.get(i))).getMiddleName();
            tempLName = (employeeDatabase.get(tempKeySortStorage.get(i))).getLastName();
            tempGender = (employeeDatabase.get(tempKeySortStorage.get(i))).getGender();
            tempBirthDate = dateFormat.format(((employeeDatabase.get(tempKeySortStorage.get(i))).getBirthDate()).getTime());
            tempPosition = (employeeDatabase.get(tempKeySortStorage.get(i))).getPosition();
            System.out.format(DISPLAYFORMAT, tempID, tempFName, tempMName, tempLName, tempGender, tempBirthDate, tempPosition);
        }
    }

    public void viewEmployeeByID (Integer idKey, Map<Integer, EmployeeData> employeeDatabase) {
        tempID = (employeeDatabase.get(idKey)).getEmployeeID().toString();
        tempFName = (employeeDatabase.get(idKey)).getFirstName();
        tempMName = (employeeDatabase.get(idKey)).getMiddleName();
        tempLName = (employeeDatabase.get(idKey)).getLastName();
        tempGender = (employeeDatabase.get(idKey)).getGender();
        tempBirthDate = dateFormat.format(((employeeDatabase.get(idKey)).getBirthDate()).getTime());
        tempPosition = (employeeDatabase.get(idKey)).getPosition();
        System.out.format(NXTLINE + DISPLAYFORMAT + NXTLINE, EMPLOYEE_ID, EMPLOYEE_FNAME, EMPLOYEE_MNAME, EMPLOYEE_LNAME, EMPLOYEE_GENDER, EMPLOYEE_BIRTHDATE, EMPLOYEE_POSITION);
        System.out.format(DISPLAYFORMAT, tempID, tempFName, tempMName, tempLName, tempGender, tempBirthDate, tempPosition);
    }

    public void showSortEmployeeData (List<String> tempKeySortStorage, Map<String, Integer> key, Map<Integer, EmployeeData> employeeDatabase ) {
        String order;
        boolean exitOrderLoop = false;
        while (!exitOrderLoop) {
            System.out.print("\nSort by (1) Ascending or (2) Descending : ");
            order = input.nextLine();
            if (validate.onlyNumbers(order)) {
                if (Integer.parseInt(order) == DESCENDING) {
                    Collections.sort(tempKeySortStorage);
                    Collections.reverse(tempKeySortStorage);
                    System.out.println("\nDescending Sort");
                    exitOrderLoop = true;
                } else if (Integer.parseInt(order) == ASCENDING) {
                    Collections.sort(tempKeySortStorage);
                    System.out.println("\nAscending Sort");
                    exitOrderLoop = true;
                } else {
                    System.out.print("\nInvalid Input. Please choose only between '1' Ascending and '2' Descending: \n");
                }
            }
        }
        if (sortChoice == AGE) {
            Collections.reverse(tempKeySortStorage);
        }
        System.out.format(NXTLINE + DISPLAYFORMAT + NXTLINE, EMPLOYEE_ID, EMPLOYEE_FNAME, EMPLOYEE_MNAME, EMPLOYEE_LNAME, EMPLOYEE_GENDER, EMPLOYEE_BIRTHDATE, EMPLOYEE_POSITION);
        for (int i=0; i<tempKeySortStorage.size(); i++) {
            tempID = (employeeDatabase.get(key.get(tempKeySortStorage.get(i)))).getEmployeeID().toString();
            tempFName = (employeeDatabase.get(key.get(tempKeySortStorage.get(i)))).getFirstName();
            tempMName = (employeeDatabase.get(key.get(tempKeySortStorage.get(i)))).getMiddleName();
            tempLName = (employeeDatabase.get(key.get(tempKeySortStorage.get(i)))).getLastName();
            tempGender = (employeeDatabase.get(key.get(tempKeySortStorage.get(i)))).getGender();
            tempBirthDate = dateFormat.format(((employeeDatabase.get(key.get(tempKeySortStorage.get(i)))).getBirthDate()).getTime());
            tempPosition = (employeeDatabase.get(key.get(tempKeySortStorage.get(i)))).getPosition();
        System.out.format(DISPLAYFORMAT, tempID, tempFName, tempMName, tempLName, tempGender, tempBirthDate, tempPosition);
        }
    }

    public void sortEmpData (List<String> keyID, Map<String, Integer> sortKey, Map<Integer, EmployeeData> eDatabase) {
        String searchString;
        boolean exitSortOption = false; 
        keyID.clear();
        sortKey.clear();
        while (!exitSortOption)  {
            System.out.print("\nSort Data by: (1) First Name, (2) Last Name, (3) Age, (4) Position, (5) Employee ID | Please Choose a Number from 1~5: ");
            try { 
                sortChoice = Integer.parseInt(input.nextLine()); 
            } catch (NumberFormatException e) { 
            }
            exitSortOption = true;
            if (sortChoice == LASTNAME) { 
                System.out.println("\nSorted by Last Name");
                for (Integer employeeID : eDatabase.keySet()) {
                    searchString = (String)(eDatabase.get(employeeID).getLastName() + eDatabase.get(employeeID).getFirstName() + eDatabase.get(employeeID).getMiddleName())
                        + String.valueOf(employeeID);
                    sortKey.put(searchString  , employeeID);
                }
                viewDataClass.sortBy(keyID, sortKey);
                viewDataClass.showSortEmployeeData(keyID, sortKey, eDatabase);
            } else if (sortChoice == FIRSTNAME) { 
                System.out.println("\nSorted by First Name");
                for (Integer employeeID : eDatabase.keySet()) {
                   searchString = (String)(eDatabase.get(employeeID).getFirstName() + eDatabase.get(employeeID).getLastName() + eDatabase.get(employeeID).getMiddleName())
                        + String.valueOf(employeeID);
                   sortKey.put(searchString  , employeeID);
                }
                viewDataClass.sortBy(keyID, sortKey);
                viewDataClass.showSortEmployeeData(keyID, sortKey, eDatabase);
            } else if (sortChoice == AGE) {
                System.out.println("\nSorted by Age" );
                for (Integer employeeID : eDatabase.keySet()) {
                    searchString = String.valueOf(sortDateFormat.format((eDatabase.get(employeeID).getBirthDate()).getTime())) + (String)(eDatabase.get(employeeID).getLastName()
                        + eDatabase.get(employeeID).getFirstName() + eDatabase.get(employeeID).getMiddleName()) + String.valueOf(employeeID);
                    sortKey.put(searchString  , employeeID);
                }
                viewDataClass.sortBy(keyID, sortKey);
                viewDataClass.showSortEmployeeData(keyID, sortKey, eDatabase);
            } else if (sortChoice == POSITION) {
                System.out.println("\nSorted by Position");
                for (Integer employeeID : eDatabase.keySet()) {
                    searchString = (String)(eDatabase.get(employeeID).getPosition() + eDatabase.get(employeeID).getLastName() + eDatabase.get(employeeID).getFirstName()
                        + eDatabase.get(employeeID).getMiddleName()) + String.valueOf(employeeID);
                    sortKey.put(searchString  , employeeID);
                }
                viewDataClass.sortBy(keyID, sortKey);
                viewDataClass.showSortEmployeeData(keyID, sortKey, eDatabase);
            } else if (sortChoice == EMPLOYEEID) {
                System.out.println("\nSorted by EmployeeID");
                for (Integer employeeID : eDatabase.keySet()) {
                    searchString = String.valueOf(employeeID) + (String)(eDatabase.get(employeeID).getLastName() + eDatabase.get(employeeID).getFirstName()
                        + eDatabase.get(employeeID).getMiddleName());
                    sortKey.put(searchString  , employeeID);
                }
                    viewDataClass.sortBy(keyID, sortKey);
                    viewDataClass.showSortEmployeeData(keyID, sortKey, eDatabase);
            } else{ 
                exitSortOption = false; 
                System.out.println("\nInvalid Input"); 
            }
        }
    }

    public List<String> sortBy (List<String> searchKey, Map<String, Integer> keyMap) {
        for (String KeyVar : keyMap.keySet()) { 
            searchKey.add(KeyVar);
        }
        return searchKey;
    }


}
