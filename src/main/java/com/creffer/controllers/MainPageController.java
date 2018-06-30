package com.creffer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {
    @RequestMapping(value = {"/","/main"},method = RequestMethod.GET,produces = "text/html")
    public ModelAndView mainPage(){
        return new ModelAndView("main");
    }
}
