package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.database.DBInsert;

public class EditHireDate extends EditMenu {
    private static final String updateSql = "UPDATE companyEmployeeData SET hireDate = \'";
    private static final String conditionSql = "\' WHERE companyEmployeeData.employeeId = ";

    public void execute (String employeeNumberInput) {
        Scanner input = new Scanner(System.in);
        DBInsert dbInsert = new DBInsert();
        EmployeeDataValidation validate = new EmployeeDataValidation();

        String userInput = validate.dateString("Hire Date: ");
        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu () {
        return false;
    }

}
