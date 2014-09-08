package com.exist;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceConnection {
    private static BasicDataSource ds;
    private static ResultSet rs;
    private static Statement stm;
    private static Connection db;
    private static final String ALLDATAQUERY = "SELECT personalInfo.employeeId, personalInfo.firstName, personalInfo.middleName, personalInfo.lastName, personalInfo.birthDate,"
      + " personalInfo.gender, company.hireDate, company.position_name, company.dept_name, company.basicSalary, company.emailId FROM personalInfo"
      + " LEFT JOIN (SELECT companyEmployeeData.employeeId, companyEmployeeData.hireDate, posdept.position_refId, posdept.position_name,  posdept.dept_name,"
      + " companyEmployeeData.basicSalary, companyEmployeeData.emailId FROM companyEmployeeData"
      + " LEFT JOIN (SELECT employeePosition.position_refId, employeePosition.position_name, departments.dept_name FROM employeePosition"
      + " LEFT JOIN departments ON employeePosition.deptId=departments.deptId) AS posdept"
      + " ON companyEmployeeData.position_refId=posdept.position_refId) AS company"
      + " ON personalInfo.employeeId=company.employeeId";

    public DataSourceConnection () {
    }

    public static void main (String[] args) {
        Properties dbProperty = new Properties();
        try {
            InputStream in = new FileInputStream("src/main/resources/db.properties");
            dbProperty.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            ds = new BasicDataSource();
            ds.setDriverClassName(dbProperty.getProperty("db.Driver"));
            ds.setUsername(dbProperty.getProperty("db.Username"));
            ds.setPassword(dbProperty.getProperty("db.Password"));
            ds.setUrl(dbProperty.getProperty("db.Url"));
            db = ds.getConnection();
            stm = db.createStatement(); 
            System.out.println("Current Active Connection: " + ds.getNumActive());
            rs = stm.executeQuery(ALLDATAQUERY);
            while (rs.next()) {
                String id = rs.getString("personalInfo.employeeId");
                String name = rs.getString("personalInfo.firstName");
                System.out.println(id + "\t" + name);
            }
            db.close();
            System.out.println("Max Active Connection: " + ds.getMaxTotal());
            System.out.println("Current Active Connection: " + ds.getNumActive());
            System.out.println("Max Idle Connection: " + ds.getMaxIdle());
            System.out.println("Current Idle Connection: " + ds.getNumIdle());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
