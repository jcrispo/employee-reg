package com.exist.controller;

import com.exist.dao.EmployeeDAOImpl;
import com.exist.services.EmployeeDataValidation;
import com.exist.services.QueryManager;
import com.exist.services.utilities.InvalidInputException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jcrispo on 10/1/14.
 */
public class ModifyController implements Controller {
    EmployeeDAOImpl employeeDAO;
    QueryManager queryManager;
    EmployeeDataValidation employeeDataValidation;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("modify");

        try {
            Integer id = employeeDataValidation.number(request.getParameter("id"));
            String condition = " WHERE E.id = " + id.toString();

            String hqlQuery = queryManager.getQuery("SELECT_ALL_JOIN_ALL") + condition;

            List<Object[]> employeeData = employeeDAO.getEmployeeData(hqlQuery, 0, 1);

            if (employeeData.size()==1) {
                return new ModelAndView("modifyPage", "message", "Employee ID does not exist.");
            }

            String[] labels = (String[]) employeeData.remove(employeeData.size()-1);

            employeeData.add(0,labels);

            returnValue.addObject("id", id);
            returnValue.addObject("employeeData", employeeData);

            hqlQuery = queryManager.getQuery("S_ALL_Positions_ORDER_Position");
            List<Object[]> positionData = employeeDAO.getEmployeeData(hqlQuery , 0, 0);

            if (positionData.size()==1) {
                return new ModelAndView("modifyPage", "message", "Position List is Empty.");
            }

            positionData.remove(positionData.size()-1);

            returnValue.addObject("positionData", positionData);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return new ModelAndView("modifyPage", "message", e.toString());
        }

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
