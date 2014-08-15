package com.exist.menu.sort_Menu;

import java.util.Scanner;

public class Sort_HireDate extends Sort_Menu {
    private static Scanner input;

    public Sort_HireDate () {
        input = new Scanner(System.in);
    }

    public String parameter () {
        boolean exit = false;
        String userInput;
        while (!exit) {
            System.out.print("Sort (1) Ascending or (2) Descending: ");
            userInput = input.nextLine().trim();
            exit = true;
            if (userInput.equals("1")) {
                return " ORDER BY company.hireDate ASC";
            } else if (userInput.equals("2")) {
                return " ORDER BY company.hireDate DESC";
            } else {
                System.out.println("Invalid input! Choose only between '1' (Ascending) and '2' (Descending).");
                exit = false;
            }
        }
        return " ";
    }

    public boolean exitViewMenu () {
        return true;
    }

}