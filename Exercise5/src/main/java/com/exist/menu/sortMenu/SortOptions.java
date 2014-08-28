package com.exist.menu.sortMenu;

public abstract class SortOptions extends SortMenu {
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

    public static SortMenu sortBy (String userInput) {
        if (userInput.length() == BLANK) {
            System.out.println("No data was entered!");
            return new SortBlank();
        } else if (userInput.equals(ID)) {
            return new SortId();
        } else if (userInput.equals(FIRSTNAME)) {
            return new SortFirstName();
        } else if (userInput.equals(MIDDLENAME)) {
            return new SortMiddleName();
        } else if (userInput.equals(LASTNAME)) {
            return new SortLastName();
        } else if (userInput.equals(BIRTHDATE)) {
            return new SortBirthDate();
        } else if (userInput.equals(HIREDATE)) {
            return new SortHireDate();
        } else if (userInput.equals(POSITION)) {
            return new SortPosition();
        } else if (userInput.equals(DEPARTMENT)) {
            return new SortDepartment();
        } else if (userInput.equals(SALARY)) {
            return new SortSalary();
        } else {
            System.out.println("Invalid input! Choose only from 1~9.");
            return new SortInvalid();
        }

    }

    public abstract String parameter ();
    public abstract boolean exitViewMenu ();

}
