package com.exist.springWebApp.controllers;


import com.exist.service.SimpleUserList;
import com.exist.storage.User;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController implements Controller {
    SimpleUserList simpleUserList;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView returnValue = new ModelAndView("view");

        Map<String, Object> myModel = new HashMap<String, Object>();
        User user = new User();

        try {
            int idNumber = Integer.valueOf(request.getParameter("id"));
            user.setId(idNumber);
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));

            simpleUserList.setUsers(user);
            myModel.put("users", simpleUserList.getUsers());

            returnValue.addObject("model", myModel);
        } catch (NumberFormatException e) {
            String message = "Error. Id input must be a number";

            returnValue = new ModelAndView("error", "message", message);
        } finally {
            return returnValue;
        }
    }

    public SimpleUserList getSimpleUserList() {
        return simpleUserList;
    }

    public void setSimpleUserList(SimpleUserList simpleUserList) {
        this.simpleUserList = simpleUserList;
    }
}

//FOR NOTES:
//public class UserController implements Controller {
//    private SimpleUserList simpleUserList;
//    private CreateUserList createUserList;
//    private List<User> userList;
//
//    @Override
//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        Map<String, Object> myModel = new HashMap<String, Object>();
//
//        createUserList.populate();
//
//        simpleUserList.getUsers().clear();
//
//        for (User user : userList) {
//            simpleUserList.getUsers().add(user);
//        }
//
//        for (User user : createUserList.getUsers()) {
//            simpleUserList.getUsers().add(user);
//        }
//        createUserList.getUsers().clear();
//
//        this.simpleUserList.addEmailDomain();
//        myModel.put("users", this.simpleUserList.getUsers());
//
//        return new ModelAndView("view", "model", myModel);
//    }
//
//    public void setSimpleUserList(SimpleUserList simpleUserList) {
//        this.simpleUserList = simpleUserList;
//        userList = new ArrayList<User>();
//        for (User user : simpleUserList.getUsers()) {
//            userList.add(user);
//        }
//    }
//
//    public void setCreateUserList(CreateUserList createUserList) {
//        this.createUserList = createUserList;
//    }
//
//}