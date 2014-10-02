package com.exist.controller;

import com.exist.dao.EmployeeDAOImpl;
import org.hibernate.HibernateException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PopulateDBController implements Controller {
    EmployeeDAOImpl employeeDAO;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String message;

        try {
            employeeDAO.populateDB();

            message = "Populating Database with random data successful";
        } catch (HibernateException e) {
            message = "Database already contains data";
        }

        return new ModelAndView("result", "message", message);
    }

    public void setEmployeeDAO(EmployeeDAOImpl employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
