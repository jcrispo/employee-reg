package com.exist.main_Functions;

import java.util.Scanner;

public class AddData {
    private static Database companyDatabase;
    private static EmployeeDataValidation validate;
    private static ViewEmployeeData view;
    private static InputValidation validation;
    private static final String sqlStatement1 = "INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlStatement2 = "INSERT INTO companyEmployeeData (position_refId, hireDate, basicSalary, emailId) VALUES (?, ?, ?, ?)";
    private static final String sqlStatement3 = "INSERT INTO employeePosition (position_name, deptId) VALUES (?, ?)";
    private static final String sqlStatement4 = "INSERT INTO departments (dept_name) VALUES (?)";
    private static final String POS_NUM_CONDITION = " WHERE position_refId = ";
    private static final String DEPT_NUM_CONDITION = " WHERE deptId = ";
    private static final String DEPT_NAME_CONDITION = " WHERE dept_name = \'";
    private static final String POS_NAME_CONDITION = " WHERE position_name = \'";
    private static final String QUERY_DEPARTMENTS = "SELECT deptId, dept_name FROM departments";
    private static final String QUERY_POSITION_DATA = "SELECT position_refId, position_name, deptId FROM employeePosition";
    private static final String QUERY_POSITIONS = "SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition LEFT JOIN departments ON employeePosition.deptId = departments.deptId ORDER BY employeePosition.position_name ASC";

    public AddData () {
        companyDatabase = new Database();
        validation = new InputValidation();
        validate = new EmployeeDataValidation();
        view = new ViewEmployeeData();
    }

    public void addEmployeePersonalData () {
        companyDatabase.loginDatabase();
        System.out.println("Register");
        companyDatabase.insertStatement(sqlStatement1);
        companyDatabase.insertIntoDatabase(1, validate.name("First Name: "));
        companyDatabase.insertIntoDatabase(2, validate.name("Middle Name: "));
        companyDatabase.insertIntoDatabase(3, validate.name("Last Name: "));
        companyDatabase.insertIntoDatabase(4, validate.gender("Gender 'm'/'f': "));
        companyDatabase.insertIntoDatabase(5, validate.dateString("Birth Date: "));
        companyDatabase.updateDatabase();
        companyDatabase.closeDatabase();
    }

    public void addEmployeeCompanyData () {
        companyDatabase.loginDatabase();
        companyDatabase.insertStatement(sqlStatement2);
        view.showPositions(QUERY_POSITIONS);
        System.out.println("Enter only the Position ID.");
        companyDatabase.insertIntoDatabase(1, validate.numericDataExists("Position: ", QUERY_POSITION_DATA + POS_NUM_CONDITION ));
        companyDatabase.insertIntoDatabase(2, validate.dateString("Hire Date: "));
        companyDatabase.insertIntoDatabase(3, validate.number("Salary: "));
        companyDatabase.insertIntoDatabase(4, validate.email("E-Mail Address: "));
        companyDatabase.updateDatabase();
        companyDatabase.closeDatabase();
    }

    public void addNewDepartment () {
        companyDatabase.loginDatabase();
        view.showDepartments(QUERY_DEPARTMENTS);
        companyDatabase.insertStatement(sqlStatement4);
        companyDatabase.insertIntoDatabase(1, validate.newWordData("Department: ", QUERY_DEPARTMENTS + DEPT_NAME_CONDITION));
        companyDatabase.updateDatabase();
        companyDatabase.closeDatabase();
    }

    public void addNewPosition () {
        companyDatabase.loginDatabase();
        view.showPositions(QUERY_POSITIONS);
        companyDatabase.insertStatement(sqlStatement3);
        companyDatabase.insertIntoDatabase(1, validate.newWordData("Position: ", QUERY_POSITION_DATA + POS_NAME_CONDITION));
        view.showDepartments(QUERY_DEPARTMENTS);
        companyDatabase.insertIntoDatabase(2, validate.numericDataExists("Department: ", QUERY_DEPARTMENTS + DEPT_NUM_CONDITION ));
        companyDatabase.updateDatabase();
        companyDatabase.closeDatabase();
    }

}
