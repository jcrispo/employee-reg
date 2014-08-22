package com.exist.main_Functions;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Insert {
    private Connection dbConnection;
    private PreparedStatement insertStatement;
    private static String databaseUrl;
    private static String databaseUser;
    private static String databasePassword;
    private static final String sqlStatement1 = "INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlStatement2 = "INSERT INTO companyEmployeeData (position_refId, hireDate, basicSalary, emailId) VALUES (?, ?, ?, ?)";
    private static final String sqlStatement3 = "INSERT INTO employeePosition (position_name, deptId) VALUES (?, ?)";
    private static final String sqlStatement4 = "INSERT INTO departments (dept_name) VALUES (?)";

    public Insert () {
        dbConnection = null;
        insertStatement = null;
    }

    public void setCredentials (String url, String user, String pass) {
        databaseUrl = url;
        databaseUser = user;
        databasePassword = pass;
    }

    public void insertEmployeeData (EmployeeData data) {
        try {
            dbConnection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            insertStatement = dbConnection.prepareStatement(sqlStatement1);
            insertStatement.setString(1, data.getFirstName());
            insertStatement.setString(2, data.getMiddleName());
            insertStatement.setString(3, data.getLastName());
            insertStatement.setString(4, data.getGender());
            insertStatement.setString(5, data.getBirthDate());
            insertStatement.executeUpdate();
            insertStatement = dbConnection.prepareStatement(sqlStatement2);
            insertStatement.setString(1, data.getPosition().toString());
            insertStatement.setString(2, data.getHireDate());
            insertStatement.setString(3, data.getSalary().toString());
            insertStatement.setString(4, data.getEmail());
            insertStatement.executeUpdate();
            insertStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

    public void insertPosition (CompanyData positionList) {
        try {
            dbConnection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            insertStatement = dbConnection.prepareStatement(sqlStatement3);
            insertStatement.setString(1, positionList.getPositionName());
            insertStatement.setString(2, positionList.getDeptId().toString());
            insertStatement.executeUpdate();
            insertStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewDept (String deptName) {
        try {
            dbConnection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            insertStatement = dbConnection.prepareStatement(sqlStatement4);
            insertStatement.setString(1, deptName);
            insertStatement.executeUpdate();
            insertStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
