package com.exist.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.exist.database.DBDataSource;

public class DBDelete {

    public void deleteFromDb (String sqlQuery) {
        Connection dbConnection = null;
        PreparedStatement deleteQuery = null;

        try {
            dbConnection = DBDataSource.getInstance().getConnection();
            deleteQuery = dbConnection.prepareStatement(sqlQuery);
            deleteQuery.executeUpdate();

            System.out.println("Delete Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();    
            }
        }

    }   

}
