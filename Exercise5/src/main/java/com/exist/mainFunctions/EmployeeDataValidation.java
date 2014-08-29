package com.exist.mainFunctions;

import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import com.exist.database.DBRetrieve;

public class EmployeeDataValidation {
    private InputValidation validate;
    private ViewEmployeeData view;
    private Scanner userInput;
    private DBRetrieve fromDatabase;
    private static final Calendar currentDate = Calendar.getInstance();
    private static final int LIMIT = 25;
    private static final String INVALID = "\nInvalid Input!";

    public EmployeeDataValidation () {
        validate = new InputValidation();
        userInput = new Scanner(System.in);
        view = new ViewEmployeeData();
        fromDatabase = new DBRetrieve();
    }

    public String dateString (String print) {
        Calendar date = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.print(print + " in YYYY-MM-DD format: ");

            String tempString = userInput.nextLine().trim();

            try {
                date.setTime(dateFormat.parse(tempString));

                if (validate.dayIsValid(date) && validate.monthIsValid(date) && validate.yearIsValid(date)) {
                    inputIsValid = true; 
                }
            } catch (ParseException e) {
                System.out.println("Date input is not valid");
            } 
        }

        return dateFormat.format(date.getTime());
    }

    public String name (String print) {
        String returnValue = new String();

        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.print(print);

            returnValue = userInput.nextLine();

            inputIsValid = validate.containsOnlyLetters(returnValue);
            if (inputIsValid) {
                inputIsValid = validate.belowCharLimit(returnValue, LIMIT);
            }
        }

        return returnValue.toLowerCase();
    }

    public String gender (String print) {
        String returnValue = new String();

        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.print(print);

            returnValue = userInput.nextLine().trim();
            if (returnValue.equals("m")) {
                returnValue = "male";

                inputIsValid = true;
            } else if (returnValue.equals("f")) {
                returnValue = "female";

                inputIsValid = true;
            } else {
                System.out.println(INVALID);
            }
        }

        return returnValue;
    }

    public String numericDataExists (String print, String query) {
        String returnValue = new String();

        boolean resultIsEmpty = true;
        while (resultIsEmpty) {
            System.out.print(print);

            returnValue = userInput.nextLine().trim();

            if (validate.containsOnlyNumbers(returnValue)) {
                resultIsEmpty = fromDatabase.resultIsEmpty(query + returnValue);
                if (resultIsEmpty){
                    System.out.println("ID number does not exist. Try again");
                }
            }
        }

        return returnValue;
    }

    public String newWordData (String print, String query) {
        String returnValue = new String();

        boolean resultIsEmpty = false; 
        while (!resultIsEmpty) {
            System.out.print(print);

            returnValue = userInput.nextLine().trim();
            if (validate.containsOnlyLetters(returnValue) && validate.belowCharLimit(returnValue, LIMIT)) {
                resultIsEmpty = fromDatabase.resultIsEmpty(query + returnValue + "\'");
                if (!resultIsEmpty){
                    System.out.println("Data already exists!");
                }
            }
        }

        return returnValue.toLowerCase();
    }

    public String number (String print) {
        String returnValue = new String();

        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.print(print);

            returnValue = userInput.nextLine().trim();
            if (validate.containsOnlyNumbers(returnValue)) {
                inputIsValid = true;
            }
        }

        return returnValue;
    }

    public String email (String print) {
        String returnValue = new String();

        System.out.print(print);

        returnValue = userInput.nextLine().trim();

        return returnValue;
    }

    public String departments (String print) {
        String returnValue = new String();

        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.print(print);

            returnValue = userInput.nextLine().trim();
            if (validate.containsOnlyLetters(returnValue)) {
                inputIsValid = true;
            }
        }

        return returnValue;
    }

}
