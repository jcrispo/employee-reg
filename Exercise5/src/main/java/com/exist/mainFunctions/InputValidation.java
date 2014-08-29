package com.exist.mainFunctions;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

public class InputValidation {
    private static final String INVALID = "\nInvalid Input! ";
    private static final Integer LOWEST_VALID_YEAR = 1000;
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
    private static final String DAY_INPUT_INVALID = "\nInput Invalid. Value may be exceeding the number of Days in this given Month or is less than 1.\nPlease try Again\n";

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

        if (date.get(YEAR) > currentDate.get(YEAR)) {
            System.out.println("\nInput data shouldn't exceed the current date: year\n");

            retVal = false;
        } else if (date.get(YEAR) < LOWEST_VALID_YEAR) {
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
