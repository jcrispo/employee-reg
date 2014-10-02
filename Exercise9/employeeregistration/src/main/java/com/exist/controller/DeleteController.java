package com.exist.controller;

import com.exist.dao.EmployeeDAOImpl;
import com.exist.services.EmployeeDataValidation;
import com.exist.services.QueryManager;
import com.exist.services.utilities.InvalidInputException;
import org.hibernate.HibernateException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DeleteController implements Controller {
    EmployeeDataValidation employeeDataValidation;
    EmployeeDAOImpl employeeDAO;
    QueryManager queryManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("result");

        String message;

        try {
            Integer id = employeeDataValidation.number(request.getParameter("id"));

            Map<String, Object> namedParameter = new HashMap<String, Object>();
            String hqlQuery = queryManager.getQuery("DEL_Employee_WHERE_ID");

            message = "Employee Data Deleted.";

            namedParameter.put(":id", id);

            employeeDAO.modifyEmployeeData(hqlQuery, namedParameter);
        } catch (InvalidInputException e) {
            message = e.toString();
        } catch (HibernateException e) {
            message = e.toString();
            e.printStackTrace();
        }

        returnValue.addObject("message", message);

        return returnValue;
    }

    public void setEmployeeDAO(EmployeeDAOImpl employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    public void setEmployeeDataValidation(EmployeeDataValidation employeeDataValidation) {
        this.employeeDataValidation = employeeDataValidation;
    }
}
