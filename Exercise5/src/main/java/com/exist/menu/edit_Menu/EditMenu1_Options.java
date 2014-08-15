package com.exist.menu.edit_Menu;

import com.exist.menu.edit_Menu.EditMenu1;
import com.exist.menu.edit_Menu.EditMenu1_BackToMenu;
import com.exist.menu.edit_Menu.EditMenu1_Blank;
import com.exist.menu.edit_Menu.EditMenu1_InvalidChoice;
import com.exist.menu.edit_Menu.EditMenu1_Edit;
import com.exist.main_Functions.InputValidation;

public abstract class EditMenu1_Options extends EditMenu1 {

    public static EditMenu1 choice (String userInput) {
        InputValidation inputValidity = new InputValidation();
        if (userInput.length() == 0) {
            return new EditMenu1_Blank();
        } else if (userInput.equals("m")) {
            return new EditMenu1_BackToMenu();
        } else if (inputValidity.containsOnlyNumbers(userInput)) {
            return new EditMenu1_Edit();
        } else {
            return new EditMenu1_InvalidChoice();
        }
    }

    public abstract void execute (String userInput);
    public abstract boolean exitEditMenu1 ();

}
