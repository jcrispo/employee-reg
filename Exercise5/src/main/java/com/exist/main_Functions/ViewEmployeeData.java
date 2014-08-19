package com.exist.main_Functions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.Scanner;
import com.exist.menu.sort_Menu.Sort_Menu;
import com.exist.menu.sort_Menu.Sort_Options;

public class ViewEmployeeData {
    private static Database companyDatabase;
    private static ResultSet data;
    private static Sort_Menu sort;
    private static Scanner input;
    private static String userChoice;
    private static boolean exitViewMenu;
    private static boolean invalidSearchParameter;
    private static final String SALARY = "Salary";
    private static final String EMAIL = "E-Mail";
    private static final String HIREDATE = "Hire Date";
    private static final String DEPARTMENT = "Department";
    private static final String POSITION = "Position";
    private static final String BIRTHDATE = "Birth Date";
    private static final String GENDER = "Gender";
    private static final String NAME = "Employee Name";
    private static final String FNAME = "First Name";
    private static final String MNAME = "Middle Name";
    private static final String LNAME = "Last Name";
    private static final String ID = "ID ";
    private static final String NXTLINE = "\n";
    private static final String COMPLETEDISPLAYFORMAT = "|%7s| %-20s| %-20s| %-20s| %-7s| %-11s| %-11s| %-16s| %-21s | %-10s | %-10s |\n";
    private static final String POSITIONDISPLAYFORMAT = "|%7s| %-20s| %-21s |\n";
    private static final String DEPARTMENTDISPLAYFORMAT = "|%7s| %-21s |\n";
    private static final String ALLDATAQUERY = "SELECT personalInfo.employeeId, personalInfo.firstName, personalInfo.middleName, personalInfo.lastName, personalInfo.birthDate, personalInfo.gender, company.hireDate, company.position_name, company.dept_name, company.basicSalary, company.emailId FROM personalInfo LEFT JOIN (SELECT companyEmployeeData.employeeId, companyEmployeeData.hireDate, posdept.position_refId, posdept.position_name,  posdept.dept_name, companyEmployeeData.basicSalary, companyEmployeeData.emailId FROM companyEmployeeData LEFT JOIN (SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition LEFT JOIN departments ON employeePosition.deptId=departments.deptId) AS posdept ON companyEmployeeData.position_refId=posdept.position_refId) AS company ON personalInfo.employeeId=company.employeeId";
    private static final String QUERYPOSITIONS = "SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition LEFT JOIN departments ON employeePosition.deptId = departments.deptId";
    private static final String QUERYDEPARTMENTS = "SELECT * FROM departments";

    public ViewEmployeeData () {
        companyDatabase = new Database();
        input = new Scanner(System.in);
        data = null;
        exitViewMenu = false;
        invalidSearchParameter = false;
    }

