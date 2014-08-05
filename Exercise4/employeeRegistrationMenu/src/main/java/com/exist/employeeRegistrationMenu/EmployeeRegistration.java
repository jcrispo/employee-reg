package com.exist.employeeRegistrationMenu;

import com.exist.employeeRegistrationMenu.EmployeeRegMenu;
import com.exist.employeeRegistrationMenu.MenuFactory;
import com.exist.employeeInfoPackage.EmployeeData;
import com.exist.addViewPackage.ViewEmployeeData;
import java.util.Scanner;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class EmployeeRegistration extends EmployeeData{
    private static EmployeeRegMenu userChoice;
    private static ViewEmployeeData viewData;
    private static Map<Integer, EmployeeData> employeeDatabase;
    private static Map<String, Integer> sortKeyID;
    private static List<String> sortKeyString;
    private static List<Integer> employeeIDStorage;
    private static Scanner input;
    private static DateFormat dateFormat;
    private static String option;
    private static boolean dontExitMenu;
    private static boolean showDatabase;	
    private static final String MENU = "\nRegister (r), View (v), Edit (e), Delete (d), Exit (x): ";
    private static final String EMPTYDATABASE = "\nEmployee Database is Empty!";
    private static final String BLANKINPUT = "b";

    public EmployeeRegistration(){
        viewData = new ViewEmployeeData();
        employeeDatabase = new HashMap<Integer, EmployeeData>();
        sortKeyID = new HashMap<String, Integer>();
        sortKeyString = new ArrayList<String>();
        employeeIDStorage = new ArrayList<Integer>();
        input = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("YYYY/MM/dd - HH:mm");
        dontExitMenu = true;
        showDatabase = true;
    }

    public static void main(String[] args) {
        EmployeeRegistration mainRegistration = new EmployeeRegistration();
        System.out.print("\nEmployee Registration Program\n");
        while (dontExitMenu) {
            mainRegistration.showMenu();
            userChoice = MenuFactory.getUserChoice(option);
            userChoice.execute(sortKeyString, employeeIDStorage, sortKeyID, employeeDatabase);
            showDatabase = userChoice.getShowDatabase();
            dontExitMenu = userChoice.getDontExitMenu();
        }
    }

    public void showMenu () {
        if (!employeeDatabase.isEmpty() && showDatabase) {
            viewData.viewEmpData(employeeIDStorage, employeeDatabase);
        }
        System.out.print(MENU);
        option = (input.nextLine()).trim();
        if (option.length() == 0) {
            option = BLANKINPUT;
        }
    }

}
