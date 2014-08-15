package com.exist.main_Functions;

import java.util.Scanner;
import com.exist.menu.edit_Menu.EditMenu1;
import com.exist.menu.edit_Menu.EditMenu1_Options;

public class EditEmployeeData {
    private static Database companyDatabase;
    private static EditMenu1 menu1;
    private static String userChoice;
    private static Scanner userInput;
    private static boolean exitEditMenu;

    public EditEmployeeData () {
        companyDatabase = new Database();
        userInput = new Scanner(System.in);
        exitEditMenu = false;
    }

    public void editData () {
        while (!exitEditMenu) {
            System.out.print("\nInput Employee ID to Edit the Employee Information or 'm' to go back to the Menu: ");
            userChoice = userInput.nextLine().trim();
            menu1 = EditMenu1_Options.choice(userChoice);
            menu1.execute(userChoice);
            exitEditMenu = menu1.exitEditMenu1();          
        }
    }

}
