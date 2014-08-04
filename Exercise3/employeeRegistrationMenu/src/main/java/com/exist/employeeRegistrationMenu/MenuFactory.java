package com.exist.employeeRegistrationMenu;

import com.exist.employeeRegistrationMenu.EmployeeRegMenu;
import com.exist.employeeRegistrationMenu.Register;
import com.exist.employeeRegistrationMenu.View;
import com.exist.employeeRegistrationMenu.Edit;
import com.exist.employeeRegistrationMenu.Delete;
import com.exist.employeeRegistrationMenu.Exit;
import com.exist.employeeRegistrationMenu.Blank;
import com.exist.employeeRegistrationMenu.InvalidMenuChoice;

public abstract class MenuFactory extends EmployeeRegMenu {
    private static final String REGISTER = "r";
    private static final String VIEW = "v";
    private static final String EDIT = "e";
    private static final String DELETE = "d";
    private static final String EXIT = "x";
    private static final String BLANKINPUT = "b";

    public static EmployeeRegMenu getUserChoice (String input) {
        if (input.equals(REGISTER)) {
            return new Register();
        } else if (input.equals(VIEW)) {
            return new View();
        } else if (input.equals(EDIT)) {
            return new Edit();
        } else if (input.equals(DELETE)) {
            return new Delete();
        } else if (input.equals(EXIT)) {
            return new Exit();
        } else if (input.equals(BLANKINPUT)) {
            return new Blank();
        } else {
            return new InvalidMenuChoice();
        }
    }

    public abstract void execute ();
    public abstract boolean getShowDatabase ();
    public abstract boolean getDontExitMenu ();

}
