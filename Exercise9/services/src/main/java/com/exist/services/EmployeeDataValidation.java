package com.exist.services;

import com.exist.services.utilities.InvalidInputException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class EmployeeDataValidation {
    private InputValidation validate;
    private static final int WORD_LIMIT = 20;

    public EmployeeDataValidation(InputValidation validate) {
        this.validate = validate;
    }

    public Date dateString(String dateString) throws InvalidInputException {
        Calendar returnValue = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            returnValue.setTime(dateFormat.parse(dateString));

            if (!validate.dayIsValid(returnValue) || !validate.monthIsValid(returnValue) || !validate.yearIsValid(returnValue)) {
                throw new InvalidInputException("Invalid Date");
            }
        } catch (ParseException e) {
            throw new InvalidInputException("Invalid Date");
        }

        return returnValue.getTime();
    }

    public String name(String name) throws InvalidInputException {
        String returnValue;

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

        if (gender.equals("male") || gender.equals("female")) {
            returnValue = gender;
        } else {
            throw new InvalidInputException("Invalid input for Gender. Please choose between 'male'/'female' only.");
        }

        return returnValue;
    }

}