package com.exist.menu.editMenu;

import com.exist.menu.editMenu.EditMenu1;
import com.exist.menu.editMenu.EditMenu1BackToMenu;
import com.exist.menu.editMenu.EditMenu1Blank;
import com.exist.menu.editMenu.EditMenu1InvalidChoice;
import com.exist.menu.editMenu.EditMenu1Edit;
import com.exist.mainFunctions.InputValidation;

public abstract class EditMenu1Options extends EditMenu1 {

    public static EditMenu1 choice (String userInput) {
        InputValidation inputValidity = new InputValidation();
        if (userInput.length() == 0) {
            return new EditMenu1Blank();
        } else if (userInput.equals("m")) {
            return new EditMenu1BackToMenu();
        } else if (inputValidity.containsOnlyNumbers(userInput)) {
            return new EditMenu1Edit();
        } else {
            return new EditMenu1InvalidChoice();
        }
    }

    public abstract void execute (String userInput);
    public abstract boolean exitEditMenu1 ();

}
