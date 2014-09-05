package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.mainFunctions.ViewEmployeeData;
import com.exist.database.DBRetrieve;
import com.exist.database.DBInsert;

public class EditPosition extends EditMenu {
    private static final String updateSql = "UPDATE companyEmployeeData SET position_refId = \'";
    private static final String conditionSql = "\' WHERE companyEmployeeData.employeeId = ";

    public void execute(String employeeNumberInput) {
        EmployeeDataValidation validate = new EmployeeDataValidation();
        ViewEmployeeData view = new ViewEmployeeData();
        DBInsert dbInsert = new DBInsert();

        view.showDataNoLimit(DBRetrieve.getQuery("SELECT_ALL_employeePosition_JOIN_departments_ORDERBY_Position"));

        System.out.println("Enter only the Position ID.");

        String condition = " WHERE position_refId = ";
        String userInput = validate.numericDataExists("Position: ", DBRetrieve.getQuery("SELECT_ALL_employeePosition") + condition);

        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu() {
        return false;
    }

}
