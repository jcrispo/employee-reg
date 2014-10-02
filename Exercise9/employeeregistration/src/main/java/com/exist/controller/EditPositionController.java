package com.exist.controller;

import com.exist.dao.EmployeeDAOImpl;
import com.exist.services.QueryManager;
import com.exist.services.utilities.InvalidInputException;
import org.hibernate.HibernateException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditPositionController implements Controller {
    EmployeeDAOImpl employeeDAO;
    QueryManager queryManager;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("modify");

        String id = null;
        String message = null;
        try {
            id = request.getParameter("id");
            message = "Edited Employee's Position";
            String hqlQuery;

            Map<String, Object> namedParameter = new HashMap<String, Object>();
            String fieldValue = request.getParameter("positionId");

            namedParameter.put(":positionId", Integer.valueOf(fieldValue));
            namedParameter.put(":id", Integer.valueOf(id));

            hqlQuery = queryManager.getQuery("UPDATE_positionId");

            employeeDAO.modifyEmployeeData(hqlQuery, namedParameter);

            String condition = " WHERE E.id = " + id;
            hqlQuery = queryManager.getQuery("SELECT_ALL_JOIN_ALL") + condition;

            List<Object[]> employeeData = employeeDAO.getEmployeeData(hqlQuery, 0, 1);

            String[] labels = (String[]) employeeData.remove(employeeData.size()-1);
            employeeData.add(0,labels);

            hqlQuery = queryManager.getQuery("S_ALL_Positions_ORDER_Position");

            List<Object[]> positionData = employeeDAO.getEmployeeData(hqlQuery , 0, 0);

            if (positionData.size()==1) {
                return new ModelAndView("modifyPage", "message", "Position List is Empty.");
            }
            positionData.remove(positionData.size()-1);

            returnValue.addObject("employeeData", employeeData);
            returnValue.addObject("positionData", positionData);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            message = e.toString();
        } catch (HibernateException e) {
            e.printStackTrace();
            message = "Editing Position Failed.";
        }

        returnValue.addObject("id", id);
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
