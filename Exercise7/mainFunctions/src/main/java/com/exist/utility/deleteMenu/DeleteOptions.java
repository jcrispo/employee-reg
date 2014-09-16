package com.exist.utility.deleteMenu;

import com.exist.mainFunctions.InputValidation;

public abstract class DeleteOptions extends DeleteMenu {

    public static DeleteMenu choice(String userInput) {
        InputValidation validation = new InputValidation();

        if (userInput.equals("m")) {

            return new DeleteExit();
        } else if (validation.containsOnlyNumbers(userInput) ){

            return new DeleteDelete();
        }  else if (userInput.length() == 0) {

            return new DeleteBlank();
        } else {

            return new DeleteInvalid();
        }
    }

    public abstract void execute(String employeeNumber);
    public abstract boolean exitDeleteMenu ();

}
