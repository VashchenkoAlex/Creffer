package com.creffer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Станислав on 21.07.2018.
 */



@Controller
public class HeaderController {

    @GetMapping(value = "/header")
    public ModelAndView dashGet(){

        return new ModelAndView("/protected/header");
    }
    @PostMapping(value = "/header")
    public ModelAndView dashPost(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        response.setStatus(HttpServletResponse.SC_OK);
        return new ModelAndView("forward:/protected/header");
    }

}