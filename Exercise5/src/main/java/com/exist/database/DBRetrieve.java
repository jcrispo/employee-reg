package com.exist.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.exist.database.DBDataSource;

public class DBRetrieve implements Retrieve {
    private static final String queryPropertyFileDir = "src/main/resources/query.properties";

    public boolean resultIsEmpty (String query) {
        boolean retVal = false;

        Connection dbConnection = null;
        ResultSet result = null;
        Statement statement = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();
            statement = dbConnection.createStatement();
            result = statement.executeQuery(query);

            if (!result.isBeforeFirst()){
                retVal = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            retVal = true;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return retVal;
    }

    @Override
    public List<List<String>> getData(String query) {
        List<List<String>> retVal = new ArrayList<List<String>>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBDataSource.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.isBeforeFirst()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                List<String> columnLabels = new ArrayList<String>();
                List<String> columnDisplaySize = new ArrayList<String>();

                for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                    columnLabels.add(metaData.getColumnLabel(columnIndex));
                    columnDisplaySize.add(String.valueOf(metaData.getPrecision(columnIndex)));
                }

                retVal.add(columnDisplaySize);
                retVal.add(columnLabels);

                while (resultSet.next()) {
                    List<String> columnData = new ArrayList<String>();

                    for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                        columnData.add(resultSet.getString(columnIndex));
                    } 

                    retVal.add(columnData); 
                }
                
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return retVal;
    }

    public static String getQuery (String propertyKey) {
        String returnValue = new String();

        try {
            Properties queryProperty = new Properties();
            InputStream in = new FileInputStream(queryPropertyFileDir);

            queryProperty.load(in);

            returnValue = queryProperty.getProperty(propertyKey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

} 
