package com.exist.utility.deleteMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.exist.database.HDBRetrieveManager;
import com.exist.database.HDBUpdateDeleteManager;
import com.exist.mainFunctions.ViewEmployeeData;
import org.hibernate.dialect.Ingres10Dialect;

public class DeleteDelete extends DeleteMenu {

    public void execute(String employeeNumber) {
        ViewEmployeeData view = new ViewEmployeeData();

        String condition = " WHERE E.id = ";

        view.showQueryNoLimit(HDBRetrieveManager.getQuery("SELECT_ALL_JOIN_ALL") + condition + employeeNumber);

        if (view.invalidSearch()) {
            System.out.println("User ID does not exist!");

            view.setInvalidSearchToDefault();
        } else {
            HDBUpdateDeleteManager hdbUpdateDeleteManager = new HDBUpdateDeleteManager();
            Scanner input = new Scanner(System.in);
            String userInput;

            boolean exitDeleteConfirmation = false;
            while (!exitDeleteConfirmation) {
                System.out.print("\nConfirm deletion of data? 'y'/'n': ");

                userInput = input.nextLine().trim();
                if (userInput.equals("y")) {
                    Map<String, Object> namedParameter = new HashMap<String, Object>();

                    namedParameter.put(":id", Integer.valueOf(employeeNumber));
                    hdbUpdateDeleteManager.updateDB(HDBRetrieveManager.getQuery("DEL_Employee_WHERE_ID"), namedParameter);

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
