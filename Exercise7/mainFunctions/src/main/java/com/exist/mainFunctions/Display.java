package com.exist.mainFunctions;

import java.util.ArrayList;
import java.util.List;

public class Display {

    public void displayObjectArray(List<Object[]> dataList) {
        Display display = new Display();

        String[] labels = (String[]) dataList.remove(dataList.size()-1);

        List<Integer> columnSize = display.columnLabelSizeList(labels);

        System.out.println();
        for (Object[] row : dataList) {
            int columnIndex = 0;
            for (Object column : row) {
                System.out.format("| %-" + columnSize.get(columnIndex++) + "s ", column.toString());
            }
            System.out.println();
        }
    }

    public List<Integer> columnLabelSizeList (String[] labels) {
        List<Integer> returnValue = new ArrayList<Integer>();

        for (String label : labels) {
            if (label.contains("ID")) {
                returnValue.add(10);

                System.out.format("| %-10s ", "ID");
            }
            if (label.contains("First_Name")) {
                returnValue.add(20);

                System.out.format("| %-20s ", "First Name");
            }
            if (label.contains("Middle_Name")) {
                returnValue.add(20);

                System.out.format("| %-20s ", "Middle Name");
            }
            if (label.contains("Last_Name")) {
                returnValue.add(20);

                System.out.format("| %-20s ", "Last Name");
            }
            if (label.contains("Gender")) {
                returnValue.add(6);

                System.out.format("| %-6s ", "Gender");
            }
            if (label.contains("Birth_Date")) {
                returnValue.add(10);

                System.out.format("| %-10s ", "Birth Date");
            }
            if (label.contains("Hire_Date")) {
                returnValue.add(10);

                System.out.format("| %-10s ", "Hire Date");
            }
            if (label.contains("Position")) {
                returnValue.add(20);

                System.out.format("| %-20s ", "Position");
            }
            if (label.contains("Department")) {
                returnValue.add(20);

                System.out.format("| %-20s ", "Department");
            }
            if (label.contains("Email")) {
                returnValue.add(20);

                System.out.format("| %-20s ", "Email");
            }
            if (label.contains("Salary")) {
                returnValue.add(7);

                System.out.format("| %-7s ", "Salary");
            }
        }

        return returnValue;
    }

}
