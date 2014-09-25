package com.exist.storage;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private String email;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
