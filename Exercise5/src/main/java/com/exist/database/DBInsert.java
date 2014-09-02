package com.exist.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.exist.mainFunctions.EmployeeData;
import com.exist.mainFunctions.CompanyData;
import com.exist.database.DBDataSource;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DBInsert {
    private static final String sqlStatement1 = "INSERT INTO personalInfo (firstName, middleName, lastName, gender, birthDate) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlStatement2 = "INSERT INTO companyEmployeeData (position_refId, hireDate, basicSalary) values (?, ?, ?)";
    private static final String sqlStatement3 = "INSERT INTO employeePosition (position_name, deptId) VALUES (?, ?)";
    private static final String sqlStatement4 = "INSERT INTO departments (dept_name) VALUES (?)";
    private static final String sqlStatement5 = "UPDATE companyEmployeeData company JOIN personalInfo employee ON company.employeeId=employee.employeeId  set company.emailId = ? WHERE employee.firstName = ? AND employee.middleName = ? AND employee.lastName = ? AND employee.birthDate = ? ";

    public void editData (String sql) {
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();

            insertStatement = dbConnection.prepareStatement(sql);
            insertStatement.executeUpdate();
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void insertEmployeeData (EmployeeData data) {
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();

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
            insertStatement.executeUpdate();

            insertStatement = dbConnection.prepareStatement(sqlStatement5);
            insertStatement.setString(1, data.getEmail());
            insertStatement.setString(2, data.getFirstName());
            insertStatement.setString(3, data.getMiddleName());
            insertStatement.setString(4, data.getLastName());
            insertStatement.setString(5, data.getBirthDate());
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            if (e instanceof MySQLIntegrityConstraintViolationException) {
                System.out.println("Duplicate Entry! Adding given data to database Failed.");
                e.printStackTrace();
            } else {
                e.printStackTrace();
            }
        } finally {
            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void insertPosition (CompanyData positionList) {
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();

            insertStatement = dbConnection.prepareStatement(sqlStatement3);
            insertStatement.setString(1, positionList.getPositionName());
            insertStatement.setString(2, positionList.getDeptId().toString());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void insertNewDept (String deptName) {
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();

            insertStatement = dbConnection.prepareStatement(sqlStatement4);
            insertStatement.setString(1, deptName);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
