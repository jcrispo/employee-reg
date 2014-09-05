package com.exist.database;

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.exist.mainFunctions.EmployeeData;
import com.exist.mainFunctions.CompanyData;
import com.exist.database.DBDataSource;
import com.exist.database.DBRetrieve;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DBInsert {

    public void editData (String sql) {
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();

            insertStatement = dbConnection.prepareStatement(sql);
            insertStatement.executeUpdate();

            insertStatement.close();
        } catch (SQLException e) {

            String exception = e.toString();
            if (exception.contains("Duplicate entry")) {
                System.out.println("Edit Failed.");

                System.out.println((exception.split(" ", 2)[1]).split("for", 2)[0]);
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

    public void rollbackAutoIncrement(String queryLastId, String tableName) {
        DBRetrieve dbGet = new DBRetrieve();

        Connection connection = null;
        PreparedStatement prepstatement = null;
        Statement statement = null;

        try {
            List<List<String>> data = dbGet.getData(queryLastId); 

            String employeeIdIncrement = String.valueOf(Integer.valueOf((data.get(2)).get(0)) + 1);

            String query = "ALTER TABLE "+ tableName + " AUTO_INCREMENT = " + employeeIdIncrement;

            connection = DBDataSource.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void insertEmployeeData (EmployeeData data) {
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;

        boolean flagDuplicateEntry = false;
        try {
            dbConnection = DBDataSource.getInstance().getConnection();
            dbConnection.setAutoCommit(false);

            insertStatement = dbConnection.prepareStatement(DBRetrieve.getQuery("INSERT_personalInfo"));
            insertStatement.setString(1, data.getFirstName());
            insertStatement.setString(2, data.getMiddleName());
            insertStatement.setString(3, data.getLastName());
            insertStatement.setString(4, data.getGender());
            insertStatement.setString(5, data.getBirthDate());
            insertStatement.executeUpdate();

            insertStatement = dbConnection.prepareStatement(DBRetrieve.getQuery("INSERT_companyEmployeeData"));
            insertStatement.setString(1, data.getPosition().toString());
            insertStatement.setString(2, data.getHireDate());
            insertStatement.setString(3, data.getSalary().toString());
            insertStatement.setString(4, data.getEmail());
            insertStatement.executeUpdate();

            dbConnection.commit();
        } catch (SQLException e) {

            String exception = e.toString();
            if (exception.contains("Duplicate entry")) {
                System.out.println("Adding given data to database Failed.");

                System.out.println((exception.split(" ", 2)[1]).split("for", 2)[0]);

                flagDuplicateEntry = true;
            } else {
                e.printStackTrace();
            }

            try {
                dbConnection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
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
                if (flagDuplicateEntry) {
                    DBInsert dbIn = new DBInsert();

                    dbIn.rollbackAutoIncrement(DBRetrieve.getQuery("SELECT_lastID_personalInfo"), "personalInfo");
                    dbIn.rollbackAutoIncrement(DBRetrieve.getQuery("SELECT_lastID_personalInfo"), "companyEmployeeData");
                }
        }

    }

    public void insertPosition (CompanyData positionList) {
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();

            insertStatement = dbConnection.prepareStatement(DBRetrieve.getQuery("INSERT_employeePosition"));
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

            insertStatement = dbConnection.prepareStatement(DBRetrieve.getQuery("INSERT_departments"));
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

    public void addRandomData() {
        Connection dbConnection = null;
        PreparedStatement insertStatement = null;
        PreparedStatement insertStatement1 = null;

        boolean flagDuplicateEntry = false;
        try {
            dbConnection = DBDataSource.getInstance().getConnection();
            dbConnection.setAutoCommit(false);

            insertStatement = dbConnection.prepareStatement(DBRetrieve.getQuery("INSERT_personalInfo"));
            insertStatement1 = dbConnection.prepareStatement(DBRetrieve.getQuery("INSERT_companyEmployeeData"));

            String[] gender = {"male", "female"};
            String[] hireDate = {"2010-05-13", "2013-09-01", "2014-01-29", "2005-04-07", "2003-08-25", "2007-07-17", "2012-03-11", "2000-12-03", "2009-07-06", "2004-10-23", "2002-06-04"};
            Integer[] positionNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 11, 12, 13};
            String path1 = "src/main/resources/firstnames.txt";
            String path2 = "src/main/resources/middlenames.txt";
            String path3 = "src/main/resources/lastnames.txt";
            String path4 = "src/main/resources/birthdates.txt";

            Random random = new Random();
            int emailNumber = 0;

            File file1 = new File(path1);
            FileReader fr1 = new FileReader(file1);
            BufferedReader br1 = new BufferedReader(fr1);
            for (String firstName; (firstName = br1.readLine()) != null; ) {

                File file2 = new File(path2);
                FileReader fr2 = new FileReader(file2);
                BufferedReader br2 = new BufferedReader(fr2);
                for (String middleName; (middleName = br2.readLine()) != null; ) {

                    File file3 = new File(path3);
                    FileReader fr3 = new FileReader(file3);
                    BufferedReader br3 = new BufferedReader(fr3);
                    for (String lastName; (lastName = br3.readLine()) != null; ) {

                        File file4 = new File(path4);
                        FileReader fr4 = new FileReader(file4);
                        BufferedReader br4 = new BufferedReader(fr4);
                        for (String birthDate; (birthDate = br4.readLine()) != null; ) {

                            emailNumber++;
                            String email = firstName.substring(0,1) + middleName.substring(0,1) + lastName.substring(0,1) + emailNumber + "@exist.com";

                            insertStatement.setString(1, firstName);
                            insertStatement.setString(2, middleName);
                            insertStatement.setString(3, lastName);
                            insertStatement.setString(4, gender[random.nextInt(gender.length)]);
                            insertStatement.setString(5, birthDate);
                            insertStatement.addBatch();

                            insertStatement1.setString(1, positionNum[random.nextInt(positionNum.length)].toString());
                            insertStatement1.setString(2, hireDate[random.nextInt(hireDate.length)]);
                            insertStatement1.setString(3, String.valueOf(10000 + random.nextInt(20000)));
                            insertStatement1.setString(4, email);
                            insertStatement1.addBatch();
                        }
                    }
                }
            }

            insertStatement.executeBatch();
            insertStatement1.executeBatch();

            dbConnection.commit();
        } catch (SQLException e) {

            String exception = e.toString();
            if (exception.contains("Duplicate entry")) {
                System.out.println("Adding given data to database Failed.");

                flagDuplicateEntry = true;

                System.out.println((exception.split(" ", 2)[1]).split("for", 2)[0]);
            } else {
                e.printStackTrace();
            }

            try {
                dbConnection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

            if (flagDuplicateEntry) {
                DBInsert dbIn = new DBInsert();

                dbIn.rollbackAutoIncrement(DBRetrieve.getQuery("SELECT_lastID_personalInfo"), "personalInfo");
                dbIn.rollbackAutoIncrement(DBRetrieve.getQuery("SELECT_lastID_personalInfo"), "companyEmployeeData");
            }
        }

    }

}
