package com.exist.mainFunctions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Formatter;
import java.util.Scanner;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.exist.menu.sortMenu.SortMenu;
import com.exist.menu.sortMenu.SortOptions;
import com.exist.database.DBRetrieve;
import com.exist.database.DBDataSource;
import com.exist.database.Retrieve;

public class ViewEmployeeData {
    private DBRetrieve retrieve;
    private Retrieve retrieveFromDb;
    private PrintDisplay print;
    private SortMenu sort;
    private Scanner input;
    private InputValidation validation;
    private static Connection dbConnection;
    private static ResultSet data;
    private static String userChoice;
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

    public ViewEmployeeData () {
        retrieve = new DBRetrieve();
        retrieveFromDb = new DBRetrieve();
        input = new Scanner(System.in);
        print = new PrintDisplay();
        validation = new InputValidation();

        dbConnection = null;
        data = null;
        exitViewMenu = false;
        invalidSearchParameter = false;
    }

    public void viewEmployeeInformation () {
        ViewEmployeeData view = new ViewEmployeeData();

        boolean databaseIsEmpty = false;

        while (!exitViewMenu) {
            System.out.println("Sort by:\t(1) ID\t\t(2) First Name\t(3) Middle Name\n\t\t(4) Last Name\t(5) Birth Date\t(6) Hire Date\n\t\t(7) Position\t(8) Department\t(9) Salary");
            System.out.print("Enter number of Sort Parameter: ");

            userChoice = input.nextLine().trim();

            sort = SortOptions.sortBy(userChoice);
            exitViewMenu = sort.exitViewMenu();
        }

        databaseIsEmpty = retrieve.resultIsEmpty(DBRetrieve.getQuery("queryAllData"));
        view.showData(DBRetrieve.getQuery("queryAllData") + sort.parameter());
        if (databaseIsEmpty) {
            System.out.println("Database is Empty!");
        }
    }

    public void showDataNoLimit (String query) {
        List<List<String>> databaseData = retrieveFromDb.getData(query);

        if (databaseData.isEmpty()){
            System.out.println("\nNo Result Found!");
            invalidSearchParameter = true;                
        } else {
            print.printDatabaseData(databaseData);
        }

    }

    public void showData (String query) {
        List<List<String>> databaseData = retrieveFromDb.getData(query);

        if (databaseData.isEmpty()){
            System.out.println("\nNo Result Found!");
            invalidSearchParameter = true;                
        } else {
            print.printDatabaseData(databaseData);
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
        view.showData(QUERYPOSITIONS + positionSort);
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
        view.showData(QUERYDEPARTMENTS + departmentSort);
    }

    public String fieldsToDisplay () {
        String fieldNumbers = "0";
        boolean exit = false;
        System.out.println("Choose which fields to print:\n\t\t(1) Id\t\t(2) Name\t(3) Gender\n\t\t(4) Birth Date\t(5) Hire Date\t(6) Position\n\t\t(7) Department\t(8) Salary\t(9) Email");
        while (!exit) {
        System.out.print("Enter the numbers with no spaces: ");
            userChoice = input.nextLine();
            if (validation.containsOnlyNumbers(userChoice)) {
                if (userChoice.contains("1")) {
                    fieldNumbers = fieldNumbers.concat("1");
                }
                if (userChoice.contains("2")) {
                    fieldNumbers = fieldNumbers.concat("2");
                }
                if (userChoice.contains("3")) {
                    fieldNumbers = fieldNumbers.concat("3");
                }
                if (userChoice.contains("4")) {
                    fieldNumbers = fieldNumbers.concat("4");
                }
                if (userChoice.contains("5")) {
                    fieldNumbers = fieldNumbers.concat("5");
                }
                if (userChoice.contains("6")) {
                    fieldNumbers = fieldNumbers.concat("6");
                }
                if (userChoice.contains("7")) {
                    fieldNumbers = fieldNumbers.concat("7");
                }
                if (userChoice.contains("8")) {
                    fieldNumbers = fieldNumbers.concat("8");
                }
                if (userChoice.contains("9")) {
                    fieldNumbers = fieldNumbers.concat("9");
                }
                System.out.println(fieldNumbers);
                exit = true;
                if (fieldNumbers.equals("0")) {
                    System.out.println("Please choose a field to display.");
                    exit = false;
                }
            } else {
                exit = false;
            }
        }
        return fieldNumbers;
    }

    public boolean invalidSearch () {
        return invalidSearchParameter;
    }

    public void setInvalidSearchToDefault () {
        invalidSearchParameter = false;
    }

}
