package com.exist.mainFunctions;

import java.util.Scanner;
import java.util.List;

import com.exist.database.HDBRetrieveManager;
import com.exist.utility.sortMenu.SortMenu;
import com.exist.utility.sortMenu.SortOptions;
import com.exist.database.DBRetrieve;

public class ViewEmployeeData {
    private DBRetrieve retrieve;
    private PrintDisplay print;
    private Scanner input;
    private static SortMenu sort;
    private static boolean invalidSearchParameter;
    private static final int EMPTY = 1;

    public ViewEmployeeData () {
        retrieve = new DBRetrieve();
        input = new Scanner(System.in);
        print = new PrintDisplay();
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

    public void showQueryNoLimit(String query) {
        HDBRetrieveManager hdbRetrieveManager = new HDBRetrieveManager();
        Display display = new Display();

        List<Object[]> data = hdbRetrieveManager.retrieveObjectArray(query, 0, 0);

        if (data.size() == EMPTY) {
            System.out.println("\nNo Result Found!");

            invalidSearchParameter = true;
        } else {
            display.displayObjectArray(data);
        }

    }

    public void showData(String query) {
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
                positionSort = " ORDER BY P.position ASC";
            } else if (userChoice.equals("2")) {
                positionSort = " ORDER BY D.department ASC";
            } else {
                System.out.println("Invalid input!");

                exit = false;
           }
        }

        view.showQueryNoLimit(HDBRetrieveManager.getQuery("S_ALL_Positions_JOIN_Departments") + positionSort);
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
                departmentSort = " ORDER BY D.department ASC";
            } else if (userChoice.equals("2")) {
                departmentSort = " ORDER BY D.department DESC";
            } else {
                System.out.println("Invalid input!");

                exit = false;
           }
        }

        view.showQueryNoLimit(HDBRetrieveManager.getQuery("S_ALL_Departments") + departmentSort);
    }

    public boolean invalidSearch () {
        return invalidSearchParameter;
    }

    public void setInvalidSearchToDefault () {
        invalidSearchParameter = false;
    }

}
