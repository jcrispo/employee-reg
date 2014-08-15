package com.exist.menu.sort_Menu;

public abstract class Sort_Options extends Sort_Menu {
    private static final int BLANK = 0;
    private static final String ID = "1";
    private static final String FIRSTNAME = "2";
    private static final String MIDDLENAME = "3";
    private static final String LASTNAME = "4";
    private static final String BIRTHDATE = "5";
    private static final String HIREDATE = "6";
    private static final String POSITION = "7";
    private static final String DEPARTMENT = "8";
    private static final String SALARY = "9";

    public static Sort_Menu sortBy (String userInput) {
        if (userInput.length() == BLANK) {
            System.out.println("No data was entered!");
            return new Sort_Blank();
        } else if (userInput.equals(ID)) {
            return new Sort_Id();
        } else if (userInput.equals(FIRSTNAME)) {
            return new Sort_FirstName();
        } else if (userInput.equals(MIDDLENAME)) {
            return new Sort_MiddleName();
        } else if (userInput.equals(LASTNAME)) {
            return new Sort_LastName();
        } else if (userInput.equals(BIRTHDATE)) {
            return new Sort_BirthDate();
        } else if (userInput.equals(HIREDATE)) {
            return new Sort_HireDate();
        } else if (userInput.equals(POSITION)) {
            return new Sort_Position();
        } else if (userInput.equals(DEPARTMENT)) {
            return new Sort_Department();
        } else if (userInput.equals(SALARY)) {
            return new Sort_Salary();
        } else {
            System.out.println("Invalid input! Choose only from 1~9.");
            return new Sort_Invalid();
        }

    }

    public abstract String parameter ();
    public abstract boolean exitViewMenu ();

}
