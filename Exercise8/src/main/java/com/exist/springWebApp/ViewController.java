package com.exist.springWebApp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @RequestMapping("/view")
    public ModelAndView viewData() {

        String message = "Testing Annotations!";
        return new ModelAndView("view", "viewmessage", message);
    }
}