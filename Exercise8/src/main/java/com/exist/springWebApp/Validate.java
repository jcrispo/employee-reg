package com.exist.springWebApp;

public class Validate {

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
                    System.out.println("Invalid! Input should only contain letters!");

                    retVal = false;
                }
            }
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
                System.out.println("Invalid! Data may only contain numbers and must not have spaces in between!");

                retVal = false;
            }
        }

        return retVal;
    }

}
