package com.exist.controller;

import com.exist.dao.utility.EmployeeDAO;
import com.exist.services.QueryManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegisterEmployeePageController implements Controller {
    private EmployeeDAO employeeDAO;
    private QueryManager queryManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("registerEmployeePage");

        String hqlQuery = queryManager.getQuery("S_ALL_Positions_ORDER_Position");

        List<Object[]> positionData = employeeDAO.getEmployeeData(hqlQuery , 0, 0);

        if (positionData.size()==1) {
            return new ModelAndView("result", "message", "Position List is Empty.");
        }

        positionData.remove(positionData.size()-1);

        returnValue.addObject("positionData", positionData);

        return returnValue;
    }

    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
