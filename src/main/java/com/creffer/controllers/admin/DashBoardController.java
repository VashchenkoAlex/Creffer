package com.creffer.controllers.admin;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/adminDashboard")
public class DashBoardController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView dashGet(HttpServletRequest request){
        System.out.println("/adminDashboard");
        return new ModelAndView("redirect:/pages/protected/admin/dashboard");
        //return new ModelAndView("forward:/pages/protected/admin/dashboard.html");
    }
    @RequestMapping(method = RequestMethod.POST,produces = "text/html")
    public ModelAndView dashPost(){
        /*HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        response.setStatus(HttpServletResponse.SC_OK);
        return "redirect:/pages/protected/admin/dashboard.html";*/
        return new ModelAndView("forward:/pages/protected/admin/dashboard.html");
    }
}
