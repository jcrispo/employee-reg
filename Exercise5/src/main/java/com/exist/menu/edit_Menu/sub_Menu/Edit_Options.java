package com.exist.menu.edit_Menu.sub_Menu;

import com.exist.main_Functions.InputValidation;

public abstract class Edit_Options extends EditMenu {
    private static final int FIRSTNAME = 1;
    private static final int MIDDLENAME = 2;
    private static final int LASTNAME = 3;
    private static final int GENDER = 4;
    private static final int BIRTHDATE = 5;
    private static final int HIREDATE = 6;
    private static final int POSITION = 7;
    private static final int SALARY = 8;
    private static final int EXIT = 9;
    private static final int BLANK = 0;

    public static EditMenu choice (String userInput) {
        InputValidation inputValidity = new InputValidation();
        int userNumberInput = BLANK;
        if (inputValidity.containsOnlyNumbers(userInput)) {
            userNumberInput = Integer.parseInt(userInput);
        }
        if (userNumberInput == FIRSTNAME) {
            return new Edit_FirstName();
        } else if (userNumberInput == MIDDLENAME) {
            return new Edit_MiddleName();
        } else if (userNumberInput == LASTNAME) {
            return new Edit_LastName();
        } else if (userNumberInput == BIRTHDATE) {
            return new Edit_BirthDate();
        } else if (userNumberInput == GENDER) {
            return new Edit_Gender();
        } else if (userNumberInput == HIREDATE) {
            return new Edit_HireDate();
        } else if (userNumberInput == POSITION) {
            return new Edit_Position();
        } else if (userNumberInput == SALARY) {
            return new Edit_Salary();
        } else if (userNumberInput == EXIT) {
            return new Edit_Exit();
        } else {
            if (userNumberInput < BLANK || userNumberInput > 8) {
                System.out.println("Invalid input!");
            }
            return new Edit_Invalid();
        }
    }

    public abstract void execute (String userInput, String employeeNumberInput);
    public abstract boolean exitEditMenu ();

}
