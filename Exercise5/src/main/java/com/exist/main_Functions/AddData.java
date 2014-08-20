package com.exist.main_Functions;

import java.util.Scanner;

public class AddData {
    private Database companyDatabase;
    private EmployeeDataValidation validate;
    private ViewEmployeeData view;
    private InputValidation validation;
    private EmployeeData employee;
    private PositionData positionList;
    private Insert toDatabase;
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
        employee = new EmployeeData();
        positionList = new PositionData();
        toDatabase = new Insert();
    }

    public void addEmployeePersonalData () {
        System.out.println("Register");
        employee.setFirstName(validate.name("First Name: "));
        employee.setMiddleName(validate.name("Middle Name: "));
        employee.setLastName(validate.name("Last Name: "));
        employee.setGender(validate.gender("Gender m/f: "));
        employee.setBirthDate(validate.dateString("Birth Date: "));
        employee.setHireDate(validate.dateString("Hire Date: "));
        view.showPositions(QUERY_POSITIONS);
        System.out.println("Enter only the Position ID.");
        employee.setPosition(Integer.valueOf(validate.numericDataExists("Position: ", QUERY_POSITION_DATA + POS_NUM_CONDITION)));
        employee.setSalary(Integer.valueOf(validate.number("Salary: ")));
        employee.setEmail(validate.email("E-Mail Address: "));
        toDatabase.insertEmployeeData(employee);
    }

    public void addNewPosition () {
        view.showPositions(QUERY_POSITIONS);
        positionList.addPositionName(validate.newWordData("Position: ", QUERY_POSITION_DATA + POS_NAME_CONDITION));
        view.showDepartments(QUERY_DEPARTMENTS);
        positionList.setDeptId(Integer.valueOf(validate.numericDataExists("Department: ", QUERY_DEPARTMENTS + DEPT_NUM_CONDITION)));
        toDatabase.insertPosition(positionList);
    }

    public void addNewDepartment () {
        view.showDepartments(QUERY_DEPARTMENTS);
        String departmentName = validate.newWordData("Department: ", QUERY_DEPARTMENTS + DEPT_NAME_CONDITION);
        toDatabase.insertNewDept(departmentName);
    }

}
