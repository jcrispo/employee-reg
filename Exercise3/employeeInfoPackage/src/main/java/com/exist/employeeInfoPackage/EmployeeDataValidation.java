package com.exist.employeeInfoPackage;

import java.util.Scanner;
import java.util.Calendar;

public class EmployeeDataValidation{
    private static EmployeeDataValidation validate = new EmployeeDataValidation();
    private static Scanner dataIn;
    private static boolean dataIsValid;
    private static String tempString;
    private static Integer tempNumber;
    private static int month;
    private static final Calendar currentDate = Calendar.getInstance();
    private static final String DAY_INPUT_INVALID = "\nInput Invalid. Value may be exceeding the number of Days in this given Month or is less than 1.\nPlease try Again\n";
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    private static final int LOWEST_VALID_YEAR = 1000;
    private static final int MAX_NAME_CHAR_LIMIT = 25;

    public EmployeeDataValidation () {
        dataIn = new Scanner(System.in);
        dataIsValid = false;
    }

    public boolean onlyLetters (String name) {
        char[] nameChar = (name.trim()).toCharArray();
        if (name.trim().length() == 0) {
            System.out.println("\nNo data was given.\n");
            return false;
        }
        for (char c : nameChar) {
            if (!Character.isLetter(c) && c != ' ') {
                System.out.println("\nInvalid input. Data may only contain Letters\n"); 
                return false;
            }
        }
        return true;
    }

    public boolean nameBelowCharLimit (String name) {
        if (name.length() > MAX_NAME_CHAR_LIMIT) {
            System.out.println("\nName is too long. Please input a shorter version of your name.\n");
            return false;
        }
        return true;
    }

    public boolean onlyNumbers (String number) {
        tempString = number.trim();
        if (tempString.length() == 0){
            System.out.println("\nNo data was given!");
            return false;
        }
        try {
            tempNumber = Integer.valueOf(tempString);
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input. Data may only contain numbers, must not be a blank field, and must have no spaces between the numbers");
            return false;
        }
        return true;
    }

    public String name (String print) {
        while (!dataIsValid) {
            System.out.print(print);
            tempString = dataIn.nextLine().trim();
            dataIsValid = validate.onlyLetters(tempString);
            if (dataIsValid) {
                dataIsValid = validate.nameBelowCharLimit(tempString);
            }
        }
        dataIsValid = false;
        return tempString.toLowerCase();
    }

    public String gender (String print) {
        while (!dataIsValid) {
            System.out.print(print);
            tempString = dataIn.nextLine().trim();
            if (tempString.equals("m")) {
                tempString = "Male";
                dataIsValid = true;
            } else if (tempString.equals("f")) {
                tempString = "Female"; 
                dataIsValid = true;
            } else {
                System.out.print("Please choose only from the given options. ");
            }
        }
        dataIsValid = false;
        return tempString.toLowerCase();     
    }

    public String position (String print) {
        while (!dataIsValid) {
            System.out.print(print);
            tempString = dataIn.nextLine().trim();
            dataIsValid = validate.onlyLetters(tempString);
        }
        dataIsValid = false;
        return tempString.toLowerCase(); 
    }

    public Integer birthDate (String print, String dateType) {
        Integer dateValue;
        String dateString = "0";
        while (!dataIsValid) {
            System.out.print(print);
            dateString = dataIn.nextLine().trim();
            dataIsValid = validate.onlyNumbers(dateString);
            if(!dataIsValid) {
                continue;
            }
            if (dateType.equals("year")) {
                if ((Integer.valueOf(dateString) < LOWEST_VALID_YEAR) || (Integer.valueOf(dateString) > currentDate.get(Calendar.YEAR))) {
                    System.out.println("\nInvalid Data. Please follow the format 'YYYY'. Input value shouldn't exceed the current Year: \n");
                    dataIsValid = false;
                }
            } else if (dateType.equals("month")) {
                if ((Integer.valueOf(dateString) < 1) || (Integer.valueOf(dateString) > 12)) {
                    System.out.println("\nThe Months of the Year range from '1' (January) to '12' (December): \n");
                    dataIsValid = false;
                } else {
                month = Integer.valueOf(dateString);
                }
            } else if (dateType.equals("day")) {
                switch(month){
                    case JANUARY:
                    case MARCH:
                    case MAY:
                    case JULY:
                    case AUGUST:
                    case OCTOBER:
                    case DECEMBER:
                        if (Integer.valueOf(dateString) > 31 || (Integer.valueOf(dateString) < 1)) {
                            System.out.println(DAY_INPUT_INVALID);
                            dataIsValid = false;
                        } else {
                            dataIsValid = true;
                        }
                        break;
                    case APRIL:
                    case JUNE:
                    case SEPTEMBER:
                    case NOVEMBER:
                        if (Integer.valueOf(dateString) > 30 || (Integer.valueOf(dateString) < 1)) {
                            System.out.println(DAY_INPUT_INVALID);
                            dataIsValid = false;
                        } else {
                            dataIsValid = true;
                        }
                        break;
                    case FEBRUARY:
                        if (Integer.valueOf(dateString) > 29 || (Integer.valueOf(dateString) < 1)) {
                            System.out.println(DAY_INPUT_INVALID);
                            dataIsValid = false;
                        } else {
                            dataIsValid = true;
                        }
                        break;
                    default:
                        break;
                }
            } else if (dateType.equals("hour")) {
                if ((Integer.valueOf(dateString) < 0) || (Integer.valueOf(dateString) > 23)) {
                    System.out.println("\nThe Hour must range from '0' to '23': \n");
                    dataIsValid = false;
                }
            } else if (dateType.equals("minutes")) {
                if ((Integer.valueOf(dateString) < 0) || (Integer.valueOf(dateString) > 59)) {
                    System.out.println("\nThe Minutes must range from '0' to '59': \n");
                    dataIsValid = false;
                }
            } else {
                System.out.println("\nSupported Date types only include Year, Month, Day, Hour, and Minute");
            }
        }
        dataIsValid = false;
        return Integer.valueOf(dateString);
    }

}
