package com.exist.menu.editMenu.subMenu;

import java.util.Scanner;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.database.DBInsert;
import com.exist.mainFunctions.ViewEmployeeData;

public class EditPosition extends EditMenu {
    private static EmployeeDataValidation validate;
    private static ViewEmployeeData view;
    private static DBInsert dbInsert;
    private static Scanner input;
    private static String userInput;
    private static final String updateSql = "UPDATE companyEmployeeData SET position_refId = \'";
    private static final String conditionSql = "\' WHERE companyEmployeeData.employeeId = ";
    private static final String POS_NUM_CONDITION = " WHERE position_refId = ";
    private static final String QUERY_POSITION_DATA = "SELECT position_refId, position_name, deptId FROM employeePosition";
    private static final String QUERY_POSITIONS = "SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition"
      + " LEFT JOIN departments ON employeePosition.deptId = departments.deptId ORDER BY employeePosition.position_name ASC";

    public EditPosition () {
        dbInsert = new DBInsert();
        input = new Scanner(System.in);
        validate = new EmployeeDataValidation();
        view = new ViewEmployeeData();
    }

    public void execute (String userInput, String employeeNumberInput) {
        view.showPositions(QUERY_POSITIONS);
        System.out.println("Enter only the Position ID.");
        userInput = validate.numericDataExists("Position: ", QUERY_POSITION_DATA + POS_NUM_CONDITION);
        dbInsert.editData(updateSql + userInput + conditionSql + employeeNumberInput);
    }

    public boolean exitEditMenu () {
        return false;
    }

}
