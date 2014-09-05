package com.exist.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBDataSource {
    private static BasicDataSource bds;
    private static DBDataSource ds;
    private static final String propertyFileDir = "src/main/resources/db.properties";

    public DBDataSource () throws IOException, SQLException {
        try {
            InputStream in = new FileInputStream(propertyFileDir);
            Properties dbProperty = new Properties();

            dbProperty.load(in);

            bds = new BasicDataSource();

            bds.setDriverClassName(dbProperty.getProperty("db.Driver"));
            bds.setUsername(dbProperty.getProperty("db.Username"));
            bds.setPassword(dbProperty.getProperty("db.Password"));
            bds.setUrl(dbProperty.getProperty("db.Url"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static DBDataSource getInstance () throws SQLException {
        if (ds == null) {
            try {
                ds = new DBDataSource();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        return ds;
    }

    public Connection getConnection () throws SQLException {
        return this.bds.getConnection();
    }

}
