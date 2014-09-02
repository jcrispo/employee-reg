package com.exist.menu.sortMenu;

import java.util.Scanner;
import com.exist.database.DBRetrieve;

public class SortId extends SortMenu {

    public String parameter() {
        String returnValue = new String(" ");

        Scanner input = new Scanner(System.in);
        String userInput = new String();

        boolean exit = false;
        while (!exit) {
            System.out.print("Sort (1) Ascending or (2) Descending: ");

            userInput = input.nextLine().trim();

            if (userInput.equals("1")) {
                returnValue = " " + DBRetrieve.getQuery("sortIdA") + " ";

                exit = true;
            } else if (userInput.equals("2")) {
                returnValue = " " + DBRetrieve.getQuery("sortIdD") + " ";

                exit = true;
            } else {
                System.out.println("Invalid input! Choose only between '1' (Ascending) and '2' (Descending).");
            }
        }

        return returnValue;
    }

    public String queryA() {
        return "queryDataLimitA";
    }
    
    public String queryB() {
        return "queryDataLimitB";
    }


    public boolean exitViewMenu() {
        return true;
    }

}
