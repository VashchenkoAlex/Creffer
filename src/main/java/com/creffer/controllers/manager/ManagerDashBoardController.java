package com.creffer.controllers.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/managerDashboard")
public class ManagerDashBoardController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView dashGet(){
        System.out.println("/managerDashboard");
        return new ModelAndView("/protected/manager/dashboard");
    }
    @RequestMapping(method = RequestMethod.POST,produces = "text/html")
    public ModelAndView dashPost(){
        /*HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        response.setStatus(HttpServletResponse.SC_OK);
        return "redirect:/pages/protected/admin/dashboard.html";*/
        return new ModelAndView("forward:/pages/protected/manager/dashboard.html");
    }
}
