package com.exist.menu.deleteMenu;

import java.util.Scanner;
import com.exist.mainFunctions.InputValidation;
import com.exist.mainFunctions.ViewEmployeeData;
import com.exist.database.DBDelete;

public class DeleteDelete extends DeleteMenu {
    private InputValidation validation;
    private ViewEmployeeData view;
    private DBDelete delDB;
    private Scanner input;
    private static String userInput;
    private static boolean exitDeleteConfirmation;
    private static final String deleteStatement = "DELETE FROM personalInfo";
    private static final String condition = " WHERE personalInfo.employeeId = ";

    public DeleteDelete () {
        validation = new InputValidation();
        delDB = new DBDelete();
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
                } else if (userInput.equals("n")) {
                    exitDeleteConfirmation = true;
                } else {
                    System.out.println("Please choose between \"y\" (yes) and \"n\" (no)");
                }
            }
        }
    }

    public boolean exitDeleteMenu () {
        return true;
    }
}
