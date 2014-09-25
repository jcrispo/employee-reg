package com.exist.storage;

import java.io.Serializable;
import java.util.Set;

public class Departments implements Serializable {
    private int id;
    private String department;
    private Set<Positions> positionsSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<Positions> getPositionsSet() {
        return positionsSet;
    }

    public void setPositionsSet(Set<Positions> positionsSet) {
        this.positionsSet = positionsSet;
    }
}
