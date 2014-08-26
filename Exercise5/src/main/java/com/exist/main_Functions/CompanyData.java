package com.exist.main_Functions;

public class CompanyData {
    private String position;
    private String department;
    private Integer positionId;
    private Integer deptId;

    public void setPositionName (String newPosition) {
        position = newPosition;
    }

    public void setDepartmentName (String deptName) {
        department = deptName;
    }

    public void setPositionId (Integer id) {
        positionId = id;
    }

    public void setDeptId (Integer id) {
        deptId = id;
    }

    public String getPositionName () {
        return position;
    }

    public String getDepartmentName () {
        return department;
    }

    public Integer getPositionId () {
        return positionId;
    }

    public Integer getDeptId () {
        return deptId;
    }

}
