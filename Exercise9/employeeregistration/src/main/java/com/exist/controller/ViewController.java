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

public class ViewController implements Controller {
    private EmployeeDAOImpl employeeDAO;
    private QueryManager queryManager;
    private EmployeeDataValidation employeeDataValidation;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("view");

        try {
            String sortBy = request.getParameter("sortBy");
            String sort = request.getParameter("sort");
            int maxResult = employeeDataValidation.number(request.getParameter("maxResult"));

            String hqlQuery;
            Integer beginIndex;

            if (request.getParameter("beginIndex") == null) {
                beginIndex = 0;
            } else {
                beginIndex = Integer.valueOf(request.getParameter("beginIndex"));
            }

            if (maxResult <= 0) {
                throw new InvalidInputException("Maximum Result Value must be greater than 0.");
            }

            hqlQuery = queryManager.getQuery("SELECT_ALL_JOIN_ALL");

            List<Object[]> employeeData = employeeDAO.getEmployeeData(hqlQuery + sortBy + sort, beginIndex, maxResult);

            String[] labels = (String[]) employeeData.remove(employeeData.size()-1);

            employeeData.add(0,labels);

            returnValue.addObject("sort", sort);
            returnValue.addObject("sortBy", sortBy);
            returnValue.addObject("maxResult", maxResult);
            returnValue.addObject("beginIndex", beginIndex+maxResult);
            returnValue.addObject("employeeData", employeeData);
        } catch (InvalidInputException e) {
            return new ModelAndView("viewPage", "message", e.toString());
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
