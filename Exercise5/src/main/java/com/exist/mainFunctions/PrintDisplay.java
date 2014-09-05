package com.exist.mainFunctions;

import java.util.Formatter;
import java.util.List;

public class PrintDisplay {

    public void printDatabaseData (List<List<String>> databaseData) {
        System.out.println();

        List<String> columnWidth = databaseData.remove(0);
        for (List<String> row : databaseData) {

            int columnIndex = 0;
            for (String column : row) {
                System.out.format("|%-" + columnWidth.get(columnIndex) + "s", column);

                columnIndex++;
            }
            System.out.println();
        }

    }

}
