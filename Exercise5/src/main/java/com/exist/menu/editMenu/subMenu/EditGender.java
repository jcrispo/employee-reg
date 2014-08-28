package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.database.DBInsert;

public class EditGender extends EditMenu {
    private static final String updateSql = "UPDATE personalInfo SET gender = \'";
    private static final String conditionSql = "\' WHERE personalInfo.employeeId = ";

    public void execute (String employeeNumberInput) {
        Scanner input = new Scanner(System.in);
        DBInsert dbInsert = new DBInsert();
        EmployeeDataValidation validate = new EmployeeDataValidation();

        String userInput = validate.gender("Gender 'm'/'f': ");
        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu () {
        return false;
    }

}
