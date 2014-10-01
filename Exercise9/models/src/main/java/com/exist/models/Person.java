package com.exist.models;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable{
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private CompanyEmployeeData companyEmployeeData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public CompanyEmployeeData getCompanyEmployeeData() {
        return companyEmployeeData;
    }

    public void setCompanyEmployeeData(CompanyEmployeeData companyEmployeeData) {
        this.companyEmployeeData = companyEmployeeData;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
