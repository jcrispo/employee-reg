package com.exist.services;


import com.exist.services.utilities.InvalidInputException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDataValidation {
    private InputValidation validate;
    private static final int WORD_LIMIT = 20;

    public EmployeeDataValidation(InputValidation validate) {
        this.validate = validate;
    }

    public Date dateString(String dateString) throws InvalidInputException {
        Calendar date = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            date.setTime(dateFormat.parse(dateString));

            if (!validate.dayIsValid(date) || !validate.monthIsValid(date) || !validate.yearIsValid(date)) {
                throw new InvalidInputException("Invalid Date");
            }
        } catch (ParseException e) {
            throw new InvalidInputException("Invalid Date");
        }

        return date.getTime();
    }

    public String name(String name) throws InvalidInputException {
        String returnValue = new String();

        if (validate.containsOnlyLetters(name)) {
            if (validate.belowCharLimit(name, WORD_LIMIT)) {
                returnValue = name;
            } else {
                throw new InvalidInputException("Invalid Name");
            }
        } else {
            throw new InvalidInputException("Invalid Name");
        }

        return returnValue;
    }

    public Integer number(String number) throws InvalidInputException {
        Integer returnValue;

        if (validate.containsOnlyNumbers(number)) {
            returnValue = Integer.valueOf(number);
        } else {
            throw new InvalidInputException("Invalid Number");
        }

        return returnValue;
    }

    public String gender(String gender) throws InvalidInputException {
        String returnValue;

        System.out.println(gender);

        if (gender.equals("male") || gender.equals("female")) {
            returnValue = gender;
        } else {
            throw new InvalidInputException("Invalid input for Gender. Please choose between 'male'/'female' only.");
        }

        return returnValue;
    }

    public String numericDataExists(String print, String query) {
        String returnValue = new String();

        boolean resultIsEmpty = true;
        while (resultIsEmpty) {
            System.out.print(print);

            if (validate.containsOnlyNumbers(returnValue)) {
                Map<String, Object> namedParameter = new HashMap<String, Object>();

                namedParameter.put(":id", Integer.valueOf(returnValue));

//                resultIsEmpty = fromDatabase.resultIsEmpty(query, namedParameter);
                if (resultIsEmpty){
                    System.out.println("ID number does not exist. Try again");
                }
            }
        }

        return returnValue;
    }

    public String newWordData(String print, String query) {
        String returnValue = new String();

        boolean resultIsNotEmpty = true;
        while (resultIsNotEmpty) {
            System.out.print(print);

            if (validate.containsOnlyLetters(returnValue) && validate.belowCharLimit(returnValue, WORD_LIMIT)) {
                Map<String, Object> namedParameter = new HashMap<String, Object>();

//                String parameter = HDBRetrieveManager.getNamedParameters(query).get(0);
//                namedParameter.put(parameter, returnValue);

//                resultIsNotEmpty = !fromDatabase.resultIsEmpty(query, namedParameter);
                if (resultIsNotEmpty){
                    System.out.println("Data already exists!");
                }
            }
        }

        return returnValue.toLowerCase();
    }

}