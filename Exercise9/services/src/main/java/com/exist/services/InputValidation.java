package com.exist.services;

import java.util.Calendar;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.DATE;

public class InputValidation {
    private static final String INVALID = "\nInvalid Input! ";
    private static final Calendar currentDate = Calendar.getInstance();

    public static boolean stringIsNull (String input) {
        boolean retVal = false;

        if ((input.trim()).length() == 0) {
            System.out.println("No data was given");

            retVal = true;
        }

        return retVal;
    }

    public boolean containsOnlyLetters (String input) {
        boolean retVal = true;

        if (stringIsNull(input)) {
            retVal = false;
        } else {
            char[] word = (input.trim()).toCharArray();
            for (char wordChar : word) {
               if (!Character.isLetter(wordChar) && wordChar != ' ') {
                    System.out.println(INVALID + "Input should only contain letters!");

                    retVal = false;
                }
            }
        }

        return retVal;
    }

    public boolean belowCharLimit (String input, int limit) {
        boolean retVal = true;

        if ((input.trim()).length() > limit) {
            System.out.println(INVALID + "Input exceeds character limit of " + limit + "!");

            retVal = false;
        }

        return retVal;
    }

    public boolean containsOnlyNumbers (String number) {
        boolean retVal = true;

        if (stringIsNull(number)) {
            retVal = false; 
        } else {
            try {
                Integer.valueOf(number.trim());

                retVal = true;
            } catch (NumberFormatException e) {
                System.out.println(INVALID + "Data may only contain numbers and must not have spaces in between!");

                retVal = false;
            }
        }

        return retVal;
    }

    public boolean yearIsValid (Calendar date) {
        boolean retVal = true;

        Integer minimumValidYear = 1000;

        if (date.get(YEAR) > currentDate.get(YEAR)) {
            System.out.println("\nInput data shouldn't exceed the current date: year\n");

            retVal = false;
        } else if (date.get(YEAR) < minimumValidYear) {
            System.out.println("\nInput Year data should follow the format YYYY with Four significant digits\n");

            retVal = false;
        }

        return retVal;
    }

    public boolean monthIsValid (Calendar date) {
        boolean retVal = true;

        if (date.get(YEAR) == currentDate.get(YEAR) && date.get(MONTH) > currentDate.get(MONTH)) {
            System.out.println("\nInput value shouldn't exceed the current date: month\n");

            retVal = false;
        }

        return retVal;
    }

    public boolean dayIsValid (Calendar date) {
        boolean retVal = true;

        if (date.get(YEAR) == currentDate.get(YEAR) && date.get(MONTH) == currentDate.get(MONTH) && date.get(DATE) > currentDate.get(DATE)) {
            System.out.println("\nInput value shouldn't exceed the current date: day\n");

            retVal = false;
        }

        return retVal;
    }

}