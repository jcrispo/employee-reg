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
import java.util.*;

public class EditController implements Controller {
    EmployeeDAOImpl employeeDAO;
    QueryManager queryManager;
    EmployeeDataValidation employeeDataValidation;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("modify");

        String id = request.getParameter("id");
        String field = request.getParameter("field");
        String message = "Employee Data Edited.";
        String hqlQuery;

        try {
            Map<String, Object> namedParameter = new HashMap<String, Object>();
            hqlQuery = queryManager.getQuery("UPDATE_" + field.split(":")[1]);
            String fieldValue;

            if (field.contains("Name")) {
                fieldValue = employeeDataValidation.name(request.getParameter("fieldValue"));
                namedParameter.put(field, fieldValue);
            } else if (field.contains("Date")) {
                Date date = employeeDataValidation.dateString(request.getParameter("fieldValue"));
                namedParameter.put(field, date);
            } else if (field.contains("salary")) {
                Integer salary = employeeDataValidation.number(request.getParameter("fieldValue"));
                namedParameter.put(field, salary);
            } else if (field.contains("gender")) {
                String gender = employeeDataValidation.gender(request.getParameter("fieldValue"));
                namedParameter.put(field, gender);
            } else {
                fieldValue = request.getParameter("fieldValue").toString();
                namedParameter.put(field, fieldValue);
            }

            namedParameter.put(":id", Integer.valueOf(request.getParameter("id")));

            employeeDAO.modifyEmployeeData(hqlQuery, namedParameter);
        } catch (InvalidInputException e) {
            message = e.toString();
        } catch (NumberFormatException e) {
            message = "Invalid number.";
        } catch (HibernateException e) {
            message = "Error editing of Employee data.";
        }
        String condition = " WHERE E.id = " + id;
        hqlQuery = queryManager.getQuery("SELECT_ALL_JOIN_ALL") + condition;

        List<Object[]> employeeData = employeeDAO.getEmployeeData(hqlQuery, 0, 1);

        String[] labels = (String[]) employeeData.remove(employeeData.size()-1);
        employeeData.add(0,labels);

        hqlQuery = queryManager.getQuery("SELECT_ALL_JOIN_ALL") + condition;
        List<Object[]> positionData = employeeDAO.getEmployeeData("SELECT P.id AS ID, P.position AS Position FROM Position P ORDER BY P.position ASC" , 0, 0);

        if (positionData.size()==1) {
            return new ModelAndView("modifyPage", "message", "Position List is Empty.");
        }
        positionData.remove(positionData.size()-1);

        returnValue.addObject("id", id);
        returnValue.addObject("message", message);
        returnValue.addObject("employeeData", employeeData);
        returnValue.addObject("positionData", positionData);

        return returnValue;
    }

    public void setEmployeeDataValidation(EmployeeDataValidation employeeDataValidation) {
        this.employeeDataValidation = employeeDataValidation;
    }

    public void setEmployeeDAO(EmployeeDAOImpl employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }
}
