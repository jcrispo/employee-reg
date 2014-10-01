package com.exist.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QueryManager {
    private String queryPropertyFileDir;

    public String getQuery (String propertyKey) {
        String returnValue = new String();

        queryPropertyFileDir = "src/main/resources/hqlQuery.properties";

        try {
            Properties queryProperty = new Properties();
            InputStream in = new FileInputStream(queryPropertyFileDir);

            queryProperty.load(in);

            returnValue = queryProperty.getProperty(propertyKey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public void setQueryPropertyFileDir(String queryPropertyFileDir) {
        this.queryPropertyFileDir = queryPropertyFileDir;
    }
}
