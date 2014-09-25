package com.exist.storage;

import java.io.Serializable;
import java.util.Date;

public class EmployeeData implements Serializable{
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private Date hireDate;
    private String email;
    private Integer employeeId;
    private Integer positionId;
    private Integer salary;

    public void setEmployeeId (Integer id) {
        employeeId = id;
    }

    public void setFirstName (String fName) {
        firstName = fName;
    }

    public void setMiddleName (String mName) {
        middleName = mName;
    }

    public void setLastName (String lName) {
        lastName = lName;
    }

    public void setGender (String tempGender) {
        gender = tempGender;
    }

    public void setPosition (Integer positionNum) {
        positionId = positionNum;
    }

    public void setSalary (Integer money) {
        salary = money;
    }

    public void setEmail (String mail) {
        email = mail;
    }

    public Integer getEmployeeId () {
        return employeeId;
    }

    public String getFirstName () {
        return firstName;
    }

    public String getMiddleName () {
        return middleName;
    }

    public String getLastName () {
        return lastName;
    }

    public String getGender () {
        return gender;
    }

    public String getEmail () {
        return email;
    }

    public Integer getPosition () {
        return positionId;
    }

    public Integer getSalary () {
        return salary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}
