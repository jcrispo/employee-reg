package com.exist.menu.delete_Menu;

import java.util.Scanner;
import com.exist.main_Functions.InputValidation;
import com.exist.main_Functions.Database;
import com.exist.main_Functions.ViewEmployeeData;

public class Delete_Delete extends Delete_Menu {
    private static InputValidation validation;
    private static Database companyDatabase;
    private static ViewEmployeeData view;
    private static Scanner input;
    private static String userInput;
    private static boolean exitDeleteConfirmation;
    private static final String deleteStatement = "DELETE FROM personalInfo";
    private static final String condition = " WHERE personalInfo.employeeId = ";

    public Delete_Delete () {
        validation = new InputValidation();
        companyDatabase = new Database();
        view = new ViewEmployeeData();
        input = new Scanner(System.in);
        exitDeleteConfirmation = false;
    }

    public void execute (String employeeNumber) {
        view.showData(view.getAllDataQueryStatement() + condition + employeeNumber + ";");
        if (view.invalidSearch()) {
            System.out.println("User ID does not exist!");
            view.setInvalidSearchToDefault();
        } else {
            while (!exitDeleteConfirmation) {
                System.out.print("Confirm deletion of data? 'y'/'n': ");
                userInput = input.nextLine().trim();
                if (userInput.equals("y")) {
                    companyDatabase.loginDatabase();
                    companyDatabase.insertStatement(deleteStatement + condition + employeeNumber + ";");
                    companyDatabase.updateDatabase();
                    companyDatabase.closeDatabase();
                    exitDeleteConfirmation = true;
                    System.out.println("Delete successful!");
                } else if (userInput.equals("n")) {
                    exitDeleteConfirmation = true;
                } else {
                    System.out.println("Please choose between \"y\" (yes) and \"no\" (no)");
                }
            }
        }
    }

    public boolean exitDeleteMenu () {
        return true;
    }
}