package com.exist.main_Functions;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
    private static PreparedStatement updateStatement;
    private static Connection dbConnection;
    private static ResultSet result;
    private static Statement sqlStatement;
    private static String databaseURL;
    private static String databaseUser;
    private static String databasePassword;

    public Database () {
    }

    public void setCredentials (String url, String user, String pass) {
        databaseURL = url;
        databaseUser = user;
        databasePassword = pass;
    }

    public boolean loginDatabase () {
        try {
            dbConnection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public ResultSet getData (String sql) {
        try {
            sqlStatement = dbConnection.createStatement();
            result = sqlStatement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error getting data from Database!");
        }
        return result;
    }

    public boolean insertStatement (String sql) {
        try {
            updateStatement = dbConnection.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error in Prepared Statement!");
            return false;
        }
        return true;
    }

    public boolean insertIntoDatabase (int fieldNumber, String stringData) {
        try {
            updateStatement.setString(fieldNumber, stringData);
        } catch (SQLException e) {
            System.out.println("Error inserting data into Database!");
            return false;
        }
        return true;
    }

    public boolean updateDatabase () {
        try {
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in adding data into Database!");
			System.out.println(e);//delete
            return false;
        }
        return true;
    }

    public boolean closeDatabase () {
        try {
            if (updateStatement != null) {
                updateStatement.close();
            }
            if (sqlStatement != null) {
                sqlStatement.close();
            }
            if (result != null) {
                result.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing Database!");
            return false;
        }
        return true;
    }

    public String getURL () {
        return databaseURL;
    }
    public String getUser () {
        return databaseUser;
    }
    public String getPassword () {
        return databasePassword;
    }

}
