package com.exist.main_Functions;

public class EmployeeData{
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String birthDate;
    private String hireDate;
    private String email;
    private Integer position;
    private Integer salary;

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

    public void setBirthDate (String bdate) {
        birthDate = bdate;
    }

    public void setHireDate (String hdate) {
        hireDate = hdate;
    }

    public void setEmail (String mail) {
        email = mail;
    }

    public void setPosition (Integer positionNum) {
        position = positionNum;
    }

    public void setSalary (Integer money) {
        salary = money;
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

    public String getBirthDate () {
        return birthDate;
    }

    public String getHireDate () {
        return hireDate;
    }

    public String getGender () {
        return gender;
    }

    public String getEmail () {
        return email;
    }

    public Integer getPosition () {
        return position;
    }

    public Integer getSalary () {
        return salary;
    }
	
}
