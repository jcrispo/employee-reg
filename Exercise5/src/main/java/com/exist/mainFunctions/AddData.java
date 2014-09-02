package com.exist.mainFunctions;

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
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

    public AddData() {
        validate = new EmployeeDataValidation();
        view = new ViewEmployeeData();
        employee = new EmployeeData();
        positionList = new CompanyData();
        inDB = new DBInsert();
    }

    public void addEmployeePersonalData() {
        System.out.println("Register");

        employee.setFirstName(validate.name("First Name: "));
        employee.setMiddleName(validate.name("Middle Name: "));
        employee.setLastName(validate.name("Last Name: "));
        employee.setGender(validate.gender("Gender m/f: "));
        employee.setBirthDate(validate.dateString("Birth Date: "));
        employee.setHireDate(validate.dateString("Hire Date: "));

        view.showDataNoLimit(QUERYPOSITIONS);
        System.out.println("Enter only the Position ID.");
        employee.setPosition(Integer.valueOf(validate.numericDataExists("Position: ", QUERYPOSITIONDATA + POSNUMCONDITION)));

        employee.setSalary(Integer.valueOf(validate.number("Salary: ")));
        employee.setEmail(validate.email("E-Mail Address: "));

        inDB.insertEmployeeData(employee);
    }

    public void addNewPosition() {
        view.showDataNoLimit(QUERYPOSITIONS);
        positionList.setPositionName(validate.newWordData("Position: ", QUERYPOSITIONDATA + POSNAMECONDITION));

        view.showDataNoLimit(QUERYDEPARTMENTS);
        positionList.setDeptId(Integer.valueOf(validate.numericDataExists("Department: ", QUERYDEPARTMENTS + DEPTNUMCONDITION)));

        inDB.insertPosition(positionList);
    }

    public void addNewDepartment() {
        view.showDataNoLimit(QUERYDEPARTMENTS);
        String departmentName = validate.newWordData("Department: ", QUERYDEPARTMENTS + DEPTNAMECONDITION);

        inDB.insertNewDept(departmentName);
    }

    public void addRandomData() {
        Random random = new Random();

        String[] gender = {"male", "female"};
        String[] hireDate = {"2010-05-13", "2013-09-01", "2014-01-29", "2005-04-07", "2003-08-25", "2007-07-17", "2012-03-11", "2000-12-03", "2009-07-06", "2004-10-23", "2002-06-04"};

        Integer[] positionNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 11, 12, 13};

        String path1 = "src/main/resources/firstnames.txt";
        String path2 = "src/main/resources/middlenames.txt";
        String path3 = "src/main/resources/lastnames.txt";
        String path4 = "src/main/resources/birthdates.txt";

        try {
            int emailNumber = 0;

            File file1 = new File(path1);
            FileReader fr1 = new FileReader(file1);
            BufferedReader br1 = new BufferedReader(fr1);
            for (String firstName; (firstName = br1.readLine()) != null; ) {
                File file2 = new File(path2);
                FileReader fr2 = new FileReader(file2);
                BufferedReader br2 = new BufferedReader(fr2);

                for (String middleName; (middleName = br2.readLine()) != null; ) {
                    File file3 = new File(path3);
                    FileReader fr3 = new FileReader(file3);
                    BufferedReader br3 = new BufferedReader(fr3);

                    for (String lastName; (lastName = br3.readLine()) != null; ) {
                        File file4 = new File(path4);
                        FileReader fr4 = new FileReader(file4);
                        BufferedReader br4 = new BufferedReader(fr4);

                        System.out.println(emailNumber);

                        for (String birthDate; (birthDate = br4.readLine()) != null; ) {
                            emailNumber++;

                            String email = firstName.substring(0,1) + middleName.substring(0,1) + lastName.substring(0,1) + emailNumber;

                            System.out.println(firstName + " " + middleName + " " + lastName + " " + birthDate);
                            employee.setFirstName(firstName);
                            employee.setMiddleName(middleName);
                            employee.setLastName(lastName);
                            employee.setGender(gender[random.nextInt(gender.length)]);
                            employee.setBirthDate(birthDate);
                            employee.setHireDate(hireDate[random.nextInt(hireDate.length)]);
                            employee.setPosition(positionNum[random.nextInt(positionNum.length)]);
                            employee.setSalary(10000 + random.nextInt(20000));
                            employee.setEmail(email);

                            inDB.insertEmployeeData(employee);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
