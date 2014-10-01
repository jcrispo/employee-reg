package com.exist.models;

import java.io.Serializable;
import java.util.Set;

public class Position implements Serializable {
    private int id;
    private int departmentId;
    private String position;
    private Department department;
    private Set<CompanyEmployeeData> companyEmployeeDataSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<CompanyEmployeeData> getCompanyEmployeeDataSet() {
        return companyEmployeeDataSet;
    }

    public void setCompanyEmployeeDataSet(Set<CompanyEmployeeData> companyEmployeeDataSet) {
        this.companyEmployeeDataSet = companyEmployeeDataSet;
    }

    @Override
    public String toString() {
        return "Position{" +
                "position='" + position + '\'' +
                ", departmentId=" + departmentId +
                ", id=" + id +
                '}';
    }
}
