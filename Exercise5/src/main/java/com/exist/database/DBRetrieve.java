package com.exist.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import com.exist.database.DBDataSource;
import java.util.List;
import java.util.ArrayList;

public class DBRetrieve implements Retrieve {
    private ResultSet data;
    private Statement sqlStatement;
    private Connection dbConnection;
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

    public DBRetrieve () {
        dbConnection = null;
        sqlStatement = null;
        data = null;
    }

    public boolean resultIsEmpty (String query) {
        boolean retVal = false;

        DBRetrieve mainClass = new DBRetrieve();
        try {
            dbConnection = DBDataSource.getInstance().getConnection();
            data = mainClass.getData(dbConnection, query);
            if (!data.isBeforeFirst()){
                retVal = true;
            }
            // dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            retVal = true;
        } finally {
            try {
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

            if (!resultSet.isBeforeFirst()){
                // TODO: Handle empty resultset
            } else {
                ResultSetMetaData metaData = resultSet.getMetaData();

                while (resultSet.next()) {
                    List<String> columnData = new ArrayList<String>();

                    for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                        columnData.add(resultSet.getString(columnIndex));
                    } 

                    retVal.add(columnData); 
                    //print.printEmployeeData(employee);
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

    public ResultSet getData (Connection dbConnect, String sqlQuery) {
        Statement statement = null;
        try {
            statement = dbConnect.createStatement();
            data = statement.executeQuery(sqlQuery);
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
/*
            try {
            if (statement != null) {
                statement.close();
            }
            } catch(SQLException e) {
                e.printStackTrace();
            }
*/
        }
        
    } 

    public ResultSet employeeInfo (Connection dbConnect) {
        DBRetrieve retrieve = new DBRetrieve();
        return retrieve.getData(dbConnect, ALLDATAQUERY); 
    }

    public ResultSet allPositions (Connection dbConnect) {
        DBRetrieve retrieve = new DBRetrieve();
        return retrieve.getData(dbConnect, QUERYPOSITIONS);
    }

    public ResultSet allDepartments (Connection dbConnect) {
        DBRetrieve retrieve = new DBRetrieve();
        return retrieve.getData(dbConnect, QUERYDEPARTMENTS);
    }

} 
