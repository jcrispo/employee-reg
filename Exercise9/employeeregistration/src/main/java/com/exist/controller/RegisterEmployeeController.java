package com.exist.controller;

import com.exist.dao.EmployeeDAOImpl;
import com.exist.models.Employee;
import com.exist.services.EmployeeDataValidation;
import com.exist.services.QueryManager;
import com.exist.services.utilities.InvalidInputException;
import org.hibernate.HibernateException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegisterEmployeeController implements Controller {
    private EmployeeDAOImpl employeeDAO;
    private EmployeeDataValidation employeeDataValidation;
    private QueryManager queryManager;
    private Employee employee;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String message = new String();
        try {
            returnValue = new ModelAndView("result");

            String firstName = employeeDataValidation.name(request.getParameter("firstName"));
            String middleName = employeeDataValidation.name(request.getParameter("middleName"));
            String lastName = employeeDataValidation.name(request.getParameter("lastName"));
            String gender = employeeDataValidation.gender(request.getParameter("gender"));
            Date birthDate = employeeDataValidation.dateString(request.getParameter("birthDate"));
            Date hireDate = employeeDataValidation.dateString(request.getParameter("hireDate"));
            int positionId = employeeDataValidation.number(request.getParameter("positionId"));
            int salary = employeeDataValidation.number(request.getParameter("salary"));
            String email = request.getParameter("firstName").substring(0, 1) + request.getParameter("middleName").substring(0, 1) + request.getParameter("lastName").substring(0, 1) + (employeeDAO.getLastId()+1) + "@exist.com";

            employee.setFirstName(firstName);
            employee.setMiddleName(middleName);
            employee.setLastName(lastName);
            employee.setGender(gender);
            employee.setEmail(email);
            employee.setBirthDate(birthDate);
            employee.setHireDate(hireDate);
            employee.setPositionId(positionId);
            employee.setSalary(salary);

            employeeDAO.setEmployee(employee);

            message = "\nData Registration Successful!";
        } catch (HibernateException e) {
            returnValue = new ModelAndView("registerEmployeePage");
            message = "Duplicate Entry. Data was not saved.";
        } catch (InvalidInputException e) {
            returnValue = new ModelAndView("registerEmployeePage");
            message = e.toString();
        } catch (NumberFormatException e) {
            returnValue = new ModelAndView("registerEmployeePage");
            message = e.toString();
        }

        String hqlQuery = queryManager.getQuery("S_ALL_Positions_ORDER_Position");

        List<Object[]> positionData = employeeDAO.getEmployeeData(hqlQuery , 0, 0);

        if (positionData.size()==1) {
            return new ModelAndView("result", "message", "Position List is Empty.");
        }

        positionData.remove(positionData.size()-1);

        returnValue.addObject("positionData", positionData);
        returnValue.addObject("message", message);

        return returnValue;
    }

    public EmployeeDAOImpl getEmployeeDAO() {
        return employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAOImpl employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public EmployeeDataValidation getEmployeeDataValidation() {
        return employeeDataValidation;
    }

    public void setEmployeeDataValidation(EmployeeDataValidation employeeDataValidation) {
        this.employeeDataValidation = employeeDataValidation;
    }

    public void setQueryManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
