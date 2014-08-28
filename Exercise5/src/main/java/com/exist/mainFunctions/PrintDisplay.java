package com.exist.mainFunctions;

import java.util.Formatter;
import java.util.List;

public class PrintDisplay {
    private static final String ID = "ID ";
    private static final String NAME = "Employee Name";
    private static final String FNAME = "First Name";
    private static final String MNAME = "Middle Name";
    private static final String LNAME = "Last Name";
    private static final String GENDER = "Gender";
    private static final String BIRTHDATE = "Birth Date";
    private static final String HIREDATE = "Hire Date";
    private static final String POSITION = "Position";
    private static final String SALARY = "Salary";
    private static final String EMAIL = "E-Mail";
    private static final String DEPARTMENT = "Department";
    private static final String NXTLINE = "\n";
    private static final String COMPLETEDISPLAYFORMAT = "|%7s| %-20s| %-20s| %-20s| %-7s| %-11s| %-11s| %-16s| %-21s | %-10s | %-10s |\n";
    private static final String POSITIONDISPLAYFORMAT = "|%7s| %-20s| %-21s |\n";
    private static final String DEPARTMENTDISPLAYFORMAT = "|%7s| %-21s |\n";

    public void printHeader () {
        System.out.format(NXTLINE + COMPLETEDISPLAYFORMAT + NXTLINE, ID, FNAME, MNAME, LNAME, GENDER, BIRTHDATE, HIREDATE, POSITION, DEPARTMENT, SALARY, EMAIL);
    }

    public void printPositionHeader () {
        System.out.format(NXTLINE + POSITIONDISPLAYFORMAT + NXTLINE, ID, POSITION, DEPARTMENT);
    }

    public void printDepartmentHeader () {
        System.out.format(NXTLINE + DEPARTMENTDISPLAYFORMAT + NXTLINE, ID, DEPARTMENT);
    }

    public void printEmployeeData (EmployeeData employee) {
        System.out.format("|%7s", employee.getEmployeeId() + " ");
        System.out.format("| %-20s", employee.getFirstName());
        System.out.format("| %-20s", employee.getMiddleName());
        System.out.format("| %-20s", employee.getLastName());
        System.out.format("| %-7s", employee.getGender());
        System.out.format("| %-11s", employee.getBirthDate());
        System.out.format("| %-11s", employee.getHireDate());
        System.out.format("| %-16s", employee.getPositionName());
        System.out.format("| %-21s", employee.getDepartmentName());
        System.out.format(" | %-10s", employee.getSalary());
        System.out.format(" | %-10s", employee.getEmail());
        System.out.println(" |");
    }

    public void printPositions (CompanyData positionList) {
        System.out.format("|%7s", positionList.getPositionId() + " ");
        System.out.format("| %-20s", positionList.getPositionName());
        System.out.format("| %-21s", positionList.getDepartmentName());
        System.out.println(" |");
    }

    public void printDepartments (CompanyData departmentList) {
        System.out.format("|%7s", departmentList.getDeptId() + " ");
        System.out.format("| %-21s", departmentList.getDepartmentName());
        System.out.println(" |");
    }

    public void printDatabaseData (List<List<String>> databaseData) {
        for (List<String> columnData : databaseData) {
            for (String data : columnData) {
                System.out.format("| %-15s", data);
            }
            System.out.println();
        }
    }

}
