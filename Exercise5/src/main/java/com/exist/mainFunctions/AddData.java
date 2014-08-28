package com.exist.mainFunctions;

import java.util.Scanner;
import com.exist.database.DBInsert;

public class AddData {
    private EmployeeDataValidation validate;
    private ViewEmployeeData view;
    private EmployeeData employee;
    private CompanyData positionList;
    private DBInsert inDB;
    private static final String sqlStatement1 = "INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlStatement2 = "INSERT INTO companyEmployeeData (position_refId, hireDate, basicSalary, emailId) VALUES (?, ?, ?, ?)";
    private static final String sqlStatement3 = "INSERT INTO employeePosition (position_name, deptId) VALUES (?, ?)";
    private static final String sqlStatement4 = "INSERT INTO departments (dept_name) VALUES (?)";
    private static final String POSNUMCONDITION = " WHERE position_refId = ";
    private static final String DEPTNUMCONDITION = " WHERE deptId = ";
    private static final String DEPTNAMECONDITION = " WHERE dept_name = \'";
    private static final String POSNAMECONDITION = " WHERE position_name = \'";
    private static final String QUERYDEPARTMENTS = "SELECT deptId, dept_name FROM departments";
    private static final String QUERYPOSITIONDATA = "SELECT position_refId, position_name, deptId FROM employeePosition";
    private static final String QUERYPOSITIONS = "SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition LEFT JOIN departments ON employeePosition.deptId = departments.deptId ORDER BY employeePosition.position_name ASC";

    public AddData () {
        validate = new EmployeeDataValidation();
        view = new ViewEmployeeData();
        employee = new EmployeeData();
        positionList = new CompanyData();
        inDB = new DBInsert();
    }

    public void addEmployeePersonalData () {
        System.out.println("Register");

        employee.setFirstName(validate.name("First Name: "));
        employee.setMiddleName(validate.name("Middle Name: "));
        employee.setLastName(validate.name("Last Name: "));
        employee.setGender(validate.gender("Gender m/f: "));
        employee.setBirthDate(validate.dateString("Birth Date: "));
        employee.setHireDate(validate.dateString("Hire Date: "));

        view.showPositions(QUERYPOSITIONS);
        System.out.println("Enter only the Position ID.");
        employee.setPosition(Integer.valueOf(validate.numericDataExists("Position: ", QUERYPOSITIONDATA + POSNUMCONDITION)));

        employee.setSalary(Integer.valueOf(validate.number("Salary: ")));
        employee.setEmail(validate.email("E-Mail Address: "));

        inDB.insertEmployeeData(employee);
    }

    public void addNewPosition () {
        view.showPositions(QUERYPOSITIONS);
        positionList.setPositionName(validate.newWordData("Position: ", QUERYPOSITIONDATA + POSNAMECONDITION));

        view.showDepartments(QUERYDEPARTMENTS);
        positionList.setDeptId(Integer.valueOf(validate.numericDataExists("Department: ", QUERYDEPARTMENTS + DEPTNUMCONDITION)));

        inDB.insertPosition(positionList);
    }

    public void addNewDepartment () {
        view.showDepartments(QUERYDEPARTMENTS);
        String departmentName = validate.newWordData("Department: ", QUERYDEPARTMENTS + DEPTNAMECONDITION);

        inDB.insertNewDept(departmentName);
    }

}
