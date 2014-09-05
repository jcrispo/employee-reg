package com.exist.mainFunctions;

import java.util.Scanner;
import com.exist.database.DBInsert;
import com.exist.database.DBRetrieve;

public class AddData {
    private EmployeeDataValidation validate;
    private ViewEmployeeData view;
    private DBInsert inDB;

    public AddData() {
        validate = new EmployeeDataValidation();
        view = new ViewEmployeeData();
        inDB = new DBInsert();
    }

    public void addEmployeePersonalData() {
        EmployeeData employee = new EmployeeData();

        System.out.println("Register");

        employee.setFirstName(validate.name("First Name: "));
        employee.setMiddleName(validate.name("Middle Name: "));
        employee.setLastName(validate.name("Last Name: "));
        employee.setGender(validate.gender("Gender m/f: "));
        employee.setBirthDate(validate.dateString("Birth Date: "));
        employee.setHireDate(validate.dateString("Hire Date: "));

        view.showDataNoLimit(DBRetrieve.getQuery("SELECT_ALL_employeePosition_JOIN_departments_ORDERBY_Position"));

        System.out.println("Enter only the Position ID.");

        String condition = " WHERE position_refId = ";
        employee.setPosition(Integer.valueOf(validate.numericDataExists("Position: ", DBRetrieve.getQuery("SELECT_ALL_employeePosition") + condition)));

        employee.setSalary(Integer.valueOf(validate.number("Salary: ")));
        employee.setEmail(validate.email("E-Mail Address: "));

        inDB.insertEmployeeData(employee);
    }

    public void addNewPosition() {
        CompanyData positionList = new CompanyData();
        String condition = new String();

        view.showDataNoLimit(DBRetrieve.getQuery("SELECT_ALL_employeePosition_JOIN_departments_ORDERBY_Position"));

        condition = " WHERE position_name = \'";
        positionList.setPositionName(validate.newWordData("Position: ", DBRetrieve.getQuery("SELECT_ALL_employeePosition") + condition));

        view.showDataNoLimit(DBRetrieve.getQuery("SELECT_ALL_departments"));

        condition = " WHERE deptId = ";
        positionList.setDeptId(Integer.valueOf(validate.numericDataExists("Department: ", DBRetrieve.getQuery("SELECT_ALL_departments") + condition)));

        inDB.insertPosition(positionList);
    }

    public void addNewDepartment() {
        view.showDataNoLimit(DBRetrieve.getQuery("SELECT_ALL_departments"));

        String condition = " WHERE dept_name = \'";
        String departmentName = validate.newWordData("Department: ", DBRetrieve.getQuery("SELECT_ALL_departments") + condition);

        inDB.insertNewDept(departmentName);
    }

}
