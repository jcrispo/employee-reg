package com.exist.menu.delete_Menu;

import com.exist.main_Functions.InputValidation;

public abstract class Delete_Options extends Delete_Menu {
    private static int userNumberInput;

    public static Delete_Menu choice (String userInput) {
        InputValidation validation = new InputValidation();

        if (userInput.equals("m")) {
            return new Delete_Exit();
        } else if (validation.containsOnlyNumbers(userInput) ){
            return new Delete_Delete();
        }  else if (userInput.length() == 0) {
            return new Delete_Blank();
        } else {
            return new Delete_Invalid();
        }
    }

    public abstract void execute (String employeeNumber);
    public abstract boolean exitDeleteMenu ();

}