    public void viewEmployeeInformation () {
        ViewEmployeeData view = new ViewEmployeeData();
        while (!exitViewMenu) {
            System.out.println("Sort by:\t(1) ID\t\t(2) First Name\t(3) Middle Name\n\t\t(4) Last Name\t(5) Birth Date\t(6) Hire Date\n\t\t(7) Position\t(8) Department\t(9) Salary");
            System.out.print("Enter number of Sort Parameter: ");
            userChoice = input.nextLine().trim();
            sort = Sort_Options.sortBy(userChoice);
            exitViewMenu = sort.exitViewMenu();
        }
        companyDatabase.loginDatabase();
        view.showData(ALLDATAQUERY + sort.parameter());
        try {
            if (!companyDatabase.getData(ALLDATAQUERY).isBeforeFirst()) {
                System.out.println("Database is Empty!");
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        companyDatabase.closeDatabase();
    }

    public void showData (String query) {
        data = companyDatabase.getData(query);
        try {
                System.out.format(NXTLINE + COMPLETEDISPLAYFORMAT + NXTLINE, ID, FNAME, MNAME, LNAME, GENDER, BIRTHDATE, HIREDATE, POSITION, DEPARTMENT, SALARY, EMAIL);
            if (!data.isBeforeFirst()){
                System.out.println("\nNo Result Found!");
                invalidSearchParameter = true;
            }
            while (data.next()) {
                String employeeId  = data.getString("personalInfo.employeeId");
                String fName = data.getString("personalInfo.firstName");
                String mName = data.getString("personalInfo.middleName");
                String lName = data.getString("personalInfo.lastName");
                String gender = data.getString("personalInfo.gender");
                String birthDate = data.getString("personalInfo.birthDate");
                String hireDate = data.getString("company.hireDate");
                String position = data.getString("company.position_name");
                String department = data.getString("company.dept_name");
                String salary = data.getString("company.basicSalary");
                String email = data.getString("company.emailId");
                System.out.format(COMPLETEDISPLAYFORMAT, employeeId + " ", fName, mName, lName, gender, birthDate, hireDate, position, department, salary, email);
            }
            data.close();
            data = null;
        } catch (SQLException e) {
            System.out.println("Error retrieving data from Company Database");
        }
    }

    public void showPositions (String query) {
        companyDatabase.loginDatabase();
        data = companyDatabase.getData(query);
        try {
           if (!data.isBeforeFirst()){
                invalidSearchParameter = true;
           } else {
                System.out.format(NXTLINE + POSITIONDISPLAYFORMAT + NXTLINE, ID, POSITION, DEPARTMENT);
            }
            while (data.next()) {
                String id  = data.getString("employeePosition.position_refId");
                String position = data.getString("employeePosition.position_name");
                String department = data.getString("departments.dept_name");
                System.out.format(POSITIONDISPLAYFORMAT, id + " ", position, department);
            }
            data.close();
            data = null;
        } catch (SQLException e) {
            System.out.println("Error retrieving data from Company Database");
        }
        companyDatabase.closeDatabase();
    }

    public void showDepartments (String query) {
        //companyDatabase.loginDatabase();
        data = companyDatabase.getData(query);
        try {
           if (!data.isBeforeFirst()){
                invalidSearchParameter = true;
           } else {
                System.out.format(NXTLINE + DEPARTMENTDISPLAYFORMAT + NXTLINE, ID, DEPARTMENT);
            }
            while (data.next()) {
                String id  = data.getString("departments.deptId");
                String department = data.getString("departments.dept_name");
                System.out.format(DEPARTMENTDISPLAYFORMAT, id + " ", department);
            }
            //data.close();
            //data = null;
        } catch (SQLException e) {
            System.out.println("Error retrieving data from Company Database");
        }
        //companyDatabase.closeDatabase();
    }

    public void viewSortedPosition () {
        ViewEmployeeData view = new ViewEmployeeData();
        String positionSort = " ";
        boolean exit = false;
        while (!exit) {
            System.out.print("Sort by (1) Position Name or (2) Department Name: ");
            userChoice = input.nextLine().trim();
            exit = true;
            if (userChoice.equals("1")) {
                positionSort = " ORDER BY employeePosition.position_name ASC";
            } else if (userChoice.equals("2")) {
                positionSort = " ORDER BY departments.dept_name ASC";
            } else {
                System.out.println("Invalid input!");
                exit = false;
           }
        }
        companyDatabase.loginDatabase();
        view.showPositions(QUERYPOSITIONS + positionSort);
        companyDatabase.closeDatabase();
    }

    public void viewSortedDepartments () {
        ViewEmployeeData view = new ViewEmployeeData();
        String departmentSort = " ";
        boolean exit = false;
        while (!exit) {
            System.out.print("Sort (1) Ascending or (2) Descending: ");
            userChoice = input.nextLine().trim();
            exit = true;
            if (userChoice.equals("1")) {
                departmentSort = " ORDER BY departments.dept_name ASC";
            } else if (userChoice.equals("2")) {
                departmentSort = " ORDER BY departments.dept_name DESC";
            } else {
                System.out.println("Invalid input!");
                exit = false;
           }
        }
        companyDatabase.loginDatabase();//delete
        view.showDepartments(QUERYDEPARTMENTS + departmentSort);
        companyDatabase.closeDatabase();//delete
    }

    public String getAllDataQueryStatement () {
        return ALLDATAQUERY;
    }

    public boolean invalidSearch () {
        return invalidSearchParameter;
    }

    public void setInvalidSearchToDefault () {
        invalidSearchParameter = false;
    }

}
