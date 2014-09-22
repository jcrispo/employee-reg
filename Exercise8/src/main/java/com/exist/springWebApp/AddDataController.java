package com.exist.springWebApp;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDataController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView returnValue = new ModelAndView("addData");

        try {
            Validate validate = new Validate();

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String id = request.getParameter("id");

        if (!validate.containsOnlyNumbers(id)) {
                throw new Exception ("Invalid id.");
        }

        if (!validate.containsOnlyLetters(firstName) || !validate.containsOnlyLetters(lastName)) {
                throw new Exception ("Invalid name.");
        }

            returnValue.addObject("id", id);
            returnValue.addObject("firstName", "first name is: " + firstName);
            returnValue.addObject("lastName", "last name is: " + lastName);
            returnValue.addObject("message", "Data Added!");
        } catch (Exception e) {
            String error = e.toString().split(":")[1];

            returnValue.addObject("message", "Error! " + error);
        } finally {
            return returnValue;
        }
    }

}