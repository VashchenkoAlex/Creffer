package com.creffer.controllers;

import com.creffer.models.users.UserModel;
import com.creffer.services.users.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = {"/","/main"},method = RequestMethod.GET)
    public ModelAndView mainPage(){
        return new ModelAndView("main");
    }
    @GetMapping("/admin/main")
    public ModelAndView adminMain(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName","Welcome "+user.getFirstName()+
                " "+user.getLastName()+" ("+user.getEmail()+")");
        modelAndView.addObject("userMessage","Content Available for users with Admin Role");
        modelAndView.setViewName("admin/main");
        return modelAndView;
    }
}
