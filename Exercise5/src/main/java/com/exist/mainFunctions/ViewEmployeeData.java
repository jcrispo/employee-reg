package com.exist.mainFunctions;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.Scanner;
import com.exist.menu.sortMenu.SortMenu;
import com.exist.menu.sortMenu.SortOptions;
import com.exist.database.DBRetrieve;
import com.exist.database.DBDataSource;
import java.util.List;
import com.exist.database.Retrieve;

public class ViewEmployeeData {
    private DBRetrieve retrieve;
    private Retrieve retrieve2;
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
    public static final String ALLDATAQUERY = "SELECT personalInfo.employeeId, personalInfo.firstName, personalInfo.middleName, personalInfo.lastName, personalInfo.birthDate,"
      + " personalInfo.gender, company.hireDate, company.position_name, company.dept_name, company.basicSalary, company.emailId FROM personalInfo"
      + " LEFT JOIN (SELECT companyEmployeeData.employeeId, companyEmployeeData.hireDate, posdept.position_refId, posdept.position_name,  posdept.dept_name,"
      + " companyEmployeeData.basicSalary, companyEmployeeData.emailId FROM companyEmployeeData"
      + " LEFT JOIN (SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition"
      + " LEFT JOIN departments ON employeePosition.deptId=departments.deptId) AS posdept"
      + " ON companyEmployeeData.position_refId=posdept.position_refId) AS company"
      + " ON personalInfo.employeeId=company.employeeId";

    public ViewEmployeeData () {
        retrieve = new DBRetrieve();
        retrieve2 = new DBRetrieve();
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

        databaseIsEmpty = retrieve.resultIsEmpty(ALLDATAQUERY);
        view.showData(ALLDATAQUERY + sort.parameter());
        if (databaseIsEmpty) {
            System.out.println("Database is Empty!");
        }
    }

    public void showDataNoLimit (String query) {
        EmployeeData employee = new EmployeeData();

 //       Connection connection = null;
        // ResultSet resultSet = null;

        try {
            print.printHeader();
          //   boolean emptyResult = retrieve.resultIsEmpty(query);
//            if (emptyResult){
//                System.out.println("\nNo Result Found!");
//                invalidSearchParameter = true;
//            }
           //  dbConnection = DBDataSource.getInstance().getConnection();
            // resultSet = retrieve.getData(dbConnection, query);

            List<List<String>> data = retrieve2.getData(query);

            if (data.isEmpty()){
                System.out.println("\nNo Result Found!");
                invalidSearchParameter = true;                
            } else {
                for (List<String> row : data) {
                    for (String column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
/*
		    while (resultSet.next()) {
		        employee.setEmployeeId(Integer.valueOf(resultSet.getString("personalInfo.employeeId")));
		        employee.setFirstName(resultSet.getString("personalInfo.firstName"));
		        employee.setMiddleName(resultSet.getString("personalInfo.middleName"));
		        employee.setLastName(resultSet.getString("personalInfo.lastName"));
		        employee.setGender(resultSet.getString("personalInfo.gender"));
		        employee.setBirthDate(resultSet.getString("personalInfo.birthDate"));
		        employee.setHireDate(resultSet.getString("company.hireDate"));
		        employee.setPositionName(resultSet.getString("company.position_name"));
		        employee.setDepartmentName(resultSet.getString("company.dept_name"));
		        employee.setSalary(resultSet.getInt("company.basicSalary"));
		        employee.setEmail(resultSet.getString("company.emailId"));
		        print.printEmployeeData(employee);
		    }
*/
            }

            // dbConnection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
        } finally {
            try {
/*
            if (resultSet != null) {
                resultSet.close();
            }
*/

            if (dbConnection != null) {
                dbConnection.close();    
            }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void showData (String query) {
        EmployeeData employee = new EmployeeData();
        ViewEmployeeData view = new ViewEmployeeData();
        EmployeeDataValidation validate = new EmployeeDataValidation();
        int employeeDataPrinted = 0;
        int displayLimit;
        int employeeDataPrintLimit;
        boolean exitLoop = false;
        String showMore = "";
        String fields = view.fieldsToDisplay();
        System.out.println("Displaying fields: " + fields);
        displayLimit = Integer.valueOf(validate.number("How many results to display?: "));
        employeeDataPrintLimit = employeeDataPrinted + displayLimit; 
        print.printHeader();
        boolean emptyResult = retrieve.resultIsEmpty(query);
        if (emptyResult){
            System.out.println("\nNo Result Found!");
            invalidSearchParameter = true;
        }
        try {
            dbConnection = DBDataSource.getInstance().getConnection();
            data = retrieve.getData(dbConnection, query);
            while (data.next() && employeeDataPrinted <= employeeDataPrintLimit) {
                if (employeeDataPrinted == employeeDataPrintLimit) {
                    exitLoop = false;
                    while (!exitLoop) {
                        exitLoop = true;
                        System.out.print("\nShow more? y/n: ");
                        showMore = input.nextLine();
                        if (showMore.equals("y")) {
                            employeeDataPrintLimit+=displayLimit;
                            print.printHeader();
                        } else if (showMore.equals("n")) {
                            break;
                        } else {
                            System.out.println("Invalid input! 'y' or 'n' only.");
                            exitLoop = false;
                        }
                    }
                }
                if (showMore.equals("n")) {
                    break;
                }
                employee.setEmployeeId(Integer.valueOf(data.getString("personalInfo.employeeId")));
                employee.setFirstName(data.getString("personalInfo.firstName"));
                employee.setMiddleName(data.getString("personalInfo.middleName"));
                employee.setLastName(data.getString("personalInfo.lastName"));
                employee.setGender(data.getString("personalInfo.gender"));
                employee.setBirthDate(data.getString("personalInfo.birthDate"));
                employee.setHireDate(data.getString("company.hireDate"));
                employee.setPositionName(data.getString("company.position_name"));
                employee.setDepartmentName(data.getString("company.dept_name"));
                employee.setSalary(data.getInt("company.basicSalary"));
                employee.setEmail(data.getString("company.emailId"));
                print.printEmployeeData(employee);
                employeeDataPrinted++;
            }
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showPositions (String query) {
        CompanyData positionList = new CompanyData();
        boolean emptyResult = retrieve.resultIsEmpty(query);
        if (emptyResult){
            System.out.println("\nNo Result Found!");
            invalidSearchParameter = true;
        } else {
            print.printPositionHeader();
        }
        try {
            dbConnection = DBDataSource.getInstance().getConnection();
            data = retrieve.getData(dbConnection, query);
            data = retrieve.getData(dbConnection, query);
            while (data.next()) {
                positionList.setPositionId(data.getInt("employeePosition.position_refId"));
                positionList.setPositionName(data.getString("employeePosition.position_name"));
                positionList.setDepartmentName(data.getString("departments.dept_name"));
                print.printPositions(positionList);
            }
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving data from Company Database");
        }
    }

    public void showDepartments (String query) {
        CompanyData departmentList = new CompanyData();
        boolean emptyResult = retrieve.resultIsEmpty(query);
        if (emptyResult){
            System.out.println("\nNo Result Found!");
            invalidSearchParameter = true;
        } else {
            print.printDepartmentHeader();
        }
        try {
            dbConnection = DBDataSource.getInstance().getConnection();
            data = retrieve.getData(dbConnection, query);
            while (data.next()) {
                departmentList.setDeptId(data.getInt("departments.deptId"));
                departmentList.setDepartmentName(data.getString("departments.dept_name"));
                print.printDepartments(departmentList); 
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
