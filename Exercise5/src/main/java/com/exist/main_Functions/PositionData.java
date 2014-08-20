package com.exist.main_Functions;

public class PositionData {
    private String position;
    private Integer deptId;

    public void addPositionName (String newPosition) {
        position = newPosition;
    }

    public void setDeptId (Integer id) {
        deptId = id;
    }

    public String getPositionName () {
        return position;
    }

    public Integer getDeptId () {
        return deptId;
    }

}
