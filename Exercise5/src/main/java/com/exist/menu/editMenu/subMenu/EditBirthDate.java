package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.database.DBInsert;

public class EditBirthDate extends EditMenu {
    private static EmployeeDataValidation validate;
    private static DBInsert dbInsert;
    private static Scanner input;
    private static String userInput;
    private static final String updateSql = "UPDATE personalInfo SET birthDate = \'";
    private static final String conditionSql = "\' WHERE personalInfo.employeeId = ";

    public EditBirthDate () {
        dbInsert = new DBInsert();
        input = new Scanner(System.in);
        validate = new EmployeeDataValidation();
    }

    public void execute (String userInput, String employeeNumberInput) {
        userInput = validate.dateString("Birthdate: ");
        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu () {
        return false;
    }

}
