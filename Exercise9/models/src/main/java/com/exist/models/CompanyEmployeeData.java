package com.exist.models;

import java.util.Date;

public class CompanyEmployeeData {
    private int id;
    private int positionId;
    private int salary;
    private String email;
    private Date hireDate;
    private Person person;
    private Position position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "CompanyEmployeeData{" +
                "hireDate=" + hireDate +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", positionId=" + positionId +
                ", id=" + id +
                '}';
    }
}
