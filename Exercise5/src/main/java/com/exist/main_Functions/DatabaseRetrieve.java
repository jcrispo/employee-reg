package com.exist.main_Functions;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseRetrieve {
    private ResultSet data;
    private Statement sqlStatement;
    private Connection dbConnection;
    private static String databaseUrl;
    private static String databaseUser;
    private static String databasePassword;
    private static final String QUERYDEPARTMENTS = "SELECT * FROM departments";
    private static final String QUERYPOSITIONS = "SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name"
      + " FROM employeePosition LEFT JOIN departments ON employeePosition.deptId = departments.deptId";
    private static final String ALLDATAQUERY = "SELECT personalInfo.employeeId, personalInfo.firstName, personalInfo.middleName, personalInfo.lastName, personalInfo.birthDate,"
      + " personalInfo.gender, company.hireDate, company.position_name, company.dept_name, company.basicSalary, company.emailId FROM personalInfo"
      + " LEFT JOIN (SELECT companyEmployeeData.employeeId, companyEmployeeData.hireDate, posdept.position_refId, posdept.position_name,  posdept.dept_name,"
      + " companyEmployeeData.basicSalary, companyEmployeeData.emailId FROM companyEmployeeData"
      + " LEFT JOIN (SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition"
      + " LEFT JOIN departments ON employeePosition.deptId=departments.deptId) AS posdept"
      + " ON companyEmployeeData.position_refId=posdept.position_refId) AS company"
      + " ON personalInfo.employeeId=company.employeeId";

    public DatabaseRetrieve () {
        dbConnection = null;
        sqlStatement = null;
        data = null;
    }

    public void setCredentials (String url, String user, String pass) {
        databaseUrl = url;
        databaseUser = user;
        databasePassword = pass;
    }

    public boolean resultIsEmpty (String query) {
        DatabaseRetrieve mainClass = new DatabaseRetrieve();
        try {
            dbConnection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            data = mainClass.getData(dbConnection, query);
            if (!data.isBeforeFirst()){
                return true;
            }
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public ResultSet getData (Connection dbConnect, String sqlQuery) {
        try {
            sqlStatement = dbConnect.createStatement();
            data = sqlStatement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    } 

    public ResultSet employeeInfo (Connection dbConnect) {
        DatabaseRetrieve retrieve = new DatabaseRetrieve();
        return retrieve.getData(dbConnect, ALLDATAQUERY); 
    }

    public ResultSet allPositions (Connection dbConnect) {
        DatabaseRetrieve retrieve = new DatabaseRetrieve();
        return retrieve.getData(dbConnect, QUERYPOSITIONS);
    }

    public ResultSet allDepartments (Connection dbConnect) {
        DatabaseRetrieve retrieve = new DatabaseRetrieve();
        return retrieve.getData(dbConnect, QUERYDEPARTMENTS);
    }

} 
