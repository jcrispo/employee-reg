package com.exist.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DB_Delete {
    private Connection dbConnection;
    private PreparedStatement deleteQuery;
    private static Properties dbProperty;

    public DB_Delete () {
        dbConnection = null;
        deleteQuery = null;
        dbProperty = new Properties();
    }

    public void deleteFromDb (String sqlQuery) {
        try {
            InputStream in = new FileInputStream("db.properties");
            dbProperty.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
        dbConnection = DriverManager.getConnection(dbProperty.getProperty("db.url"), dbProperty.getProperty("db.user"), dbProperty.getProperty("db.password"));
        deleteQuery = dbConnection.prepareStatement(sqlQuery);
        deleteQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   

}
