package com.creffer.controllers.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagerController {

    @RequestMapping(value = "/manager**", method = RequestMethod.GET)
    public ModelAndView managerPage(){
        ModelAndView model = new ModelAndView();
        model.addObject("title","Manager Page");
        model.addObject("message","Creffer Manager Page");
        model.setViewName("manager");
        return model;
    }
}
