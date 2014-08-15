package com.exist.menu.main_Menu;

public abstract class MenuOptions extends EmployeeRegistrationMenu {
    private static final String REGISTER = "r";
    private static final String VIEW = "v";
    private static final String EDIT = "e";
    private static final String DELETE = "d";
    private static final String BLANK = "b";
    private static final String EXIT = "x";

    public static EmployeeRegistrationMenu userChoice (String userInput) {

        if (userInput.equals(REGISTER)) {
            return new Register();
        } else if (userInput.equals(VIEW)) {
            return new View();
        } else if (userInput.equals(EDIT)) {
            return new Edit();
        } else if (userInput.equals(DELETE)) {
            return new Delete();
        } else if (userInput.equals(BLANK)) {
            return new Blank();
        } else if (userInput.equals(EXIT)) {
            return new Exit();
        } else {
            return new InvalidChoice();
        }

    }

    public abstract void execute ();
    public abstract boolean showDatabase();
    public abstract boolean exitMenu ();

}
