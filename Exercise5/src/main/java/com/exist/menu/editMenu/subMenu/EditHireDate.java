package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.database.DBInsert;

public class EditHireDate extends EditMenu {
    private static EmployeeDataValidation validate;
    private static DBInsert dbInsert;
    private static Scanner input;
    private static String userInput;
    private static final String updateSql = "UPDATE companyEmployeeData SET hireDate = \'";
    private static final String conditionSql = "\' WHERE companyEmployeeData.employeeId = ";

    public EditHireDate () {
        dbInsert = new DBInsert();
        input = new Scanner(System.in);
        validate = new EmployeeDataValidation();
    }

    public void execute (String userInput, String employeeNumberInput) {
        userInput = validate.dateString("Hire Date: ");
        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu () {
        return false;
    }

}
