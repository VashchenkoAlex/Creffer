package com.creffer.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/adminDashboard")
public class DashBoardController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView dashGet(){
        System.out.println("/adminDashboard");
        return new ModelAndView("forward:/pages/protected/admin/dashboard.html");
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
