package com.exist.menu.deleteMenu;

import java.util.Scanner;
import com.exist.mainFunctions.InputValidation;
import com.exist.mainFunctions.ViewEmployeeData;
import com.exist.database.DBDelete;
import com.exist.database.DBRetrieve;

public class DeleteDelete extends DeleteMenu {

    public void execute(String employeeNumber) {
        ViewEmployeeData view = new ViewEmployeeData();

        String deleteStatement = "DELETE FROM personalInfo";
        String condition = " WHERE personalInfo.employeeId = ";

        view.showDataNoLimit(DBRetrieve.getQuery("SELECT_ALL") + condition + employeeNumber + ";");

        if (view.invalidSearch()) {
            System.out.println("User ID does not exist!");

            view.setInvalidSearchToDefault();
        } else {
            DBDelete delDB = new DBDelete();
            Scanner input = new Scanner(System.in);
            String userInput = new String();

            boolean exitDeleteConfirmation = false;
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

    public boolean exitDeleteMenu() {
        return true;
    }

}
