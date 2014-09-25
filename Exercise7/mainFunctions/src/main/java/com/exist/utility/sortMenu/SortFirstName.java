package com.exist.utility.sortMenu;

import java.util.Scanner;

public class SortFirstName extends SortMenu {

    @Override
    public String parameter() {
        String returnValue = new String(" ");

        Scanner input = new Scanner(System.in);
        String userInput = new String();

        boolean exit = false;
        while (!exit) {
            System.out.print("Sort (1) Ascending or (2) Descending: ");

            userInput = input.nextLine().trim();

            if (userInput.equals("1")) {
                returnValue = " ORDER BY E.firstName ASC ";

                exit = true;
            } else if (userInput.equals("2")) {
                returnValue = " ORDER BY E.firstName DESC ";

                exit = true;
            } else {
                System.out.println("Invalid input! Choose only between '1' (Ascending) and '2' (Descending).");
            }
        }

        return returnValue;
    }

    @Override
    public boolean exitViewMenu() {
        return true;
    }

}
