package com.exist.utility.editMenu.subMenu;

import com.exist.mainFunctions.InputValidation;

public abstract class EditOptions extends EditMenu {
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

    public static EditMenu choice(String userInput) {
        InputValidation inputValidity = new InputValidation();

        int userNumberInput = BLANK;

        if (inputValidity.containsOnlyNumbers(userInput)) {
            userNumberInput = Integer.parseInt(userInput);
        }
        if (userNumberInput == FIRSTNAME) {

            return new EditFirstName();
        } else if (userNumberInput == MIDDLENAME) {

            return new EditMiddleName();
        } else if (userNumberInput == LASTNAME) {

            return new EditLastName();
        } else if (userNumberInput == BIRTHDATE) {

            return new EditBirthDate();
        } else if (userNumberInput == GENDER) {

            return new EditGender();
        } else if (userNumberInput == HIREDATE) {

            return new EditHireDate();
        } else if (userNumberInput == POSITION) {

            return new EditPosition();
        } else if (userNumberInput == SALARY) {

            return new EditSalary();
        } else if (userNumberInput == EXIT) {

            return new EditExit();
        } else {
            if (userNumberInput < BLANK || userNumberInput > 8) {
                System.out.println("Invalid input!");
            }

            return new EditInvalid();
        }

    }

    public abstract void execute(String employeeNumberInput);
    public abstract boolean exitEditMenu();

}
