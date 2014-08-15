package com.exist.menu.edit_Menu.sub_Menu;

import java.util.Scanner;
import com.exist.main_Functions.EmployeeDataValidation;
import com.exist.main_Functions.Database;
import com.exist.main_Functions.ViewEmployeeData;

public class Edit_Position extends EditMenu {
    private static EmployeeDataValidation validate;
    private static ViewEmployeeData view;
    private static Database companyDatabase;
    private static Scanner input;
    private static String userInput;
    private static final String updateSql = "UPDATE companyEmployeeData SET position_refId = \'";
    private static final String conditionSql = "\' WHERE companyEmployeeData.employeeId = ";
    private static final String POS_NUM_CONDITION = " WHERE position_refId = ";
    private static final String QUERY_POSITION_DATA = "SELECT position_refId, position_name, deptId FROM employeePosition";
    private static final String QUERY_POSITIONS = "SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition LEFT JOIN departments ON employeePosition.deptId = departments.deptId ORDER BY employeePosition.position_name ASC";

    public Edit_Position () {
        companyDatabase = new Database();
        input = new Scanner(System.in);
        validate = new EmployeeDataValidation();
        view = new ViewEmployeeData();
    }

    public void execute (String userInput, String employeeNumberInput) {
        companyDatabase.loginDatabase();
        view.showPositions(QUERY_POSITIONS);
        System.out.println("Enter only the Position ID.");
        userInput = validate.numericDataExists("Position: ", QUERY_POSITION_DATA + POS_NUM_CONDITION);
        companyDatabase.insertStatement(updateSql + userInput + conditionSql + employeeNumberInput);
        companyDatabase.updateDatabase();
        companyDatabase.closeDatabase();
    }

    public boolean exitEditMenu () {
        return false;
    }

}
