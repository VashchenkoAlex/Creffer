package com.creffer.controllers.manager.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class ManagerController {
    //private static final Logger log = LoggerFactory.getLogger(com.creffer.controllers.manager.ManagerController.class);
    @GetMapping(value = "/manager")
    public ModelAndView dashGet(){
       // log.info("/manager");
        return new ModelAndView("/protected/manager/users/list_user");
    }
    @PostMapping(value = "/manager")
    public ModelAndView dashPost(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        response.setStatus(HttpServletResponse.SC_OK);
        return new ModelAndView("forward:/protected/manager/users/list_user");
    }

}
