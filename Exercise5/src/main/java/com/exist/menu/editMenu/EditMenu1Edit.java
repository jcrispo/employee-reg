package com.exist.menu.editMenu;

import java.util.Scanner;
import com.exist.menu.editMenu.subMenu.EditMenu;
import com.exist.menu.editMenu.subMenu.EditOptions;
import com.exist.menu.editMenu.subMenu.EditFirstName;
import com.exist.menu.editMenu.subMenu.EditMiddleName;
import com.exist.menu.editMenu.subMenu.EditLastName;
import com.exist.menu.editMenu.subMenu.EditBirthDate;
import com.exist.menu.editMenu.subMenu.EditGender;
import com.exist.menu.editMenu.subMenu.EditHireDate;
import com.exist.menu.editMenu.subMenu.EditPosition;
import com.exist.menu.editMenu.subMenu.EditBlank;
import com.exist.menu.editMenu.subMenu.EditInvalid;
import com.exist.menu.editMenu.subMenu.EditExit;
import com.exist.mainFunctions.ViewEmployeeData;

public class EditMenu1Edit extends EditMenu1 {
    private static ViewEmployeeData view;
    private static Scanner userInput;
    private static EditMenu menu;
    private static String condition;
    private static String sqlQuery;
    private static String userChoice;
    private static boolean exitEditMenu;

    public EditMenu1Edit () {
        view = new ViewEmployeeData();
        userInput = new Scanner(System.in);
        exitEditMenu = false;
    }

    public void execute (String employeeNumberInput) {
        sqlQuery = view.getAllDataQueryStatement();
        condition = " WHERE personalInfo.employeeId = " + employeeNumberInput + ";";
        while (!exitEditMenu) {
            view.showDataNoLimit(sqlQuery + condition);
            if (view.invalidSearch()) {
                System.out.println("User ID does not exist!");
                view.setInvalidSearchToDefault();
                exitEditMenu = true;
            } else {
                System.out.print("\nChoose Field to Edit:\n\t(1) First Name\t(2) Middle Name\t(3) Last Name\n\t(4) Gender\t(5) Birth Date" 
                  + "\t(6) Hire Date\n\t(7) Position\t(8) Salary\t(9) Cancel\nEnter option: ");
                userChoice = (userInput.nextLine()).trim();
                menu = EditOptions.choice(userChoice);
                menu.execute(userChoice, employeeNumberInput);
                exitEditMenu = menu.exitEditMenu();
            }
        }
    }

    public boolean exitEditMenu1 () {
        return false;
    }

}
