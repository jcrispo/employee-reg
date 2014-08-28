package com.exist.mainFunctions;

import java.util.Scanner;
import com.exist.menu.editMenu.EditMenu1;
import com.exist.menu.editMenu.EditMenu1Options;

public class EditEmployeeData {

    public void editData () {
        Scanner userInput = new Scanner(System.in);

        boolean exitEditMenu = false;
        while (!exitEditMenu) {
            System.out.print("\nInput Employee ID to Edit the Employee Information or 'm' to go back to the Menu: ");

            String userChoice = userInput.nextLine().trim();

            EditMenu1 menu1 = EditMenu1Options.choice(userChoice);
            menu1.execute(userChoice);
            exitEditMenu = menu1.exitEditMenu1();          
        }

    }

}
