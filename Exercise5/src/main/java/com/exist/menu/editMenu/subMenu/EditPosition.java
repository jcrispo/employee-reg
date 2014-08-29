package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.mainFunctions.ViewEmployeeData;
import com.exist.database.DBRetrieve;
import com.exist.database.DBInsert;

public class EditPosition extends EditMenu {
    private static final String updateSql = "UPDATE companyEmployeeData SET position_refId = \'";
    private static final String conditionSql = "\' WHERE companyEmployeeData.employeeId = ";

    public void execute (String employeeNumberInput) {
        Scanner input = new Scanner(System.in);
        DBInsert dbInsert = new DBInsert();
        EmployeeDataValidation validate = new EmployeeDataValidation();
        ViewEmployeeData view = new ViewEmployeeData();

        view.showDataNoLimit(DBRetrieve.getQuery("queryPositions"));

        System.out.println("Enter only the Position ID.");

        String userInput = validate.numericDataExists("Position: ", DBRetrieve.getQuery("queryPositions.WhereIdIs"));
        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu () {
        return false;
    }

}
