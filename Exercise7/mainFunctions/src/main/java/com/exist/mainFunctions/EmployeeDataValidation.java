package com.exist.mainFunctions;

import com.exist.database.HDBRetrieveManager;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class EmployeeDataValidation {
    private InputValidation validate;
    private Scanner userInput;
    private HDBRetrieveManager fromDatabase;
    private static final int WORD_LIMIT = 20;

    public EmployeeDataValidation () {
        validate = new InputValidation();
        userInput = new Scanner(System.in);
        fromDatabase = new HDBRetrieveManager();
    }

    public Date dateString (String print) {
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

        return date.getTime();
    }

    public String name (String print) {
        String returnValue = new String();

        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.print(print);

            returnValue = userInput.nextLine();

            inputIsValid = validate.containsOnlyLetters(returnValue);

            if (inputIsValid) {
                inputIsValid = validate.belowCharLimit(returnValue, WORD_LIMIT);
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
                System.out.println("Invalid input. Please only choose between 'm' and 'f'. ");
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
                Map<String, Object> namedParameter = new HashMap<String, Object>();

                namedParameter.put(":id", Integer.valueOf(returnValue));

                resultIsEmpty = fromDatabase.resultIsEmpty(query, namedParameter);
                if (resultIsEmpty){
                    System.out.println("ID number does not exist. Try again");
                }
            }
        }

        return returnValue;
    }

    public String newWordData (String print, String query) {
        String returnValue = new String();

        boolean resultIsNotEmpty = true;
        while (resultIsNotEmpty) {
            System.out.print(print);

            returnValue = userInput.nextLine().trim();

            if (validate.containsOnlyLetters(returnValue) && validate.belowCharLimit(returnValue, WORD_LIMIT)) {
                Map<String, Object> namedParameter = new HashMap<String, Object>();

                String parameter = HDBRetrieveManager.getNamedParameters(query).get(0);
                namedParameter.put(parameter, returnValue);

                resultIsNotEmpty = !fromDatabase.resultIsEmpty(query, namedParameter);
                if (resultIsNotEmpty){
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

}