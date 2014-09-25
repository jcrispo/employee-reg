package com.exist.mainFunctions;

import java.util.Date;
import com.exist.database.HDBInsertManager;
import com.exist.storage.EmployeeData;

import static com.exist.database.HDBRetrieveManager.getQuery;

public class AddData {
    private EmployeeDataValidation validate;
    private ViewEmployeeData view;
    private HDBInsertManager hdbIn;

    public AddData() {
        validate = new EmployeeDataValidation();
        view = new ViewEmployeeData();
        hdbIn = new HDBInsertManager();
    }

    public void addEmployeePersonalData() {
        EmployeeData employee = new EmployeeData();

        System.out.println("Register");

        String firstName = validate.name("First Name: ");
        String middleName = validate.name("Middle Name: ");
        String lastName = validate.name("Last Name: ");
        String gender = validate.gender("Gender m/f: ");
        Date birthDate = validate.dateString("Birth Date: ");
        Date hireDate = validate.dateString("Hire Date: ");

        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setBirthDate(birthDate);
        employee.setHireDate(hireDate);

        view.showQueryNoLimit(getQuery("S_ALL_Positions_JOIN_Departments_ORDER_position"));

        System.out.println("Enter only the Position ID.");
        Integer positionID = Integer.valueOf(validate.numericDataExists("Position: ", getQuery("FROM_Positions_WHERE_ID")));

        Integer salary = Integer.valueOf(validate.number("Salary: "));
        String email = validate.email("E-Mail Address: ");

        employee.setPosition(positionID);
        employee.setSalary(salary);
        employee.setEmail(email);

        hdbIn.insertEmployeeData(employee);
    }

    public void addNewPosition() {
        view.showQueryNoLimit(getQuery("S_ALL_Positions_JOIN_Departments_ORDER_position"));

        String newPosition = validate.newWordData("Position: ", getQuery("FROM_Positions_WHERE_position"));

        view.showQueryNoLimit(getQuery("S_ALL_Departments"));

        Integer deptId = Integer.valueOf(validate.numericDataExists("Department: ", getQuery("FROM_Departments_WHERE_ID")));

        hdbIn.insertPosition(newPosition,deptId);
    }

    public void addNewDepartment() {
        view.showQueryNoLimit(getQuery("S_ALL_Departments"));

        String departmentName = validate.newWordData("Department: ", getQuery("FROM_Departments_WHERE_department"));

        hdbIn.insertNewDept(departmentName);
    }

}
