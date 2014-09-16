package com.exist.mainFunctions;

import java.util.ArrayList;
import java.util.List;

public class Display {

    public void displayObjectArray(List<Object[]> dataList) {

        List<Integer> columnSize = new ArrayList<Integer>();

        String[] labels = (String[]) dataList.remove(dataList.size()-1);
        for (String label : labels) {
            if (label.contains("ID")) {
                columnSize.add(10);
                System.out.format("| %-10s ", "ID");
            }
            if (label.contains("First_Name")) {
                columnSize.add(20);
                System.out.format("| %-20s ", "First Name");
            }
            if (label.contains("Middle_Name")) {
                columnSize.add(20);
                System.out.format("| %-20s ", "Middle Name");
            }
            if (label.contains("Last_Name")) {
                columnSize.add(20);
                System.out.format("| %-20s ", "Last Name");
            }
            if (label.contains("Gender")) {
                columnSize.add(9);
                System.out.format("| %-9s ", "Gender");
            }
            if (label.contains("Birth_Date")) {
                columnSize.add(12);
                System.out.format("| %-12s ", "Birth Date");
            }
            if (label.contains("Hire_Date")) {
                columnSize.add(12);
                System.out.format("| %-12s ", "Hire Date");
            }
            if (label.contains("Position")) {
                columnSize.add(20);
                System.out.format("| %-20s ", "Position");
            }
            if (label.contains("Department")) {
                columnSize.add(20);
                System.out.format("| %-20s ", "Department");
            }
            if (label.contains("Email")) {
                columnSize.add(20);
                System.out.format("| %-20s ", "Email");
            }
            if (label.contains("Salary")) {
                columnSize.add(7);
                System.out.format("| %-7s ", "Salary");
            }

        }
        System.out.println();

        for (Object[] row : dataList) {
            int columnIndex = 0;
            for (Object column : row) {
                System.out.format("| %-" + columnSize.get(columnIndex++) + "s ", column.toString());
            }
            System.out.println();
        }
    }

}
