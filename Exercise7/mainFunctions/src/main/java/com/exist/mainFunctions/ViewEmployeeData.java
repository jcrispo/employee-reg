package com.exist.mainFunctions;

import java.util.Scanner;
import java.util.List;
import com.exist.database.HDBRetrieveManager;
import com.exist.utility.sortMenu.SortMenu;
import com.exist.utility.sortMenu.SortOptions;

import static com.exist.database.HDBRetrieveManager.getQuery;

public class ViewEmployeeData {
    private Scanner input;
    private static SortMenu sort;
    private static boolean invalidSearchParameter;
    private static final int EMPTY = 1;

    public ViewEmployeeData () {
        input = new Scanner(System.in);
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

        view.showQuery(getQuery("SELECT_ALL_JOIN_ALL") + sortParameter);

        if (invalidSearchParameter) {
            System.out.println("Database is Empty!");

            invalidSearchParameter = false;
        }
    }

    public void showQueryNoLimit(String hqlQuery) {
        HDBRetrieveManager hdbRetrieveManager = new HDBRetrieveManager();
        Display display = new Display();

        List<Object[]> data = hdbRetrieveManager.retrieveObjectArray(hqlQuery, 0, 0);

        if (data.size() == EMPTY) {
            System.out.println("\nNo Result Found!");

            invalidSearchParameter = true;
        } else {
            display.displayObjectArray(data);
        }

    }

    public void showQuery(String hqlQuery) {
        HDBRetrieveManager hdbRetrieveManager = new HDBRetrieveManager();
        EmployeeDataValidation validate = new EmployeeDataValidation();
        Display display = new Display();

        Integer beginning = 0;
        Integer range = Integer.valueOf(validate.number("Show how many rows of data?: "));

        String showMore = "y";
        boolean exit = false;
        while (!exit) {

            if (showMore.equals("y")) {
                List<Object[]> data = hdbRetrieveManager.retrieveObjectArray(hqlQuery, beginning, range);

                if ((data.size() == EMPTY) && beginning.equals(0)){
                    System.out.println("\nNo Result Found!");

                    invalidSearchParameter = true;

                    break;
                } else if ((data.size() == EMPTY) && !beginning.equals(0)){
                    System.out.println("No more data");

                    break;
                } else if (data.size() <= Integer.valueOf(range)) {
                    display.displayObjectArray(data);

                    break;
                } else {
                    display.displayObjectArray(data);

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

        view.showQueryNoLimit(getQuery("S_ALL_Positions_JOIN_Departments") + positionSort);
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

        view.showQueryNoLimit(getQuery("S_ALL_Departments") + departmentSort);
    }

    public boolean invalidSearch () {
        return invalidSearchParameter;
    }

    public void setInvalidSearchToDefault () {
        invalidSearchParameter = false;
    }

}
