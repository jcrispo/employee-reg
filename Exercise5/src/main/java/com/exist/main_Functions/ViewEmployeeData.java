package com.exist.main_Functions;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.Scanner;
import com.exist.menu.sort_Menu.Sort_Menu;
import com.exist.menu.sort_Menu.Sort_Options;

public class ViewEmployeeData {
    private DatabaseRetrieve retrieve;
    private static Connection dbConnection;
    private static Database companyDatabase;
    private static ResultSet data;
    private static Sort_Menu sort;
    private static Scanner input;
    private static String userChoice;
    private static String databaseUrl;
    private static String databaseUser;
    private static String databasePassword;
    private static boolean exitViewMenu;
    private static boolean invalidSearchParameter;
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
    private static final String QUERYDEPARTMENTS = "SELECT * FROM departments";
    private static final String QUERYPOSITIONS = "SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name"
      + " FROM employeePosition LEFT JOIN departments ON employeePosition.deptId = departments.deptId";
    private static final String ALLDATAQUERY = "SELECT personalInfo.employeeId, personalInfo.firstName, personalInfo.middleName, personalInfo.lastName, personalInfo.birthDate,"
      + " personalInfo.gender, company.hireDate, company.position_name, company.dept_name, company.basicSalary, company.emailId FROM personalInfo"
      + " LEFT JOIN (SELECT companyEmployeeData.employeeId, companyEmployeeData.hireDate, posdept.position_refId, posdept.position_name,  posdept.dept_name,"
      + " companyEmployeeData.basicSalary, companyEmployeeData.emailId FROM companyEmployeeData"
      + " LEFT JOIN (SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition"
      + " LEFT JOIN departments ON employeePosition.deptId=departments.deptId) AS posdept"
      + " ON companyEmployeeData.position_refId=posdept.position_refId) AS company"
      + " ON personalInfo.employeeId=company.employeeId";

    public ViewEmployeeData () {
        companyDatabase = new Database();
        retrieve = new DatabaseRetrieve();
        input = new Scanner(System.in);
        dbConnection = null;
        data = null;
        exitViewMenu = false;
        invalidSearchParameter = false;
        }

    public void setCredentials (String url, String user, String pass) {
        databaseUrl = url;
        databaseUser = user;
        databasePassword = pass;
    }

    public void viewEmployeeInformation () {
        ViewEmployeeData view = new ViewEmployeeData();
        boolean databaseIsEmpty = false;
        while (!exitViewMenu) {
            System.out.println("Sort by:\t(1) ID\t\t(2) First Name\t(3) Middle Name\n\t\t(4) Last Name\t(5) Birth Date\t(6) Hire Date\n\t\t(7) Position\t(8) Department\t(9) Salary");
            System.out.print("Enter number of Sort Parameter: ");
            userChoice = input.nextLine().trim();
            sort = Sort_Options.sortBy(userChoice);
            exitViewMenu = sort.exitViewMenu();
        }
        databaseIsEmpty = retrieve.resultIsEmpty(ALLDATAQUERY);
        view.showData(ALLDATAQUERY + sort.parameter());
        if (databaseIsEmpty) {
            System.out.println("Database is Empty!");
        }
    }

    public void showData (String query) {
        try {
            System.out.format(NXTLINE + COMPLETEDISPLAYFORMAT + NXTLINE, ID, FNAME, MNAME, LNAME, GENDER, BIRTHDATE, HIREDATE, POSITION, DEPARTMENT, SALARY, EMAIL);
            boolean emptyResult = retrieve.resultIsEmpty(query);
            if (emptyResult){
                System.out.println("\nNo Result Found!");
                invalidSearchParameter = true;
            }
            dbConnection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            data = retrieve.getData(dbConnection, query);
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
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showPositions (String query) {
        boolean emptyResult = retrieve.resultIsEmpty(query);
        if (emptyResult){
            System.out.println("\nNo Result Found!");
            invalidSearchParameter = true;
        } else {
            System.out.format(NXTLINE + POSITIONDISPLAYFORMAT + NXTLINE, ID, POSITION, DEPARTMENT);
        }
        try {
            dbConnection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            data = retrieve.getData(dbConnection, query);
            while (data.next()) {
                String id  = data.getString("employeePosition.position_refId");
                String position = data.getString("employeePosition.position_name");
                String department = data.getString("departments.dept_name");
                System.out.format(POSITIONDISPLAYFORMAT, id + " ", position, department);
            }
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving data from Company Database");
        }
    }

    public void showDepartments (String query) {
        boolean emptyResult = retrieve.resultIsEmpty(query);
        if (emptyResult){
            System.out.println("\nNo Result Found!");
            invalidSearchParameter = true;
        } else {
            System.out.format(NXTLINE + DEPARTMENTDISPLAYFORMAT + NXTLINE, ID, DEPARTMENT);
        }
        try {
            dbConnection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            data = retrieve.getData(dbConnection, query);
            while (data.next()) {
                String id  = data.getString("departments.deptId");
                String department = data.getString("departments.dept_name");
                System.out.format(DEPARTMENTDISPLAYFORMAT, id + " ", department);
            }
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving data from Company Database");
        }
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
        view.showPositions(QUERYPOSITIONS + positionSort);
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
        view.showDepartments(QUERYDEPARTMENTS + departmentSort);
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
