package com.exist.controller;

import com.exist.dao.EmployeeDAOImpl;
import com.exist.services.QueryManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DeleteController implements Controller {
    EmployeeDAOImpl employeeDAO;
    QueryManager queryManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("result");

        Map<String, Object> namedParameter = new HashMap<String, Object>();

        namedParameter.put(":id", Integer.valueOf(request.getParameter("id")));
        String hqlQuery = queryManager.getQuery("DEL_Employee_WHERE_ID");

        employeeDAO.modifyEmployeeData(hqlQuery, namedParameter);

        String message = "Employee Data Deleted.";

        returnValue.addObject("message", message);

        return returnValue;
    }

    public void setEmployeeDAO(EmployeeDAOImpl employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }
}
