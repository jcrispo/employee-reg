package com.exist.main_Functions;

public class EmployeeData{
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String birthDate;
    private String hireDate;
    private String email;
    private String position;
    private String department;
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

    public void setBirthDate (String bdate) {
        birthDate = bdate;
    }

    public void setHireDate (String hdate) {
        hireDate = hdate;
    }

    public void setPosition (Integer positionNum) {
        positionId = positionNum;
    }

    public void setPositionName (String posName) {
        position = posName;
    }

    public void setDepartmentName (String deptName) {
        department = deptName;
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
        return positionId;
    }

    public String getPositionName () {
        return position;
    }

    public String getDepartmentName () {
        return department;
    }

    public Integer getSalary () {
        return salary;
    }
	
}
