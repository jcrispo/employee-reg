package com.exist.menu.delete_Menu;

import java.util.Scanner;
import com.exist.main_Functions.InputValidation;
import com.exist.main_Functions.Database;
import com.exist.main_Functions.ViewEmployeeData;
import com.exist.database.DB_Delete;

public class Delete_Delete extends Delete_Menu {
    private static InputValidation validation;
    private static ViewEmployeeData view;
    private DB_Delete delDB;
    private static Scanner input;
    private static String userInput;
    private static boolean exitDeleteConfirmation;
    private static final String deleteStatement = "DELETE FROM personalInfo";
    private static final String condition = " WHERE personalInfo.employeeId = ";

    public Delete_Delete () {
        validation = new InputValidation();
        delDB = new DB_Delete();
        view = new ViewEmployeeData();
        input = new Scanner(System.in);
        exitDeleteConfirmation = false;
    }

    public void execute (String employeeNumber) {
        view.showDataNoLimit(view.ALLDATAQUERY + condition + employeeNumber + ";");
        if (view.invalidSearch()) {
            System.out.println("User ID does not exist!");
            view.setInvalidSearchToDefault();
        } else {
            while (!exitDeleteConfirmation) {
                System.out.print("\nConfirm deletion of data? 'y'/'n': ");
                userInput = input.nextLine().trim();
                if (userInput.equals("y")) {
                    delDB.deleteFromDb(deleteStatement + condition + employeeNumber + ";");
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
