package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.database.DBInsert;

public class EditGender extends EditMenu {
    private static EmployeeDataValidation validate;
    private static DBInsert dbInsert;
    private static Scanner input;
    private static String userInput;
    private static final String updateSql = "UPDATE personalInfo SET gender = \'";
    private static final String conditionSql = "\' WHERE personalInfo.employeeId = ";

    public EditGender () {
        dbInsert = new DBInsert();
        input = new Scanner(System.in);
        validate = new EmployeeDataValidation();
    }

    public void execute (String userInput, String employeeNumberInput) {
        userInput = validate.gender("Gender 'm'/'f': ");
        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu () {
        return false;
    }

}
