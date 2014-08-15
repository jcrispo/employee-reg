package com.exist.main_Functions;

import java.util.Scanner;
import com.exist.menu.delete_Menu.Delete_Menu;
import com.exist.menu.delete_Menu.Delete_Options;
import com.exist.menu.delete_Menu.Delete_Delete;
import com.exist.menu.delete_Menu.Delete_Blank;
import com.exist.menu.delete_Menu.Delete_Invalid;
import com.exist.menu.delete_Menu.Delete_Exit;

public class DeleteEmployeeData {
    private static Scanner input;
    private static Delete_Menu menu;
    private static boolean exitDelete;
    private static String userInput;

    public DeleteEmployeeData () {
        input = new Scanner(System.in);
        exitDelete = false;
    }

    public void deleteEmployeeInfo () {
        while (!exitDelete) { 
            System.out.print("\nInput Employee ID to Delete the Employee's Information or 'm' to go back to the Menu: ");
            userInput = input.nextLine().trim();
            menu = Delete_Options.choice(userInput);
            menu.execute(userInput);
            exitDelete = menu.exitDeleteMenu();
        }
    }

}
