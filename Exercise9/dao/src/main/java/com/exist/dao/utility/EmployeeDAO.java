package com.exist.dao.utility;

import com.exist.models.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {

    public void setEmployee(Employee employee);
    public List<Object[]> getEmployeeData(String hqlQuery, int beginIndex, int maxResult);
    public void modifyEmployeeData(String hqlQuery, Map<String, Object> namedParameter);

}
