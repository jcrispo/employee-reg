package com.exist.main_Functions;

import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

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
    private static final int LIMIT = 25;
    private static final String INVALID = "\nInvalid Input!";

    public EmployeeDataValidation () {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        validate = new InputValidation();
        companyDatabase = new Database();
        userInput = new Scanner(System.in);
        view = new ViewEmployeeData();
        date = new GregorianCalendar();
        inputIsValid = false;
        data = null;
    }

    public String dateString (String print) {
        tempString = null;
        dateFormat.setLenient(false);
        while (!inputIsValid) {
        System.out.print(print + " in YYYY-MM-DD format: ");
        tempString = userInput.nextLine().trim();
            try {
                date.setTime(dateFormat.parse(tempString));
                if (validate.dayIsValid(date) && validate.monthIsValid(date) && validate.yearIsValid(date)) {
                inputIsValid = true; 
                } else {
                inputIsValid = false; 
                }
            } catch (ParseException e) {
                System.out.println("Date input is not valid");
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
