package com.exist.menu.edit_Menu.sub_Menu;

import java.util.Scanner;
import com.exist.main_Functions.EmployeeDataValidation;
import com.exist.main_Functions.Database;

public class Edit_MiddleName extends EditMenu {
    private static EmployeeDataValidation validate;
    private static Database companyDatabase;
    private static Scanner input;
    private static String userInput;
    private static final String updateSql = "UPDATE personalInfo SET middleName = \'";
    private static final String conditionSql = "\' WHERE personalInfo.employeeId = ";

    public Edit_MiddleName () {
        companyDatabase = new Database();
        input = new Scanner(System.in);
        validate = new EmployeeDataValidation();
    }

    public void execute (String userInput, String employeeNumberInput) {
        companyDatabase.loginDatabase();
        userInput = validate.name("Middle Name: ");
        companyDatabase.insertStatement(updateSql + userInput + conditionSql + employeeNumberInput + ";");
        companyDatabase.updateDatabase();
        companyDatabase.closeDatabase();
    }

    public boolean exitEditMenu () {
        return false;
    }

}