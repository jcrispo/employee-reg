package com.exist.editDeletePackage;

import com.exist.employeeInfoPackage.EmployeeDataValidation;
import com.exist.employeeInfoPackage.EmployeeData;
import com.exist.addViewPackage.ViewEmployeeData;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DeleteEmployeeData{
    private static Scanner inputDelete;
    private static DateFormat dateFormat;
    private static EmployeeDataValidation validate;
    private static ViewEmployeeData viewData;
    private static String confirmDelete;

    public DeleteEmployeeData () {
        inputDelete = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("dd/MM/YYYY - HH:mm");
        validate = new EmployeeDataValidation();
        viewData = new ViewEmployeeData();
    }

    public Map<Integer, EmployeeData> deleteEmpData (Map<Integer, EmployeeData> employeeDatabase) {
        Integer idKey;
        String userInputOption;
        int deleteOption=0;
        boolean exitDelete = false;
        boolean exitConfirmDelete = false;

        while (!exitDelete) {
            System.out.print("\nInput Employee ID to Delete the Employee Information or input 'm' Go Back to Menu: ");
            userInputOption = (inputDelete.nextLine()).trim();
            if (userInputOption.equals("m")) {
                exitDelete = true;
            } else if (validate.onlyNumbers(userInputOption)) {
                try {
                    idKey = Integer.valueOf(userInputOption);
                    viewData.viewEmployeeByID(idKey, employeeDatabase);
                    System.out.println("\nAre you sure you want to delete this data?");
                    while (!exitConfirmDelete) {
                        System.out.print("Confirm Delete (y/n): ");
                        confirmDelete = inputDelete.nextLine().trim();
                        if (confirmDelete.equals("y")) {
                            employeeDatabase.remove(idKey); 
                            exitConfirmDelete = true;
                            exitDelete = true;
                        } else if (confirmDelete.equals("n")) {
                            exitConfirmDelete = true;
                            exitDelete = true;
                        } else { 
                            System.out.println("Invalid Input! Choose between 'y' and 'n'");
                        } 
                    }
                } catch (NullPointerException e) {
                    System.out.println("\nEmployee ID does not exist");
                }
            }
        }
        return employeeDatabase;
    }

}
