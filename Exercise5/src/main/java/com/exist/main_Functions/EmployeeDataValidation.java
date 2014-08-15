package com.exist.main_Functions;

import java.util.Scanner;
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

public class EmployeeDataValidation {
    private static Calendar date = new GregorianCalendar();
    private static SimpleDateFormat dateFormat;
    private static InputValidation validate;
    private static Database companyDatabase;
    private static ViewEmployeeData view;
    private static Scanner userInput;
    private static ResultSet data;
    private static boolean inputIsValid;
    private static String tempString;
    private static final Calendar currentDate = Calendar.getInstance();
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
    private static final int LIMIT = 25;
    private static final Integer LOWEST_VALID_YEAR = 1000;
    private static final String INVALID = "\nInvalid Input!";
    private static final String DAY_INPUT_INVALID = "\nInput Invalid. Value may be exceeding the number of Days in this given Month or is less than 1.\nPlease try Again\n";



    public EmployeeDataValidation () {
        dateFormat = new SimpleDateFormat("YYYY/MM/dd");
        validate = new InputValidation();
        companyDatabase = new Database();
        userInput = new Scanner(System.in);
        view = new ViewEmployeeData();
        inputIsValid = false;
        data = null;
    }

    public String dateString (String print) {
        EmployeeDataValidation mainClass = new EmployeeDataValidation();
        System.out.println("Input " + print);
        mainClass.validYear();            
        mainClass.validMonth();
        mainClass.validDay();        
        inputIsValid = false;
        return dateFormat.format(date.getTime());
    }

    private void validYear () {
        while (!inputIsValid) {
            System.out.print("\tYear (YYYY): ");
            tempString = userInput.nextLine().trim();
            inputIsValid = validate.containsOnlyNumbers(tempString);
            if (!inputIsValid) {
                continue;
            }
            if ((Integer.valueOf(tempString) < LOWEST_VALID_YEAR) || (Integer.valueOf(tempString) > currentDate.get(Calendar.YEAR))) {
                System.out.println("\nInvalid Data. Please follow the format 'YYYY'. Input value shouldn't exceed the current Year: \n");
                inputIsValid = false;
            } else {
                date.set(YEAR, Integer.valueOf(tempString)); 
                inputIsValid = true;
            }
        }
        inputIsValid = false;
    }

    private void validMonth () {
        while (!inputIsValid) {
            System.out.print("\tMonth (1-12): ");
            tempString = userInput.nextLine().trim();
            inputIsValid = validate.containsOnlyNumbers(tempString);
            if (!inputIsValid) {
                continue;
            }
            if ((Integer.valueOf(tempString) < 1) || (Integer.valueOf(tempString) > 12)) {
                System.out.println("\nInvalid Data. Please choose between 1 (Jan.) - 12 (Dec.): \n");
                inputIsValid = false;
            } else {
                date.set(MONTH, Integer.valueOf(tempString)-1); 
                inputIsValid = true;
            }
        }
        inputIsValid = false;
    }

    private void validDay () {
        while (!inputIsValid) {
            System.out.print("\tDay: ");
            tempString = userInput.nextLine().trim();
            inputIsValid = validate.containsOnlyNumbers(tempString);
            if (!inputIsValid) {
                continue;
            }
            switch(date.get(MONTH)){
                case JANUARY:
                case MARCH:
                case MAY:
                case JULY:
                case AUGUST:
                case OCTOBER:
                case DECEMBER:
                    if (Integer.valueOf(tempString) > 31 || (Integer.valueOf(tempString) < 1)) {
                        System.out.println(DAY_INPUT_INVALID);
                        inputIsValid = false;
                    } else {
                        date.set(DATE, Integer.valueOf(tempString));
                        inputIsValid = true;
                    }
                    break;
                case APRIL:
                case JUNE:
                case SEPTEMBER:
                case NOVEMBER:
                    if (Integer.valueOf(tempString) > 30 || (Integer.valueOf(tempString) < 1)) {
                        System.out.println(DAY_INPUT_INVALID);
                        inputIsValid = false;
                    } else {
                        date.set(DATE, Integer.valueOf(tempString));
                        inputIsValid = true;
                    }
                    break;
                case FEBRUARY:
                    if ((date.get(YEAR)%4 == 0) && (Integer.valueOf(tempString) > 29 || (Integer.valueOf(tempString) < 1))) {
                        System.out.println(DAY_INPUT_INVALID);
                        inputIsValid = false;
                    } else if (Integer.valueOf(tempString) > 28 || (Integer.valueOf(tempString) < 1)) {
                        System.out.println(DAY_INPUT_INVALID);
                        inputIsValid = false;
                    }else {
                        date.set(DATE, Integer.valueOf(tempString));
                        inputIsValid = true;
                    }
                    break;
                default:
                    break;
            }
        }
        inputIsValid = false;
    }

    public String name (String print) {
        while (!inputIsValid) {
            System.out.print(print);
            tempString = userInput.nextLine();
            inputIsValid = validate.containsOnlyLetters(tempString);
            if (inputIsValid) {
                inputIsValid = validate.belowCharLimit(tempString, LIMIT);
            }
        }
        inputIsValid = false;
        return tempString.toLowerCase();
    }

    public String gender (String print) {
        while (!inputIsValid) {
            System.out.print(print);
            tempString = userInput.nextLine().trim();
            if (tempString.equals("m")) {
                tempString = "male";
                inputIsValid = true;
            } else if (tempString.equals("f")) {
                tempString = "female";
                inputIsValid = true;
            } else {
                System.out.println(INVALID);
            }
        }
        inputIsValid = false;
        return tempString;
    }

    public String numericDataExists (String print, String query) {
        boolean dataExists = false;
        while (!dataExists) {
            System.out.print(print);
            tempString = userInput.nextLine().trim();
            if (validate.containsOnlyNumbers(tempString)) {    
                data = companyDatabase.getData(query + tempString); 
                try {
                    dataExists = data.isBeforeFirst();
                } catch (SQLException e) {
                    System.out.println(e);
                }
                if (!dataExists){
                    System.out.println("ID number does not exist. Try again");
                }
            }
        }
        return tempString;
    }

    public String newWordData (String print, String query) {
        boolean exit = false;
        boolean dataExists = true;
        while (!exit) {
            System.out.print(print);
            tempString = userInput.nextLine().trim();
            if (validate.containsOnlyLetters(tempString) && validate.belowCharLimit(tempString, LIMIT)) {    
                data = companyDatabase.getData(query + tempString + "\'"); 
                try {
                    dataExists = data.isBeforeFirst();
                } catch (SQLException e) {
                    System.out.println(e);
                }
                if (!dataExists){
                    exit = true;
                } else {
                    System.out.println("Data already exists!");
                }
            }
        }
        return tempString.toLowerCase();
    }

    public String number (String print) {
        while (!inputIsValid) {
            System.out.print(print);
            tempString = userInput.nextLine().trim();
            if (validate.containsOnlyNumbers(tempString)) {
                inputIsValid = true;
            }
        }
        inputIsValid = false;
        return tempString;
    }

    public String email (String print) {
        System.out.print(print);
        tempString = userInput.nextLine().trim();
        return tempString;
    }

    public String departments (String print) {
        while (!inputIsValid) {
            System.out.print(print);
            tempString = userInput.nextLine().trim();
            if (validate.containsOnlyLetters(tempString)) {
                inputIsValid = true;
            }
        }
        inputIsValid = false;
        return tempString;
    }

}
