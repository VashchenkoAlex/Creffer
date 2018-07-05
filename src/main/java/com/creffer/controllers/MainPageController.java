package com.creffer.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/","/main"})
public class MainPageController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mainGet(HttpServletRequest request){
        HttpSession session = request.getSession();
        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (context!=null){
            SecurityContextHolder.setContext(context);
        }
        return new ModelAndView("/main");
    }
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView mainPost(){
        return new ModelAndView("forward:/main");
    }
}
