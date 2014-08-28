package com.exist.menu.editMenu;

import java.util.Scanner;
import com.exist.menu.editMenu.subMenu.EditMenu;
import com.exist.menu.editMenu.subMenu.EditOptions;
import com.exist.mainFunctions.ViewEmployeeData;
import com.exist.database.DBRetrieve;

public class EditMenu1Edit extends EditMenu1 {
    private static Scanner userInput;
    private static EditMenu menu;
    private static String userChoice;

    public EditMenu1Edit () {
        userInput = new Scanner(System.in);
    }

    public void execute (String employeeNumberInput) {
        String condition = " WHERE personalInfo.employeeId = " + employeeNumberInput + ";";
        ViewEmployeeData view = new ViewEmployeeData();

        boolean exitEditMenu = false;
        while (!exitEditMenu) {
            view.showDataNoLimit(DBRetrieve.getQuery("queryAllData") + condition);

            if (view.invalidSearch()) {
                System.out.println("User ID does not exist!");
                view.setInvalidSearchToDefault();
                exitEditMenu = true;
            } else {
                System.out.print("\nChoose Field to Edit:\n\t(1) First Name\t(2) Middle Name\t(3) Last Name\n\t(4) Gender\t(5) Birth Date" 
                  + "\t(6) Hire Date\n\t(7) Position\t(8) Salary\t(9) Cancel\nEnter option: ");
                userChoice = (userInput.nextLine()).trim();
                menu = EditOptions.choice(userChoice);
                menu.execute(employeeNumberInput);
                exitEditMenu = menu.exitEditMenu();
            }
        }
    }

    public boolean exitEditMenu1 () {
        return false;
    }

}
