package com.exist.storage;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        StringBuffer returnValue = new StringBuffer();

        returnValue.append(" ID: " + id + ";");
        returnValue.append(" First Name: " + email + ";");
        returnValue.append(" Last Name: " + lastName + ";");

        return returnValue.toString();
    }
}
