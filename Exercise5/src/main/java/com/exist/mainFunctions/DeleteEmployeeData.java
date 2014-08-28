package com.exist.mainFunctions;

import java.util.Scanner;
import com.exist.menu.deleteMenu.DeleteMenu;
import com.exist.menu.deleteMenu.DeleteOptions;

public class DeleteEmployeeData {

    public void deleteEmployeeInfo () {
        Scanner input = new Scanner(System.in);

        boolean exitDelete = false;
        while (!exitDelete) { 
            System.out.print("\nInput Employee ID to Delete the Employee's Information or 'm' to go back to the Menu: ");

            String userInput = input.nextLine().trim();

            DeleteMenu menu = DeleteOptions.choice(userInput);
            menu.execute(userInput);
            exitDelete = menu.exitDeleteMenu();
        }

    }

}
