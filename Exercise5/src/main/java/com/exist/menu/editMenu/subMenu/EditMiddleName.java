package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.database.DBInsert;

public class EditMiddleName extends EditMenu {
    private static final String updateSql = "UPDATE personalInfo SET middleName = \'";
    private static final String conditionSql = "\' WHERE personalInfo.employeeId = ";

    public void execute(String employeeNumberInput) {
        EmployeeDataValidation validate = new EmployeeDataValidation();
        DBInsert dbInsert = new DBInsert();

        String userInput = validate.name("Middle Name: ");

        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu() {
        return false;
    }

}
