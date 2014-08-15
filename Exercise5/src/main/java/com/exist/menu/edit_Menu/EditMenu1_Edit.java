package com.exist.menu.edit_Menu;

import java.util.Scanner;
import com.exist.menu.edit_Menu.sub_Menu.EditMenu;
import com.exist.menu.edit_Menu.sub_Menu.Edit_Options;
import com.exist.menu.edit_Menu.sub_Menu.Edit_FirstName;
import com.exist.menu.edit_Menu.sub_Menu.Edit_MiddleName;
import com.exist.menu.edit_Menu.sub_Menu.Edit_LastName;
import com.exist.menu.edit_Menu.sub_Menu.Edit_BirthDate;
import com.exist.menu.edit_Menu.sub_Menu.Edit_Gender;
import com.exist.menu.edit_Menu.sub_Menu.Edit_HireDate;
import com.exist.menu.edit_Menu.sub_Menu.Edit_Position;
import com.exist.menu.edit_Menu.sub_Menu.Edit_Blank;
import com.exist.menu.edit_Menu.sub_Menu.Edit_Invalid;
import com.exist.menu.edit_Menu.sub_Menu.Edit_Exit;
import com.exist.main_Functions.Database;
import com.exist.main_Functions.ViewEmployeeData;

public class EditMenu1_Edit extends EditMenu1 {
    private static ViewEmployeeData view;
    private static Database companyDatabase;
    private static Scanner userInput;
    private static EditMenu menu;
    private static String condition;
    private static String sqlQuery;
    private static String userChoice;
    private static boolean exitEditMenu;

    public EditMenu1_Edit () {
        view = new ViewEmployeeData();
        userInput = new Scanner(System.in);
        companyDatabase = new Database();
        exitEditMenu = false;
    }

    public void execute (String employeeNumberInput) {
        sqlQuery = view.getAllDataQueryStatement();
        condition = " WHERE personalInfo.employeeId = " + employeeNumberInput + ";";
        while (!exitEditMenu) {
            companyDatabase.loginDatabase();
            view.showData(sqlQuery + condition);
            companyDatabase.closeDatabase(); 
            if (view.invalidSearch()) {
                System.out.println("User ID does not exist!");
                view.setInvalidSearchToDefault();
                exitEditMenu = true;
            } else {
                System.out.print("\nChoose Field to Edit: (1) First Name, (2) Middle Name, (3) Last Name, (4) Gender, (5) Birth Date, (6) Hire Date, (7) Position, (8) Cancel : ");
                userChoice = (userInput.nextLine()).trim();
                menu = Edit_Options.choice(userChoice);
                menu.execute(userChoice, employeeNumberInput);
                exitEditMenu = menu.exitEditMenu();
            }
        }
    }

    public boolean exitEditMenu1 () {
        return false;
    }

}
