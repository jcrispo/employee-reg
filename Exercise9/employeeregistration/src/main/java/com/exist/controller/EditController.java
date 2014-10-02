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
    private static final int EMPTY = 1;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("modify");

        String message = null;

        try {
            Integer id = employeeDataValidation.number(request.getParameter("id"));
            String field = request.getParameter("field");

            Map<String, Object> namedParameter = new HashMap<String, Object>();
            String hqlQuery;

            if (field.contains("Name")) {
                String name = employeeDataValidation.name(request.getParameter("fieldValue"));

                namedParameter.put(field, name);
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
                String fieldValue = request.getParameter("fieldValue").toString();
                namedParameter.put(field, fieldValue);
            }

            hqlQuery = queryManager.getQuery("UPDATE_" + field.split(":")[1]);

            namedParameter.put(":id", id);

            employeeDAO.modifyEmployeeData(hqlQuery, namedParameter);

            String condition = " WHERE E.id = " + id;
            hqlQuery = queryManager.getQuery("SELECT_ALL_JOIN_ALL") + condition;

            List<Object[]> employeeData = employeeDAO.getEmployeeData(hqlQuery, 0, 1);

            String[] labels = (String[]) employeeData.remove(employeeData.size()-1);

            employeeData.add(0,labels);

            hqlQuery = queryManager.getQuery("S_ALL_Positions_ORDER_Position");

            List<Object[]> positionData = employeeDAO.getEmployeeData(hqlQuery, 0, 0);

            if (positionData.size()==EMPTY) {
                return new ModelAndView("modifyPage", "message", "Position List is Empty.");
            }

            positionData.remove(positionData.size()-1);

            returnValue.addObject("employeeData", employeeData);
            returnValue.addObject("positionData", positionData);

            message = "Employee Data Edited.";
        } catch (InvalidInputException e) {
            message = e.toString();
        } catch (NumberFormatException e) {
            message = "Invalid number.";
        } catch (HibernateException e) {
            message = "Error editing of Employee data.";
        }

        returnValue.addObject("id", request.getParameter("id"));
        returnValue.addObject("message", message);

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
