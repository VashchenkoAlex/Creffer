package com.creffer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/","/main"})
public class MainPageController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mainGet(){
        return new ModelAndView("/main");
    }
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView mainPost(){
        return new ModelAndView("forward:/main");
    }
}
