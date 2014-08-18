package com.exist.main_Functions;

import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

public class EmployeeDataValidation {
    private SimpleDateFormat dateFormat;
    private InputValidation validate;
    private Database companyDatabase;
    private ViewEmployeeData view;
    private Scanner userInput;
    private static Calendar date;
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
    private static final String INVALID = "\nInvalid Input!";
    private static final String DAY_INPUT_INVALID = "\nInput Invalid. Value may be exceeding the number of Days in this given Month or is less than 1.\nPlease try Again\n";

    public EmployeeDataValidation () {
        dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        validate = new InputValidation();
        companyDatabase = new Database();
        userInput = new Scanner(System.in);
        view = new ViewEmployeeData();
        date = new GregorianCalendar();
        inputIsValid = false;
        data = null;
    }

    public String dateString (String print) {
        EmployeeDataValidation mainClass = new EmployeeDataValidation();
        while (!inputIsValid) {
            System.out.print("Input " + print + "in 'YYYY-MM-DD' format: ");
            tempString = userInput.nextLine().trim();
            dateFormat.setLenient(false);
            try {
                date.setTime(dateFormat.parse(tempString));
                inputIsValid = true;            
            } catch (ParseException e) {
                System.out.println("The date you provided is not valid.");
            } 
        }       
        inputIsValid = false;
        return dateFormat.format(date.getTime());
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
