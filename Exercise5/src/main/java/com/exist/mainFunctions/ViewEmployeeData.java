package com.exist.mainFunctions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
import com.exist.menu.sortMenu.SortMenu;
import com.exist.menu.sortMenu.SortOptions;
import com.exist.database.DBRetrieve;

public class ViewEmployeeData {
    private DBRetrieve retrieve;
    private PrintDisplay print;
    private Scanner input;
    private InputValidation validation;
    private static SortMenu sort;
    private static boolean invalidSearchParameter;

    public ViewEmployeeData () {
        retrieve = new DBRetrieve();
        input = new Scanner(System.in);
        print = new PrintDisplay();
        validation = new InputValidation();

        invalidSearchParameter = false;
    }

    public void viewEmployeeInformation () {
        ViewEmployeeData view = new ViewEmployeeData();

        boolean exitViewMenu = false;
        while (!exitViewMenu) {
            System.out.println("Sort by:\t(1) ID\t\t(2) First Name\t(3) Middle Name\n\t\t(4) Last Name\t(5) Birth Date\t(6) Hire Date\n\t\t(7) Position\t(8) Department\t(9) Salary");
            System.out.print("Enter number of Sort Parameter: ");

            String userChoice = input.nextLine().trim();

            sort = SortOptions.sortBy(userChoice);
            exitViewMenu = sort.exitViewMenu();
        }

        String sortParameter = sort.parameter();

        view.showData(retrieve.getQuery(sort.queryA()) + sortParameter);

        if (invalidSearchParameter) {
            System.out.println("Database is Empty!");
            invalidSearchParameter = false;
        }
    }

    public void showDataNoLimit (String query) {
        List<List<String>> databaseData = retrieve.getData(query);

        if (databaseData.isEmpty()){
            System.out.println("\nNo Result Found!");

            invalidSearchParameter = true;                
        } else {
            print.printDatabaseData(databaseData);
        }

    }

    public void showData (String query) {
        EmployeeDataValidation validate = new EmployeeDataValidation();

        Integer beginning = 0;
        String range = new String();

        range = validate.number("Show how many rows of data?: ");

        String showMore = "y";
        boolean exit = false;
        while (!exit) {

            if (showMore.equals("y")) {
                List<List<String>> databaseData = retrieve.getData(query + " LIMIT " + beginning.toString() + ", " + range + ") " + retrieve.getQuery(sort.queryB()));

                if (databaseData.isEmpty() && beginning.equals("0")){
                    System.out.println("\nNo Result Found!");

                    invalidSearchParameter = true;           

                    break;
                } else if (databaseData.isEmpty() && !beginning.equals("0")) {
                    System.out.println("No more data");
                    break;
                } else if (databaseData.size() <= Integer.valueOf(range)) {
                    print.printDatabaseData(databaseData);

                    break;
                } else {
                    print.printDatabaseData(databaseData);

                    beginning += Integer.valueOf(range);
                }
            } else if (showMore.equals("n")) {
                exit = true;
                break;
            } else {
                System.out.println("Invalid input. Choose only between 'y' (yes) and 'n' (no)");
            }
            System.out.print("\nShow more? y/n: ");

            showMore = input.nextLine();
        }

    }

    public void viewSortedPosition () {
        ViewEmployeeData view = new ViewEmployeeData();
        String positionSort = new String();

        boolean exit = false;
        while (!exit) {
            System.out.print("Sort by (1) Position Name or (2) Department Name: ");

            exit = true;

            String userChoice = input.nextLine().trim();
            if (userChoice.equals("1")) {
                positionSort = " ORDER BY employeePosition.position_name ASC";
            } else if (userChoice.equals("2")) {
                positionSort = " ORDER BY departments.dept_name ASC";
            } else {
                System.out.println("Invalid input!");

                exit = false;
           }
        }

        view.showDataNoLimit(DBRetrieve.getQuery("SELECT_ALL_employeePosition_JOIN_departments") + positionSort);
    }

    public void viewSortedDepartments () {
        ViewEmployeeData view = new ViewEmployeeData();
        String departmentSort = new String();

        boolean exit = false;
        while (!exit) {
            System.out.print("Sort (1) Ascending or (2) Descending: ");

            exit = true;

            String userChoice = input.nextLine().trim();
            if (userChoice.equals("1")) {
                departmentSort = " ORDER BY departments.dept_name ASC";
            } else if (userChoice.equals("2")) {
                departmentSort = " ORDER BY departments.dept_name DESC";
            } else {
                System.out.println("Invalid input!");

                exit = false;
           }
        }

        view.showDataNoLimit(DBRetrieve.getQuery("SELECT_ALL_departments") + departmentSort);
    }

    public String fieldsToDisplay () {
        String fieldNumbers = "0";

        System.out.println("Choose which fields to print:\n\t\t(1) Id\t\t(2) Name\t(3) Gender\n\t\t(4) Birth Date\t(5) Hire Date\t(6) Position");
        System.out.println("\t\t(7) Department\t(8) Salary\t(9) Email");

        boolean exit = false;
        while (!exit) {
            System.out.print("Enter the numbers with no spaces: ");

            String userChoice = input.nextLine();
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
