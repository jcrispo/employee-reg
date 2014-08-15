package com.exist.main_Functions;

public class InputValidation {
    private static final String INVALID = "\nInvalid Input! ";

    public static boolean stringIsNull (String input) {
        if ((input.trim()).length() == 0) {
            System.out.println("No data was given");
            return true;
        }
        return false;
    }

    public boolean containsOnlyLetters (String input) {
	if (stringIsNull(input)) {
            return false;
        }
        char[] word = (input.trim()).toCharArray();
        for (char wordChar : word) {
            if (!Character.isLetter(wordChar) && wordChar != ' ') {
                System.out.println(INVALID + "Input should only contain letters!");
                return false;
            }
        }
        return true;
    }

    public boolean belowCharLimit (String input, int limit) {
        if ((input.trim()).length() > limit) {
            System.out.println(INVALID + "Input exceeds character limit of " + limit + "!");
        return false;
        }
        return true;
    }

    public boolean containsOnlyNumbers (String number) {
	if (stringIsNull(number)) {
            return false; 
        }
        try {
            Integer.valueOf(number.trim());
            return true;
        } catch (NumberFormatException e) {
            System.out.println(INVALID + "Data may only contain numbers and must not have spaces in between!");
            return false;
        }
    }

}